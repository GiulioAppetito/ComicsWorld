package com.example.comics.view1;

import com.example.comics.view1.HomeControllerG;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ProfileController {

    @FXML
    private Button btnEdit;

    public void init(){
        btnEdit.setOnAction(actionEvent -> edit());
    }

    //HomeControllerG home = HomeControllerG.getInstance();
    public void edit(){

        HomeControllerG.getInstance().openSettings();

    }



}
