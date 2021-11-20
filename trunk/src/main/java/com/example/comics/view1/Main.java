package com.example.comics.view1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        LoginControllerG loginControllerG = new LoginControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = LoginControllerG.class.getResource("login.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(loginControllerG);

        Scene scene = new Scene(loader.load());
        loginControllerG.init();

        stage.setTitle("ComicsWorld");
        stage.setScene(scene);
        stage.show();
        int k = 0;
        int a = 1;


        //senti ora andrà ritoccato tutto perché:
        //non possiamo/dobbiamo più associare i controllori e i metodi da scene builder, ma metterli a mano
        //quindi per i bottoni già esistenti etc, dobbiamo farlo alla creazione delle istanze nei costruttori di ognuno
        //good luck a te del futuro
        //però funziona capisci sarà tutto singleton

    }

    public static void main(String[] args) {
        launch();
    }

}