package com.example.comics.view2;

import com.example.comics.model.fagioli.AuthorBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AuthorCardControllerG {

    @FXML
    private Label authorName;

    @FXML
    private ImageView authorProPic;

    public void setData(AuthorBean authorBean){
        authorName.setText(authorBean.getUsername());
        authorProPic.setImage(authorBean.getProPic());
    }
}
