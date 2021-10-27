package com.example.coomics;


import com.example.coomics.model.ComicsCategory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CategoryController {

    @FXML
    private Label categoryName;


    public void setData(ComicsCategory category) {

        categoryName.setText(category.getName());
        //letterLabel.setText(category.getInitial());

    }

}
