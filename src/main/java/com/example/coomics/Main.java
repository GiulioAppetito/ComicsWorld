package com.example.coomics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginController.getInstance(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}