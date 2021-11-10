package com.example.comics;

import com.example.comics.model.Comic;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class VCardController {

    @FXML
    public ImageView comicCover;
    @FXML
    public Label comicName;

    public void setData(String name) {

        //comicCover = new ImageView("/src/main/resources/img/spidey.png");
        comicName.setText(name);

    }
    
}
