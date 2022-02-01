package com.example.comics.view2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main2 extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        LoginControllerG2 loginControllerG2 = new LoginControllerG2();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = LoginControllerG2.class.getResource("login2.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(loginControllerG2);

        Scene scene = new Scene(loader.load());
        loginControllerG2.init();

        stage.setTitle("ComicsWorld_Mobile");
        stage.setScene(scene);
        stage.setMaxHeight(705);
        stage.setMaxWidth(425);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}
