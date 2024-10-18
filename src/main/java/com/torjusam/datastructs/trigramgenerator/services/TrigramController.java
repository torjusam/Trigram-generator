package com.torjusam.datastructs.trigramgenerator.services;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    // /
    public Map<String, Integer> getCapitalWordsWithFrequency() {
        Map<String, Integer> frequencyMap = new HashMap<>();

        // Iterate through the trigram map
        trigramStorage.getTrigramMap().forEach((key, value) -> {
            String firstWord = key.get(0);

            // Check if the first word starts with a capital letter
            if (Character.isUpperCase(firstWord.charAt(0))) {
                frequencyMap.put(firstWord, frequencyMap.getOrDefault(firstWord, 0) + 1);
            }
        });

        // Sort by frequency (descending order) and return
        return frequencyMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    // Method to get next words based on a selected capital word
    public List<String> getNextWordsFor(String capitalWord) {
        Set<String> possibleNextWords = new HashSet<>();

        // Iterate through the trigram map to find sequences that start with the given capital word
        trigramStorage.getTrigramMap().forEach((key, value) -> {
            if (key.get(0).equals(capitalWord)) {
                possibleNextWords.add(key.get(1)); // Add the next word to the set
            }
        });

        // Return the set as a sorted list
        return new ArrayList<>(possibleNextWords);
    }

    public TrigramStorage getTrigramStorage() {
        return trigramStorage;
    }
}