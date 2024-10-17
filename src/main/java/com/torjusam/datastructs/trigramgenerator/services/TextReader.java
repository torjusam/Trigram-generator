package com.torjusam.datastructs.trigramgenerator.services;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

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

    /**
     * Processes text / tokens found in the URL, and stores it in the arraylist of words.
     * It uses Regex to try to filter out HTML tags, and unwanted words that are usually found on wikipedia (norwegian).
     *
     * The validation is mostly just hardcoded values of unwanted patterns that was added through trial and error.
     * Would need to use a library to properly filter webpages to make this more accurate.
     *
     * Tested with norwegian wikipedia articles;  @see https://no.wikipedia.org/wiki/Ringenes_herre
     * @param urlPath
     */
    List<String> readFromURL(String urlPath) throws IOException {
        if (urlPath == null || urlPath.isEmpty()) throw new IllegalArgumentException("URL cannot be null or empty.");

        URL url = new URL(urlPath);
        List<String> words = new ArrayList<>();

        // Regex
        String validWordRegex = "^[a-zA-ZæøåÆØÅ'-]+$";
        Pattern htmlPattern = Pattern.compile("<[^>]+>");
        Pattern unwantedPattern = Pattern.compile("^(recursion|wikipedia|wikidata|wikimedia|sidefeltet|seconds|false|visited|include|vector|key|with|autoritetsdatalenker|limit|memory|cache|time|expansion|lua|report|rendering|bytes|function|saved|parser|Wikidata|node|transclusion|id|revision|template|argument|cached|real|highest|wikibase|newpp|entities|navigasjon|prosjekt|handlinger).*", Pattern.CASE_INSENSITIVE);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            boolean newSentence = true;

            while ((line = reader.readLine()) != null) {
                line = htmlPattern.matcher(line).replaceAll(""); // Remove HTML tags
                String[] tokens = line.split("\\s+");

                for (String token : tokens) {
                    token = token.trim();

                    if (unwantedPattern.matcher(token).find()) continue; // Skip unwanted tokens

                    // Handle valid words
                    if (newSentence && token.matches(validWordRegex)) {
                        words.add(token);
                        newSentence = false;
                    } else if (token.matches(validWordRegex.toLowerCase()) || token.matches(validWordRegex)) {
                        words.add(token); // Add both lowercase and uppercase words
                    }

                    // Mark new sentence if the word ends with a period, question mark, or exclamation mark
                    if (token.endsWith(".") || token.endsWith("?") || token.endsWith("!")) {
                        newSentence = true;
                    }
                }
            }
        }

        return words;
    }
}