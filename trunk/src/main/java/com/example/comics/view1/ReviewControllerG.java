package com.example.comics.view1;

import com.example.comics.model.fagioli.ReviewBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ReviewControllerG {

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

    @FXML
    private ImageView ivProPic;

    @FXML
    private Label lblUsername;

    @FXML
    private Text txtComment;


    public void setData(ReviewBean review) {
        txtComment.setText(review.getComment());
        lblUsername.setText(review.getUsername());
        switch(review.getRating()){
            case "1":
                imgStar1.setVisible(true);
                imgStar2.setVisible(false);
                imgStar3.setVisible(false);
                imgStar4.setVisible(false);
                imgStar5.setVisible(false);
                break;
            case "2":
                imgStar1.setVisible(true);
                imgStar2.setVisible(true);
                imgStar3.setVisible(false);
                imgStar4.setVisible(false);
                imgStar5.setVisible(false);
                break;
            case "3":
                imgStar1.setVisible(true);
                imgStar2.setVisible(true);
                imgStar3.setVisible(true);
                imgStar4.setVisible(false);
                imgStar5.setVisible(false);
                break;
            case "4":
                imgStar1.setVisible(true);
                imgStar2.setVisible(true);
                imgStar3.setVisible(true);
                imgStar4.setVisible(true);
                imgStar5.setVisible(false);
                break;
            case "5":
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
        }
    }
}
