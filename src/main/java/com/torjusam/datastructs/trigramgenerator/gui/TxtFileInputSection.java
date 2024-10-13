package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX class where user can select a .txt file, and it gets processed with the trigramController.
 */
class TxtFileInputSection extends VBox {

    private List<File> selectedFiles;
    private VBox fileListContainer;
    private TrigramController trigramController;

    private static File defaultFile = new File("src/main/resources/com/torjusam/datastructs/trigramgenerator/file.txt");

    // Constructor
    TxtFileInputSection(TrigramController trigramController) {
        this.trigramController = trigramController;
        this.selectedFiles = new ArrayList<>();
        this.fileListContainer = new VBox(10);

        // btn for adding files
        Button addFileButton = new Button("Add File");
        addFileButton.setOnAction(e -> handleSelectFile());

        this.getChildren().addAll(
                new Label("Text files"), // Header
                addFileButton,
                fileListContainer
        );

        if (defaultFile.exists())
            handleAddFile(defaultFile);
    }

    // Helper f or adding file to list and displaying them
    private void handleAddFile(File file) {
        selectedFiles.add(file);
        displayFile(file);

        try {
            trigramController.processFile(file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }

    // Display file in the list
    private void displayFile(File file) {
        HBox fileRow = new HBox(10);
        TextField filePathField = new TextField(file.getName());
        filePathField.setEditable(false);

        // btn for removing specific file and this row
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> handleRemoveFile(file, fileRow));

        fileRow.getChildren().addAll(filePathField, removeButton);
        fileListContainer.getChildren().add(fileRow);
    }

    private void handleSelectFile() {
        FileChooser fileChooser = new FileChooser();
        // filters to only .txt when selecting a file
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());

        // What if file already exists??
        if (selectedFile != null)
            handleAddFile(selectedFile);
    }

    // Method to handle removing a file
    private void handleRemoveFile(File file, HBox fileRow) {
        selectedFiles.remove(file);
        fileListContainer.getChildren().remove(fileRow);
    }
}
