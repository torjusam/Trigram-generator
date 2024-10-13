package com.torjusam.datastructs.trigramgenerator.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TxtFileInputController {

    private final TrigramController trigramController;
    private final List<File> selectedFiles;

    // Constructor
    public TxtFileInputController(TrigramController trigramController, List<File> selectedFiles) {
        this.trigramController = trigramController;
        this.selectedFiles = selectedFiles;
    }

    // Method to add and process a file
    public void addFile(File file) throws IllegalArgumentException, IOException {
        if (file == null) {
            throw new IllegalArgumentException("File is null. Cannot add it.");
        }

        if (selectedFiles.contains(file)) {
            throw new IllegalArgumentException("File has already been added: " + file.getName());
        }

        // Add the file to the list
        selectedFiles.add(file);
        trigramController.processFile(file.getAbsolutePath());
    }

    // Method to remove a file from the list
    public void removeFile(File file) {
        selectedFiles.remove(file);
    }
}