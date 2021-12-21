package com.example.comics.view1;

import com.example.comics.model.Levels;
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

    }

    public static void main(String[] args) {
        launch();
    }

}