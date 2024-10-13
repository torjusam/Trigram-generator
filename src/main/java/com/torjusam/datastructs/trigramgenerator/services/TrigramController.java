package com.torjusam.datastructs.trigramgenerator.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Initalizes trigramstorage and
 */
public class TrigramController {
    private TrigramStorage trigramStorage;
    private TextReader textReader;

    public TrigramController(TrigramStorage trigramStorage) {
        this.trigramStorage = trigramStorage;
        this.textReader = new TextReader();
    }

    // Process text from a file
    public void processFile(String filePath) throws IOException {
        // double-check file first
        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            throw new IOException("File does not exist or cannot be read: " + filePath);
        }

        // If file is valid, process it
        List<String> words = textReader.readFromFile(filePath);
        trigramStorage.addTrigrams(words); // Store trigrams in the map
        trigramStorage.printTrigramMap();
    }

    // Placeholder for reading from url
    public void processUrl(String url) throws IOException {

    }

    public TrigramStorage getTrigramStorage() {
        return trigramStorage;
    }
}