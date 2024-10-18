package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import javafx.scene.control.ComboBox;

import java.util.*;
import java.util.stream.Collectors;

class SelectWordsDropdownController {

    private final TrigramController trigramController;

    public SelectWordsDropdownController(TrigramController trigramController) {
        this.trigramController = trigramController;
    }

    // Add trigram listener
    public void addTrigramListener(Runnable listener) {
        trigramController.addTrigramListener(listener);
    }

    // Check if trigram data is available
    public boolean isTrigramDataAvailable() {
        return !trigramController.getTrigramStorage().getTrigramMap().isEmpty();
    }

    // Populate capital words dropdown with words that start with a capital letter
    public void populateCapitalWordsDropdown(ComboBox<String> comboBox) {
        Map<String, Integer> capitalWords = getCapitalWordsWithFrequency();
        comboBox.getItems().clear();
        capitalWords.forEach((word, frequency) -> {
            comboBox.getItems().add(word + " (" + frequency + ")");
        });
    }

    // Populate the next words dropdown based on the selected capital word
    public void populateNextWordsDropdown(ComboBox<String> comboBox, String selectedCapitalWord) {
        comboBox.getItems().clear();
        String wordOnly = selectedCapitalWord.split(" ")[0]; // Remove frequency from the string
        List<String> nextWords = getNextWordsFor(wordOnly);
        comboBox.getItems().addAll(nextWords);
    }

    // Get capital words with frequency from trigram storage
    public Map<String, Integer> getCapitalWordsWithFrequency() {
        Map<String, Integer> frequencyMap = new HashMap<>();

        // Iterate through the trigram map
        trigramController.getTrigramStorage().getTrigramMap().forEach((key, value) -> {
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
        trigramController.getTrigramStorage().getTrigramMap().forEach((key, value) -> {
            if (key.get(0).equals(capitalWord)) {
                possibleNextWords.add(key.get(1)); // Add the next word to the set
            }
        });

        // Return the set as a sorted list
        return new ArrayList<>(possibleNextWords);
    }
}