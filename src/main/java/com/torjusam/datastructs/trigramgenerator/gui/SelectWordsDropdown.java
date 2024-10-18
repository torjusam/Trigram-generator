package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 * Responsible for initializing, enabling/disabling and populating the ComboBox.
 */
public class SelectWordsDropdown extends GridPane {

    private final ComboBox<String> capitalWordsComboBox;
    private final ComboBox<String> nextWordsComboBox;
    private final Label infoLabel;
    private final SelectWordsDropdownController controller;

    public SelectWordsDropdown(TrigramController trigramController) {
        this.controller = new SelectWordsDropdownController(trigramController);

        // Initialize ComboBoxes as initally disabled
        this.capitalWordsComboBox = new ComboBox<>();
        this.capitalWordsComboBox.setDisable(true);
        this.nextWordsComboBox = new ComboBox<>();
        this.nextWordsComboBox.setDisable(true);

        // Info label
        this.infoLabel = new Label("Add input data to populate words");
        this.infoLabel.setVisible(true);

        initializeGrid();

        controller.addTrigramListener(this::enableCapitalWordsDropdown); // Check if trigram data is redy

        // Add listener for capital words selection
        capitalWordsComboBox.setOnAction(e -> {
            String selectedCapitalWord = capitalWordsComboBox.getValue();
            if (selectedCapitalWord != null)
                enableNextWordsDropdown(selectedCapitalWord);
        });
    }

    private void initializeGrid() {
        setHgap(20);
        setVgap(10);

        Label capitalWordLabel = new Label("First word, sorted by occurrence:");
        Label nextWordLabel = new Label("Second word:");

        add(infoLabel, 0, 0);
        add(capitalWordLabel, 0, 1);
        add(capitalWordsComboBox, 0, 2);
        add(nextWordLabel, 1, 1);
        add(nextWordsComboBox, 1, 2);

    }

    /**
     * Enable dropdown for first word
     */
    private void enableCapitalWordsDropdown() {
        if (controller.isTrigramDataAvailable()) {
            capitalWordsComboBox.setDisable(false);
            infoLabel.setVisible(false);
            controller.populateCapitalWordsDropdown(capitalWordsComboBox);
        }
    }

    /**
     * Enable the next words dropdown based on the selected capital word
     */
    private void enableNextWordsDropdown(String selectedCapitalWord) {
        nextWordsComboBox.setDisable(false);
        controller.populateNextWordsDropdown(nextWordsComboBox, selectedCapitalWord);
    }
}