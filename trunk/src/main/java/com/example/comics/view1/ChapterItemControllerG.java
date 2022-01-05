package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ChapterItemControllerG {
    @FXML
    private ImageView ivCover;

    @FXML
    private Label lblName;

    @FXML
    private Text lblSeries;

    public void setData(String chaptersTitle, String chaptersSeries, Image chaptersCover){
        lblName.setText(chaptersTitle);
        lblSeries.setText(chaptersSeries);
        ivCover.setImage(chaptersCover);

    }
}
