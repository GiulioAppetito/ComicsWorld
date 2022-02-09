package com.example.comics.view2;

import com.example.comics.model.fagioli.BadgeBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class BadgeCardControllerG2 {

    @FXML
    private ImageView badgeIcon;

    @FXML
    private Label badgeName;

    public void setData(BadgeBean badgeBean){
        badgeIcon.setImage(badgeBean.getIcon());
        badgeName.setText(badgeBean.getName());
    }
}
