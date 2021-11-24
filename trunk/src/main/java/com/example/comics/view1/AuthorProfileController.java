package com.example.comics.view1;

import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AuthorProfileController {

    @FXML
    private Button btnEdit;

    @FXML
    public void initialize(){
        lblName.setText(UserLogin.getAccount().getFirstName());
        lblUsername.setText(UserLogin.getAccount().getLastName());
    }

    public void init(){
        btnEdit.setOnAction(actionEvent -> edit());
    }

    public void edit(){

        HomeControllerG.getInstance().openSettings();

    }



}
