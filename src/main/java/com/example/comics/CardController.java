package com.example.comics;

import com.example.comics.model.Comic;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CardController {

    @FXML
    public ImageView comicCover1;
    public ImageView comicCover;
    public Label comicName;

    public void setData(Comic comic) {
        //Image image = new Image(getClass().getResourceAsStream(comic.getImageSrc()));
        //comicCover.setImage(image);
        comicName.setText(comic.getName());

    }

    public void dummy() {

        System.out.println("Card selected on click : " + comicName.getText());


    }
    
}
