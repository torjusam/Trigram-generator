package com.torjusam.datastructs.trigramgenerator.services;

import java.io.*;
import java.util.*;

/**
 * Reads a text file line by line, splits each line into words while
 * treating punctuation as separate tokens, and returns a list of words
 * and punctuation marks.
 *
 * TODO: Validation, error handling etc.
 *  Currently expects a specific layout and just goes through it with regex..
 */
class TextReader {

    List<String> readFromFile(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty())
            throw new IllegalArgumentException("File path cannot be null or empty.");

        File file = new File(filePath);

        // Check if file exists and is readable
        if (!file.exists()) throw new FileNotFoundException("File not found: " + filePath);
        if (!file.canRead()) throw new IOException("File cannot be read: " + filePath);

        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Regex splits text by both spaces and punctuation, but keeps punctuation as separate tokens.
                String[] tokens = line.toLowerCase().split("(?=\\p{Punct})|\\s+");
                words.addAll(Arrays.asList(tokens));
            }
        }

        return words;
    }
}