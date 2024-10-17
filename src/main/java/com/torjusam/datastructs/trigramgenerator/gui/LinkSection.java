package com.torjusam.datastructs.trigramgenerator.gui;

import com.torjusam.datastructs.trigramgenerator.HandleUncaughtExceptions;
import com.torjusam.datastructs.trigramgenerator.services.TrigramController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import static com.torjusam.datastructs.trigramgenerator.gui.LinkSectionController.isValidLink;

/**
 * The section for inputting links
 */
class LinkSection extends VBox {

    private final VBox linkListContainer;
    private final LinkSectionController linkController;

    private static final String defaultUrl = "https://no.wikipedia.org/wiki/Ringenes_herre";

    LinkSection(TrigramController triController) {
        List<String> selectedLinks = new ArrayList<>();
        this.linkListContainer = new VBox(10);
        this.linkController = new LinkSectionController(triController, selectedLinks);

        Label header = new Label("Links");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button addLinkButton = new Button("Add Link");
        addLinkButton.setOnAction(e -> handleAddLink(""));

        this.getChildren().addAll(
                header,
                addLinkButton,
                linkListContainer
        );

        // Initialize with default URL pre-filled
        handleAddLink(defaultUrl);
    }

    /**
     * Adds a link to the list, and displays it on the ui.
     *
     * @param defURL Pre-fill the first textField with the default url
     */
    private void handleAddLink(String defURL) {
        TextField linkInputField = new TextField(defURL);
        linkInputField.setPromptText("Enter a URL");
        linkInputField.setPrefWidth(300);

        Button confirmBtn = new Button("Confirm");
        Button cancelBtn = new Button("Cancel");

        // On "add link": display row with input field, and a confirm/cancel btn
        HBox linkInputRow = new HBox(10);
        linkInputRow.getChildren().addAll(linkInputField, confirmBtn, cancelBtn);
        linkListContainer.getChildren().add(linkInputRow);

        // button logic
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
                // Invalid link
                linkInputField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                linkInputField.setPromptText("Enter a valid link (e.g., https://example.com)");
            }
        });

        cancelBtn.setOnAction(e -> linkListContainer.getChildren().remove(linkInputRow));
    }

    /**
     * Method to display the link in the UI after confirming.
     *
     * @param link The link to be displayed.
     */
    private void displayLink(String link) {
        HBox linkRow = new HBox(10); // Container for link
        Label linkLabel = new Label(link);

        linkRow.getChildren().addAll(linkLabel);
        linkListContainer.getChildren().add(linkRow);
    }
}