package com.example.comics.view2;

import com.example.comics.controller.StatisticsController;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class SeriesCardControllerG {

    @FXML
    private ImageView comicCover;

    @FXML
    private Label comicName;

    @FXML
    private ProgressBar ratingBar;

    public void setData(SeriesBean seriesBean){
        comicName.setText(seriesBean.getTitle());
        comicCover.setImage(seriesBean.getCover());
        StatisticsController statisticsController = new StatisticsController();
        System.out.println("[SeriesCard2] Rating of "+seriesBean.getTitle()+" : "+statisticsController.seriesAverageRating(seriesBean));

        ratingBar.setProgress(statisticsController.seriesAverageRating(seriesBean)/5);
    }

}
