package com.example.comics.view1;

import com.example.comics.controller.StatisticsController;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CardControllerG {

    @FXML
    public ImageView comicCover;
    @FXML
    public Label comicName;
    @FXML
    private ImageView imgStar1;
    @FXML
    private ImageView imgStar2;
    @FXML
    private ImageView imgStar3;
    @FXML
    private ImageView imgStar4;
    @FXML
    private ImageView imgStar5;

    public void setData(SeriesBean seriesBean) {
        comicName.setText(seriesBean.getTitle());
        comicCover.setImage(seriesBean.getCover());

        StatisticsController statisticsController = new StatisticsController();
        switch ((int) statisticsController.seriesAverageRating(seriesBean)){

            case 1:
                imgStar1.setVisible(true);
                imgStar2.setVisible(false);
                imgStar3.setVisible(false);
                imgStar4.setVisible(false);
                imgStar5.setVisible(false);
                break;
            case 2:
                imgStar1.setVisible(true);
                imgStar2.setVisible(true);
                imgStar3.setVisible(false);
                imgStar4.setVisible(false);
                imgStar5.setVisible(false);
                break;
            case 3:
                imgStar1.setVisible(true);
                imgStar2.setVisible(true);
                imgStar3.setVisible(true);
                imgStar4.setVisible(false);
                imgStar5.setVisible(false);
                break;
            case 4:
                imgStar1.setVisible(true);
                imgStar2.setVisible(true);
                imgStar3.setVisible(true);
                imgStar4.setVisible(true);
                imgStar5.setVisible(false);
                break;
            case 5:
                imgStar1.setVisible(true);
                imgStar2.setVisible(true);
                imgStar3.setVisible(true);
                imgStar4.setVisible(true);
                imgStar5.setVisible(true);
                break;
            default:
                imgStar1.setVisible(false);
                imgStar2.setVisible(false);
                imgStar3.setVisible(false);
                imgStar4.setVisible(false);
                imgStar5.setVisible(false);
                break;
        }
    }

    
}
