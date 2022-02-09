package com.example.comics.view1;

import com.example.comics.model.fagioli.AuthorBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class FollowedAuthorCardControllerG {
    @FXML
    private ImageView ivProPic;

    @FXML
    private Label authorUsername;

    public void setData(AuthorBean authorBean) {
        ivProPic.setImage(authorBean.getProPic());
        authorUsername.setText(authorBean.getUsername());
    }
}
