package com.example.coomics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tools.FxmlLoader;

import java.io.IOException;

public class LoginController {

    @FXML
    private VBox vbLogin;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfPassword;

    public void clickLogin(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("ComicsWorld");
        stage.setScene(scene);
        stage.show();

        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();

    }

    public void clickCancel(){
        tfEmail.setText("");
        tfPassword.setText("");
    }




}
