package com.example.comics;

import com.example.comics.model.Advertisement;
import com.example.comics.model.Comic;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AdController {

    @FXML
    private Label lblTitle;

    public void setData(Advertisement ad) {
        //Image image = new Image(getClass().getResourceAsStream(comic.getImageSrc()));
        //comicCover.setImage(image);
        lblTitle.setText(ad.getTitle());

    }

    public void dummy() {

        System.out.println("Card selected on click : " + lblTitle.getText());


    }


}
