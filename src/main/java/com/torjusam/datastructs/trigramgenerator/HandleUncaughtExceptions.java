package com.torjusam.datastructs.trigramgenerator;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class HandleUncaughtExceptions {

    public static void setupExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            Platform.runLater(() -> {
                showErrorAlert("An uncaught error occurred", throwable.getMessage());
            });
        });
    }

    // Utility method to show error alerts
    public static void showErrorAlert(String header, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(header);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
