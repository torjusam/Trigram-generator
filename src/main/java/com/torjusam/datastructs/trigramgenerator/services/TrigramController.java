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

    public String generateText(String firstWord, String secondWord) {
        StringBuilder generatedText = new StringBuilder(firstWord + " " + secondWord);
        List<String> currentKey = Arrays.asList(firstWord, secondWord);

        while (true) {
            // Fetch the map of possible next words for the current two-word combination
            Map<String, Integer> nextWordsMap = trigramStorage.getTrigramMap().get(currentKey);

            // Stop generating if no next words are found for the current key
            if (nextWordsMap == null || nextWordsMap.isEmpty()) {
                break;
            }

            // Select the next word based on probabilities
            String nextWord = selectNextWordBasedOnProbability(nextWordsMap);

            // Append the next word to the generated text
            generatedText.append(" ").append(nextWord);

            // Update the current two-word key for the next iteration
            currentKey = Arrays.asList(currentKey.get(1), nextWord); // Shift to next two-word combination
        }

        return generatedText.toString();
    }

    /**
     * Select the next word from the map based on the frequency/probability distribution.
     */
    private String selectNextWordBasedOnProbability(Map<String, Integer> nextWordsMap) {
        // Calculate total frequency sum for the probability distribution
        int total = nextWordsMap.values().stream().mapToInt(Integer::intValue).sum();

        // Generate a random number between 0 and total to simulate the probability selection
        int randomNum = new Random().nextInt(total);

        int cumulativeSum = 0;
        for (Map.Entry<String, Integer> entry : nextWordsMap.entrySet()) {
            cumulativeSum += entry.getValue();
            if (randomNum < cumulativeSum) {
                return entry.getKey(); // Select this word
            }
        }

        return null; // Fallback, but ideally, this should never happen
    }

    public TrigramStorage getTrigramStorage() {
        return trigramStorage;
    }
}