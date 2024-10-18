package com.torjusam.datastructs.trigramgenerator.gui.output;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Separator;

public class OutputSection extends VBox {
    private TrigramController trigramController;
    private SelectWordsDropdown selectWords;
    private OutputField outputField;

    public OutputSection(TrigramController trigramController) {
        this.trigramController = trigramController;
        this.selectWords = new SelectWordsDropdown(trigramController);
        this.outputField = new OutputField();

        setPadding(new Insets(0, 10, 0, 10));
        setSpacing(10);
        Label outputHeader = new Label("Output");
        outputHeader.setStyle("-fx-font-size: 33px; -fx-font-weight: bold;");

        Button generateButton = createGenerateBtn();

        getChildren().addAll(
                outputHeader,
                selectWords,
                generateButton,
                new Separator(),
                outputField
        );
    }

    private Button createGenerateBtn() {
        Button generateButton = new Button("Generate Text");
        generateButton.setDisable(true); // Initially disabled until both words are selected

        selectWords.setOnBothWordsSelected(() -> {
            generateButton.setDisable(false);
        });

        // Set the action for the Generate button
        generateButton.setOnAction(e -> {
            // Get selected first word and second word, and remove the frequency info
            String selectedWord1 = stripFrequency(selectWords.getSelectedFirstWord());
            String selectedWord2 = stripFrequency(selectWords.getSelectedSecondWord());

            // Generate text based on the selected words
            String generatedText = trigramController.generateText(selectedWord1, selectedWord2);
            outputField.setText(generatedText);
        });

        return generateButton;
    }

    /**
     * Helper method to remove frequency count from selected word.
     * Example: "Frodo (20)" becomes "Frodo".
     */
    private String stripFrequency(String wordWithFrequency) {
        return wordWithFrequency.split(" ")[0].trim();
    }
}