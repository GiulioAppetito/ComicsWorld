package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CategoryCardController {

    @FXML
    public Label lblname;

    public void setData(String categoryName){
        lblname.setText(categoryName);
    }
}
