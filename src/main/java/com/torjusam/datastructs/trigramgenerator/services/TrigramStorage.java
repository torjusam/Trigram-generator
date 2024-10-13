package com.torjusam.datastructs.trigramgenerator.services;

import java.util.*;

/***
 * Data structure for trigrams
 */
public class TrigramStorage {

    private Map<List<String>, Map<String, Integer>> trigramMap;

    public TrigramStorage() {
        this.trigramMap = new HashMap<>();
    }

    // Add-method that uses the list of words
    void addTrigrams(List<String> words) {
        for (int i = 0; i < words.size() - 2; i++) {
            // Get the first two words (trigram key)
            List<String> key = Arrays.asList(words.get(i), words.get(i + 1));

            String thirdWord = words.get(i + 2); // Get the third word (trigram value)

            // Update map
            trigramMap.putIfAbsent(key, new HashMap<>());
            Map<String, Integer> thirdWordMap = trigramMap.get(key);
            thirdWordMap.put(
                    thirdWord, thirdWordMap.getOrDefault(thirdWord, 0) + 1
            );
        }
    }

    // Placeholder
    void printTrigramMap() {
        for (Map.Entry<List<String>, Map<String, Integer>> entry : trigramMap.entrySet()) {
            List<String> key = entry.getKey();
            Map<String, Integer> valueMap = entry.getValue();

            System.out.println(key + " -> " + valueMap);
        }
    }

    public Map<List<String>, Map<String, Integer>> getTrigramMap() {
        return trigramMap;
    }

}
