package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.HandleUncaughtExceptions;
import com.torjusam.datastructs.trigramgenerator.services.TxtFileInputController;
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

    private final List<File> selectedFiles;
    private final VBox fileListContainer;
    private final TxtFileInputController fileController;
    private static final File defaultFile = new File("src/main/resources/com/torjusam/datastructs/trigramgenerator/file.txt");

    // Constructor
    TxtFileInputSection(TrigramController trigramController) {
        this.selectedFiles = new ArrayList<>();
        this.fileListContainer = new VBox(10);
        this.fileController = new TxtFileInputController(trigramController, selectedFiles);

        Label header = new Label("Text files");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // btn for adding files
        Button addFileButton = new Button("Add File");
        addFileButton.setOnAction(e -> handleSelectFile());

        this.getChildren().addAll(header, addFileButton, fileListContainer);

        if (defaultFile.exists())
            handleAddFile(defaultFile);
    }

    private void handleAddFile(File file) {
        try {
            fileController.addFile(file);
            displayFile(file);
        } catch (IllegalArgumentException | IOException e) {
            HandleUncaughtExceptions.showErrorAlert("File Error", e.getMessage());
        }
    }

    // Display file in the list (UI only)
    private void displayFile(File file) {
        HBox fileRow = new HBox(10);
        TextField filePathField = new TextField(file.getName());
        filePathField.setEditable(false);

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            fileController.removeFile(file); // Delegate to FileController
            fileListContainer.getChildren().remove(fileRow);
        });

        fileRow.getChildren().addAll(filePathField, removeButton);
        fileListContainer.getChildren().add(fileRow);
    }

    // Method to select a new file
    private void handleSelectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());

        if (selectedFile != null)
            handleAddFile(selectedFile);
    }
}