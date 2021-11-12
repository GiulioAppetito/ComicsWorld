package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class CategoryController {

    @FXML
    public Label lblcategory;


    public void setData(String name){
        lblcategory.setText(name);
    }

    public void back(){
        HomeControllerG homeControllerG = HomeControllerG.getInstance();
        try {
            homeControllerG.openCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
