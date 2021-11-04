package com.example.coomics.graphiccontroller;


import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class RegistrationController {


    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldSurname;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private PasswordField textFieldPassword;



    public void cancelForm(){
        textFieldName.setText("");
        textFieldSurname.setText("");
        textFieldUsername.setText("");
        textFieldEmail.setText("");
        textFieldPassword.setText("");
    }


}
