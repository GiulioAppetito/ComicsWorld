package com.example.coomics.graphiccontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import tools.FxmlLoader;

import java.io.IOException;

public class FormController {

    @FXML
    private BorderPane bpLogin;

    @FXML
    private VBox vbLogin;

    @FXML
    private Button btnBack;

    @FXML
    private VBox vbRegistration;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnRetreive;



    public void initialize(){
        switchToLogin();


    }

    public void switchToLogin(){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("login");
        bpLogin.setCenter(view);
        btnBack.setVisible(false);
        vbRegistration.setVisible(true);
    }

    public void switchToRegistration(){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("registration");
        bpLogin.setCenter(view);
        btnBack.setVisible(true);
        vbRegistration.setVisible(false);

    }








}
