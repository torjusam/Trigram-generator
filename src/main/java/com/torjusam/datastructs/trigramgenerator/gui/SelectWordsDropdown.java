package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsible for initializing, enabling/disabling and populating the ComboBox.
 */
public class SelectWordsDropdown extends GridPane {

    private final ComboBox<String> capitalWordsComboBox;
    private final ComboBox<String> nextWordsComboBox;
    private final Label infoLabel;
    private final TrigramController trigramController;

    public SelectWordsDropdown(TrigramController triController) {
        this.trigramController = triController;

        // Initialize the ComboBoxes and label
        this.capitalWordsComboBox = new ComboBox<>();
        this.capitalWordsComboBox.setDisable(true); // Initially disabled

        this.nextWordsComboBox = new ComboBox<>();
        this.nextWordsComboBox.setDisable(true); // Initially disabled

        this.infoLabel = new Label("Add input data to populate words");
        this.infoLabel.setVisible(true); // Visible while no data is present

        // Add elements to the VBox
        this.getChildren().addAll(infoLabel, capitalWordsComboBox, nextWordsComboBox);

        // Check if trigram data is ready
        trigramController.addTrigramListener(() -> {
            enableCapitalWordsDropdown();
        });

        // Add listener for capital words selection
        capitalWordsComboBox.setOnAction(e -> {
            String selectedCapitalWord = capitalWordsComboBox.getValue();
            if (selectedCapitalWord != null) {
                enableNextWordsDropdown(selectedCapitalWord);
            }
        });
    }

    /**
     * Enable the capital words dropdown once trigram data is available
     */
    private void enableCapitalWordsDropdown() {
        if (!trigramController.getTrigramStorage().getTrigramMap().isEmpty()) {
            capitalWordsComboBox.setDisable(false); // Enable the capital words ComboBox
            infoLabel.setVisible(false); // Hide label once dropdown is enabled
            populateCapitalWordsDropdown();
        }
    }

    /**
     * Populate the capital words dropdown with words that start with a capital letter
     */
    private void populateCapitalWordsDropdown() {
        Map<String, Integer> capitalWords = trigramController.getCapitalWordsWithFrequency();

        // Populate ComboBox with words and frequencies
        capitalWords.forEach((word, frequency) -> {
            capitalWordsComboBox.getItems().add(word + " (" + frequency + ")");
        });
    }

    /**
     * Enable the next words dropdown based on the selected capital word
     */
    private void enableNextWordsDropdown(String selectedCapitalWord) {
        nextWordsComboBox.getItems().clear(); // Clear previous items
        nextWordsComboBox.setDisable(false); // Enable the next words ComboBox

        // Extract the word without the frequency information (e.g., "Word (3)" -> "Word")
        String wordOnly = selectedCapitalWord.split(" ")[0];

        // Populate the next words ComboBox
        List<String> possibleNextWords = trigramController.getNextWordsFor(wordOnly);
        nextWordsComboBox.getItems().addAll(possibleNextWords);
    }
}