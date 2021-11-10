package com.example.comics.view1;


import com.example.comics.model.ComicsCategory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CategoryController {

    @FXML
    private Label categoryName;


    public void setData(ComicsCategory category) {

        categoryName.setText(category.getName());
        //letterLabel.setText(category.getInitial());

    }

}
