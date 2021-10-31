package com.example.coomics;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public TextField tfEmail;

    public void initialize(){

    }

    public void clickLogin(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("ComicsWorld");
        stage.setScene(scene);
        stage.show();

        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();

    }
}
