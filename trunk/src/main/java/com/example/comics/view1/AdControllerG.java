package com.example.comics.view1;

import com.example.comics.model.Advertisement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AdControllerG {

    private ImageView comicCover;
    @FXML
    private Label lblTitle;

    public void setData(Advertisement ad) {
        lblTitle.setText(ad.getTitle());

    }


}
