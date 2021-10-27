package com.example.coomics;


import com.example.coomics.model.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CategoryController {

    @FXML
    private HBox categoryLayout;

    @FXML
    private ImageView categoryImg;

    @FXML
    private Label categoryName;

    @FXML
    private Label letterLabel;

    public void setData(Category category) {

        categoryName.setText(category.getName());
        letterLabel.setText(category.getInitial());

    }

    public void dummy() {

        System.out.println("Category selected on click : " + categoryName.getText());

    }
}
