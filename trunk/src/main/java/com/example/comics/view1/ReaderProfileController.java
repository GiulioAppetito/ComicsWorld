package com.example.comics.view1;

import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ReaderProfileController {

    @FXML
    public Button btnConverter;
    @FXML
    public Label lblName;
    @FXML
    public Label lblUsername;
    @FXML
    private Button btnEdit;

    @FXML
    public void initialize(){
        lblName.setText(UserLogin.getAccount().getFirstName());
        lblUsername.setText(UserLogin.getAccount().getLastName());
        btnEdit.setOnAction(actionEvent -> edit());
    }

    public void init(){
        btnEdit.setOnAction(actionEvent -> edit());
    }

    public void edit(){

        HomeControllerG.getInstance().openSettings();

    }



}
