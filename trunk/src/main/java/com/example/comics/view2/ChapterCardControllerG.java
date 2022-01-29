package com.example.comics.view2;

import com.example.comics.model.fagioli.ChapterBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ChapterCardControllerG {
    @FXML
    private ImageView chapterCover;

    @FXML
    private Label chapterName;

    public void setData(ChapterBean chapterBean) {
        chapterName.setText(chapterBean.getTitle());
        chapterCover.setImage(chapterBean.getCover());
    }
}
