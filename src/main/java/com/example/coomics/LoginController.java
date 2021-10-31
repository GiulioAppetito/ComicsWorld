package com.example.coomics;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public static LoginController instance;


    private LoginController(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("ComicsWorld");
        stage.setScene(scene);
        stage.show();
    }

    public static LoginController getInstance(Stage stage) throws IOException {
        if(instance==null){
            instance = new LoginController(stage);
        }
        return instance;
    }

    public void login(){

    }
}
