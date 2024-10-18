package com.torjusam.datastructs.trigramgenerator.services;

import java.io.IOException;
import java.util.*;

/**
 * Initalizes trigramstorage and
 */
public class TrigramController {
    private TrigramStorage trigramStorage;
    private TextReader textReader;
    private List<Runnable> trigramListeners;

    public TrigramController(TrigramStorage trigramStorage) {
        this.trigramStorage = trigramStorage;
        this.textReader = new TextReader();
        this.trigramListeners = new ArrayList<>();
    }

    // Process text from a file
    public void processFile(String filePath) throws IOException {
        List<String> words = textReader.readFromFile(filePath);
        trigramStorage.addTrigrams(words); // Store trigrams in the map
        trigramStorage.printTrigramMap();
        notifyTrigramListeners();
        System.out.println("Finished reading from: " + filePath);
    }


    // Process text from url
    public void processUrl(String url) throws IOException {
        List<String> words = textReader.readFromURL(url);
        trigramStorage.addTrigrams(words);
        trigramStorage.printTrigramMap();
        notifyTrigramListeners();
        System.out.println("Finished reading from: " + url);
    }

    public void addTrigramListener(Runnable listener) {
        trigramListeners.add(listener);
    }

    // Notify all registered listeners
    private void notifyTrigramListeners() {
        for (Runnable listener : trigramListeners) {
            listener.run();
        }
    }

    public TrigramStorage getTrigramStorage() {
        return trigramStorage;
    }
}