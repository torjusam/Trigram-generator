package com.torjusam.datastructs.trigramgenerator.services;

import java.util.*;

public class TrigramStorage {

    private Map<List<String>, Map<String, Integer>> trigramMap;

    public TrigramStorage() {
        this.trigramMap = new HashMap<>();
    }

    // use the list of words
    public void addTrigrams(List<String> words) {
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

    public Map<List<String>, Map<String, Integer>> getTrigramMap() {
        return trigramMap;
    }

    // other methods to interact with map??
}
