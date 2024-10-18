package com.torjusam.datastructs.trigramgenerator.gui.output;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import javafx.scene.control.ComboBox;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for the selectWordsDropdown.
 * Responsible for populating the comboboxes with options, and sorting.
 *
 * @see SelectWordsDropdown
 */
class SelectWordsDropdownController {

    private final TrigramController trigramController;

    SelectWordsDropdownController(TrigramController trigramController) {
        this.trigramController = trigramController;
    }

    /**
     * Populate the capital words dropdown with words that start with a capital letter
     * @param comboBox comboBox to populate
     */
    void populateCapitalWordsDropdown(ComboBox<String> comboBox) {
        Map<String, Integer> capitalWords = getCapitalWordsWithFrequency();
        comboBox.getItems().clear();
        capitalWords.forEach((word, frequency) -> {
            comboBox.getItems().add(word + " (" + frequency + ")");
        });
    }

    void populateNextWordsDropdown(ComboBox<String> comboBox, String selectedCapitalWord) {
        comboBox.getItems().clear();
        String wordOnly = selectedCapitalWord.split(" ")[0]; // Remove frequency from the string

        List<String> nextWords = getNextWordsFor(wordOnly);
        comboBox.getItems().addAll(nextWords);
    }

    /**
     * Get capital words, with frequency from trigram storage
     * @return Map of words starting with capital letters, sorted by frequency of apperance
     */
    Map<String, Integer> getCapitalWordsWithFrequency() {
        Map<String, Integer> frequencyMap = new HashMap<>();

        trigramController.getTrigramStorage().getTrigramMap().forEach((key, value) -> {
            String firstWord = key.get(0);

            // Check if the first word starts with a capital letter
            if (Character.isUpperCase(firstWord.charAt(0)))
                frequencyMap.put(firstWord, frequencyMap.getOrDefault(firstWord, 0) + 1);
        });

        // Sort by frequency (descending order) and return
        return frequencyMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * Method to get next words based on a selected capital word
     *
     * @param capitalWord
     * @return list of possible next words; to be used to populate the combobox
     */
    List<String> getNextWordsFor(String capitalWord) {
        Set<String> possibleNextWords = new HashSet<>();

        // find words where the first key matches the parameter
        trigramController.getTrigramStorage().getTrigramMap().forEach((key, value) -> {
            if (key.get(0).equals(capitalWord))
                possibleNextWords.add(key.get(1));
        });
        return new ArrayList<>(possibleNextWords);
    }

    void addTrigramListener(Runnable listener) {
        trigramController.addTrigramListener(listener);
    }

    boolean isTrigramDataAvailable() {
        return !trigramController.getTrigramStorage().getTrigramMap().isEmpty();
    }

}