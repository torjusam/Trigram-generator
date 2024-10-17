package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;

import java.io.IOException;
import java.util.List;

class LinkSectionController {

    private final List<String> selectedLinks;
    private final TrigramController trigramController;

    LinkSectionController(TrigramController trigramController, List<String> selectedLinks) {
        this.trigramController = trigramController;
        this.selectedLinks = selectedLinks;
    }

    /**
     * Adds a link and processes it
     */
    void addLink(String link) throws IllegalArgumentException, IOException {
        if (link == null || link.trim().isEmpty())
            throw new IllegalArgumentException("Link cannot be null or empty.");

        if (selectedLinks.contains(link)) throw new IllegalArgumentException("Link has already been added: " + link);
        if (!isValidLink(link)) throw new IllegalArgumentException("Invalid link format.");

        selectedLinks.add(link);
        trigramController.processUrl(link); // Process (fetch content and store trigrams)
    }

    void removeLink(String link) {
        selectedLinks.remove(link);
    }

    static boolean isValidLink(String link) {
        return link.contains(".") && (link.contains("http") || link.contains("www"));
    }
}
