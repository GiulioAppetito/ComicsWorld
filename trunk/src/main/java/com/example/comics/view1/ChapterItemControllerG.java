package com.example.comics.view1;

import com.example.comics.model.fagioli.ChapterBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button btnCheckRead;


    public void setData(ChapterBean chapterBean){
        lblName.setText(chapterBean.getTitle());
        ivCover.setImage(chapterBean.getCover());
        if(chapterBean.getRead()){
            btnCheckRead.setVisible(true);
        }else{
            btnCheckRead.setVisible(false);
        }
    }
}
