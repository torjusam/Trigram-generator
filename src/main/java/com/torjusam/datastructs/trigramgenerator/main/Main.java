package com.torjusam.datastructs.trigramgenerator.main;

import com.torjusam.datastructs.trigramgenerator.services.TextReader;
import com.torjusam.datastructs.trigramgenerator.services.TrigramStorage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Main class for testing reading
 *
 * TODO:
 *  - Make a class for handling textReader (including errors)
 *  - Dynamically call on textReader or a URL-reader based on input source
 *  - Handle errors
 */
public class Main {
    public static void main(String[] args) {
        TextReader textReader = new TextReader();
        TrigramStorage trigramStorage = new TrigramStorage();

        try {
            List<String> words = textReader.readFromFile("src/main/resources/com/torjusam/datastructs/trigramgenerator/main/file.txt");

            trigramStorage.addTrigrams(words); // Add to storage/map

            // Print map
            for (Map.Entry<List<String>, Map<String, Integer>> entry : trigramStorage.getTrigramMap().entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // File path is null/empty
            // User did something wrong on GUI probably
            // Display "try again" or smth
            System.err.println("Invalid file path: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}