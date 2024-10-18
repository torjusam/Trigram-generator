package com.torjusam.datastructs.trigramgenerator.gui.input;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Controller class for {@link TxtFileSection}.
 * Manages list of selected files and handles file validation etc
 */
class TxtFileSectionController {
    private final TrigramController trigramController;
    private final List<File> selectedFiles;

    TxtFileSectionController(TrigramController trigramController, List<File> selectedFiles) {
        this.trigramController = trigramController;
        this.selectedFiles = selectedFiles;
    }

    void addFile(File file) throws IllegalArgumentException, IOException {
        if (file == null) throw new IllegalArgumentException("File is null. Cannot add it.");

        if (selectedFiles.contains(file))
            throw new IllegalArgumentException("File has already been added: " + file.getName());

        selectedFiles.add(file);
        trigramController.processFile(file.getAbsolutePath());
    }

    public void removeFile(File file) {
        selectedFiles.remove(file);
    }
}