package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.gui.input.LinkSection;
import com.torjusam.datastructs.trigramgenerator.gui.input.TxtFileSection;
import com.torjusam.datastructs.trigramgenerator.gui.output.OutputSection;
import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import com.torjusam.datastructs.trigramgenerator.services.TrigramStorage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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
    private final OutputSection outputSection;

    /**
     * Constructor initalizes TrigramStorage and its controller,
     * and initalizes JavaFX elements and passes the controller to them.
     */
    public MainView() {
        triStore = new TrigramStorage();
        triController = new TrigramController(triStore);

        outputSection = new OutputSection(triController);
        VBox InputSection = createInputSection();

        setLeft(InputSection);
        setCenter(outputSection);
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
        inputSec.setPadding(new Insets(0, 10, 0, 10));
        inputSec.setStyle("-fx-border-color: transparent gray transparent transparent; -fx-border-width: 0 1px 0 0;"); // border only on the right

        Label inputHeader = new Label("Input");
        inputHeader.setStyle("-fx-font-size: 33px; -fx-font-weight: bold;");

        inputSec.getChildren().addAll(
                inputHeader,
                txtFileSection,
                new Separator(),
                linkSection
        );
        return inputSec;
    }
}