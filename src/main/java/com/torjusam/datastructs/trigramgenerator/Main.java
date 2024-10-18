package com.torjusam.datastructs.trigramgenerator;

import com.torjusam.datastructs.trigramgenerator.gui.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Global exception handler
        HandleUncaughtExceptions.setupExceptionHandler();

        // JavaFx stage & Scene
        MainView mainView = new MainView();
        primaryStage.setTitle("Trigram Text Generator");
        primaryStage.setScene(new Scene(mainView, 1100, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}