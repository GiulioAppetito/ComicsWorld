package com.example.comics.view1;

import com.example.comics.model.fagioli.ChapterBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ChapterItemControllerG {

    @FXML
    private ImageView ivCover;

    @FXML
    private Label lblName;

    @FXML
    private Button btnCheckRead;


    public void setData(ChapterBean chapterBean){
        lblName.setText(chapterBean.getTitle());
        ivCover.setImage(chapterBean.getCover());
        btnCheckRead.setVisible(chapterBean.getRead());
    }
}
