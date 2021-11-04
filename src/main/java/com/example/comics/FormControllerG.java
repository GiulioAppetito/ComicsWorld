package com.example.comics;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tools.FxmlLoader;

public class FormControllerG {

    @FXML
    private BorderPane bpLogin;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRegister;

    @FXML
    private VBox vbRegistration;


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