package com.example.comics.view2;

import com.example.comics.controller.StatisticsController;
import com.example.comics.model.fagioli.ReviewBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class ReviewCardControllerG {

    @FXML
    private Label comment;

    @FXML
    private ImageView propic;

    @FXML
    private Label username;

    @FXML
    private ProgressBar ratingBar;

    @FXML
    private Label lblRating;

    public void setData(ReviewBean reviewBean) {
        username.setText(reviewBean.getAccount().getUsername());
        propic.setImage(reviewBean.getAccount().getProPic());
        comment.setText(reviewBean.getComment());
        ratingBar.setProgress(reviewBean.getRating()/5f);
        lblRating.setText(reviewBean.getRating() * 20 + "%");

    }

}
