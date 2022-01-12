package com.example.comics.view1;

import com.example.comics.model.fagioli.BadgeBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BadgeCardControllerG {

    @FXML
    private ImageView badgeIcon;
    @FXML
    private Label badgeName;

    public void setData(BadgeBean badgeBean) {
        badgeName.setText(badgeBean.getName());
        badgeIcon.setImage(badgeBean.getIcon());
    }

}
