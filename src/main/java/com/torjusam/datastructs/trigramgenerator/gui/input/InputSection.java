package com.torjusam.datastructs.trigramgenerator.gui.input;

import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

/**
 * InputSection er hoved-containeren for input-package, og er en VBox-container
 * som er ansvarlig for å initalisere txt-fil- og link input delene.
 */
public class InputSection extends VBox {

    public InputSection(TrigramController trigramController) {
        setSpacing(10);
        setPadding(new Insets(0, 10, 0, 10));
        setStyle("-fx-border-color: transparent gray transparent transparent; -fx-border-width: 0 1px 0 0;");

        Label inputHeader = new Label("Input");
        inputHeader.setStyle("-fx-font-size: 33px; -fx-font-weight: bold;");

        // Initialize Input sections and pass them controller for the trigram store
        TxtFileSection txtFileSection = new TxtFileSection(trigramController);
        LinkSection linkSection = new LinkSection(trigramController);

        getChildren().addAll(
                inputHeader,
                txtFileSection,
                new Separator(),
                linkSection
        );
    }
}
