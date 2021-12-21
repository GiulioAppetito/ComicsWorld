package com.example.comics.view1;

import com.example.comics.model.Chapter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ChapterItemController {
    @FXML
    private ImageView ivCover;

    @FXML
    private Label lblName;

    @FXML
    private Text lblSeries;

    public void setData(String chaptersTitle, String chaptersSeries){
        lblName.setText(chaptersTitle);
        lblSeries.setText(chaptersSeries);

    }
}
