package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import com.torjusam.datastructs.trigramgenerator.services.TrigramStorage;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 * MainView is the layout of the app.
 * Responsible for initalizing JavaFX classes and their respective controller classes,
 * aswell as the main shared TrigramStorage and Controller.
 */
public class MainView extends BorderPane {

    private final TrigramStorage triStore;
    private final TrigramController triController;

    /**
     * Constructor initalizes TrigramStorage and its controller,
     * and initalizes JavaFX elements and passes the controller to them.
     */
    public MainView() {
        triStore = new TrigramStorage();
        triController = new TrigramController(triStore);

        VBox InputSection = createInputSection();
        VBox OutputSection = createOutputSection();

        setLeft(InputSection);
        setCenter(OutputSection);
    }

    /**
     * Helper method for the section on the left; for inputs
     */
    private VBox createInputSection() {
        // Initialize Input sections and pass them controller for the trigram-store
        TxtFileSection txtFileSection = new TxtFileSection(triController);
        LinkSection linkSection = new LinkSection(triController);

        // container
        VBox inputSec = new VBox(10);
        inputSec.setPadding(new Insets(10));
        inputSec.setStyle("-fx-border-color: gray; -fx-border-width: 1px;"); // CSS border

        inputSec.getChildren().addAll(
                txtFileSection,
                new Separator(),
                linkSection
        );
        return inputSec;
    }

    /**
     * Section on the right; for output components.
     *
     * TODO: Implement
     */
    private VBox createOutputSection() {
        VBox middleSec = new VBox();
        middleSec.setPadding(new Insets(10));

        return middleSec;
    }
}