package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import com.torjusam.datastructs.trigramgenerator.services.TrigramStorage;
import javafx.scene.layout.VBox;

/**
 * MainView is the layout of the app.
 * Responsible for setting up JavaFX classes and their respective controller classes
 */
public class MainView extends VBox {

    // The structures for trigrams are stored and passed to child GUI elements that needs it (read/write)
    private final TrigramStorage triStore;
    private final TrigramController triController;

    /**
     * Constructor initalizes TrigramStorage and its controller,
     * and initalizes JavaFX elements and passes the controller to them.
     */
    public MainView() {
        triStore = new TrigramStorage();
        triController = new TrigramController(triStore);

        // Initialize TxtFileInputSection and pass the TrigramController
        TxtFileSection txtFileSection = new TxtFileSection(triController);

        // Add TxtFileInputSection to the layout
        this.getChildren().add(txtFileSection);
    }
}