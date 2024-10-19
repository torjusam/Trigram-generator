package com.torjusam.datastructs.trigramgenerator.gui.input;

import com.torjusam.datastructs.trigramgenerator.services.HandleUncaughtExceptions;
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
 * JavaFX UI component for managing a list of text files.
 * This class allows users to add and remove files and displays the selected files in the UI.
 * Delegates logic to {@link TxtFileSectionController}.
 */
class TxtFileSection extends VBox {
    private final VBox fileListContainer;
    private final TxtFileSectionController fileController;

    public TxtFileSection(TrigramController triController) {
        List<File> selectedFiles = new ArrayList<>();
        this.fileListContainer = new VBox(10);
        this.fileController = new TxtFileSectionController(triController, selectedFiles);

        Label header = new Label("Text files");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button addFileButton = new Button("Add File");
        addFileButton.setOnAction(e -> handleSelectFile());

        this.getChildren().addAll(
                header,
                addFileButton,
                fileListContainer
        );
    }

    /**
     * Adds a file to the file list and displays it in the UI.
     */
    private void handleAddFile(File file) {
        try {
            fileController.addFile(file);
            displayFile(file);
        } catch (IllegalArgumentException | IOException e) {
            HandleUncaughtExceptions.showErrorAlert("File Error", e.getMessage());
        }
    }

    private void displayFile(File file) {
        HBox fileRow = new HBox(10);
        TextField filePathField = new TextField(file.getName());
        filePathField.setEditable(false);

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            fileController.removeFile(file);
            fileListContainer.getChildren().remove(fileRow);
        });

        fileRow.getChildren().addAll(filePathField, removeButton);
        fileListContainer.getChildren().add(fileRow);
    }

    /**
     * Opens a file chooser to select a new file and adds it to the file list.
     */
    private void handleSelectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());

        if (selectedFile != null)
            handleAddFile(selectedFile);
    }
}