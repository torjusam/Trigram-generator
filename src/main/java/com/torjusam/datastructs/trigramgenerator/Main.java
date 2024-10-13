package com.torjusam.datastructs.trigramgenerator;

import com.torjusam.datastructs.trigramgenerator.gui.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialize MainView
        MainView mainView = new MainView();

        // Set up the stage and scene
        Scene scene = new Scene(mainView, 400, 300);
        primaryStage.setTitle("Trigram Text Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}