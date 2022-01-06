package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FollowedAuthorCardControllerG {
    @FXML
    private ImageView ivProPic;

    @FXML
    private Label authorUsername;

    public void setData(Image proPic, String username) {
        ivProPic.setImage(proPic);
        authorUsername.setText(username);
    }
}
