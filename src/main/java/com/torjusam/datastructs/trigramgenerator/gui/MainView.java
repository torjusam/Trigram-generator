package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.gui.input.InputSection;
import com.torjusam.datastructs.trigramgenerator.gui.output.OutputSection;
import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import com.torjusam.datastructs.trigramgenerator.services.TrigramStorage;
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

    public MainView() {
        triStore = new TrigramStorage();
        triController = new TrigramController(triStore);

        InputSection inputSection = new InputSection(triController);
        OutputSection outputSection = new OutputSection(triController);

        setLeft(inputSection);
        setCenter(outputSection);
    }
}