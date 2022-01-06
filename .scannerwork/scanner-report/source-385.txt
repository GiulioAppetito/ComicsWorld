package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VCardControllerG {

    @FXML
    public ImageView comicCover;
    @FXML
    public Label comicName;

    public void setData(String name,Image cover) {
        comicName.setText(name);
        comicCover.setImage(cover);
    }

    
}
