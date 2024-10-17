package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.HandleUncaughtExceptions;
import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.torjusam.datastructs.trigramgenerator.gui.LinkSectionController.defaultUrl;
import static com.torjusam.datastructs.trigramgenerator.gui.LinkSectionController.isValidLink;

class LinkSection extends VBox {

    private final VBox linkListContainer;
    private final LinkSectionController linkController;

    // Constructor
    LinkSection(TrigramController triController) {
        List<String> selectedLinks = new ArrayList<>();
        this.linkListContainer = new VBox(10);
        this.linkController = new LinkSectionController(triController, selectedLinks);

        Label header = new Label("Links");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button addLinkButton = new Button("Add Link");
        addLinkButton.setOnAction(e -> handleAddLink());

        this.getChildren().addAll(
                header,
                addLinkButton,
                linkListContainer
        );

    }

    /**
     * Adds a link to the link list and displays it in the UI.
     */
    private void handleAddLink() {
        TextField linkInputField = new TextField();
        linkInputField.setPromptText("Enter a URL");
        Button confirmBtn = new Button("Confirm");

        HBox linkInputRow = new HBox(10);
        linkInputRow.getChildren().addAll(linkInputField, confirmBtn);

        linkListContainer.getChildren().add(linkInputRow);

        confirmBtn.setOnAction(e -> {
            String link = linkInputField.getText();
            if (isValidLink(link)) {
                try {
                    linkController.addLink(link);
                    displayLink(link);
                    linkListContainer.getChildren().remove(linkInputRow); // Remove input row after confirming
                } catch (Exception e1) {
                    HandleUncaughtExceptions.showErrorAlert("Link Error", e1.getMessage());
                }
            } else {
                // Invalid link, show red border and error prompt
                linkInputField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                linkInputField.setPromptText("Enter a valid link (e.g., https://example.com)");
            }
        });
    }

    /**
     * Displays a link in the list.
     *
     * @param link The link to be displayed.
     */
    private void displayLink(String link) {
        HBox linkRow = new HBox(10);
        TextField linkField = new TextField(link);
        linkField.setEditable(false);

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            linkController.removeLink(link);
            linkListContainer.getChildren().remove(linkRow);
        });

        linkRow.getChildren().addAll(linkField, removeButton);
        linkListContainer.getChildren().add(linkRow);
    }
}
