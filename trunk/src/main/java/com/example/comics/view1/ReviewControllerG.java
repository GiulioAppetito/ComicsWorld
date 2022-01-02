package com.example.comics.view1;

import com.example.comics.model.fagioli.ReviewBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ReviewControllerG {

    @FXML
    private ImageView ivProPic;

    @FXML
    private Label lblUsername;

    @FXML
    private Text txtComment;

    public void setData(ReviewBean review) {
        //lblUsername.setText(review.getUsername());
        txtComment.setText(review.getComment());
        lblUsername.setText(review.getUsername());

    }

    public void dummy() {

        System.out.println("Card selected on click : comment");


    }
}
