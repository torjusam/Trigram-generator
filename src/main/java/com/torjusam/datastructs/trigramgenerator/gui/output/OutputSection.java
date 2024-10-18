package com.torjusam.datastructs.trigramgenerator.gui.output;

import com.torjusam.datastructs.trigramgenerator.services.TextGenerator;
import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.control.Separator;

/**
 * Class extends vbox and sets up the right side of the screen
 */
public class OutputSection extends VBox {
    private TrigramController trigramController;
    private SelectWordsDropdown selectWords;
    private OutputField outputField;
    private TextGenerator textGen;

    public OutputSection(TrigramController trigramController) {
        this.trigramController = trigramController;
        this.selectWords = new SelectWordsDropdown(trigramController);
        this.textGen = new TextGenerator(trigramController);
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

    /**
     * Initializes button for generating text and sets eventhandler action
     */
    private Button createGenerateBtn() {
        Button generateButton = new Button("Generate Text");
        generateButton.setDisable(true); // Initially disabled until both words are selected

        selectWords.setOnBothWordsSelected(() -> {
            generateButton.setDisable(false);
        });

        generateButton.setOnAction(e -> {
            String selectedWord1 = stripFrequency(selectWords.getSelectedFirstWord());
            String selectedWord2 = selectWords.getSelectedSecondWord();

            // Generate text based on the selected words
            String generatedText = textGen.generate(selectedWord1, selectedWord2);
            outputField.displayGeneratedText(generatedText);
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

    /**
     * Inner class for the textarea where text is generated
     */
    private class OutputField extends TextArea {

        private OutputField() {
            setEditable(false);
            setMinHeight(250);
            setMaxSize(600, 350);
            setWrapText(true);
            setStyle("-fx-font-size: 16px;");
        }

        private void displayGeneratedText(String text) {
            this.setText(text);
        }
    }
}