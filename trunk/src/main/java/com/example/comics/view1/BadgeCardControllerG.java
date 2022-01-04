package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BadgeCardControllerG {

    @FXML
    public ImageView badgeIcon;
    @FXML
    public Label badgeName;

    public void setData(String name, Image icon) {
        badgeName.setText(name);
        badgeIcon.setImage(icon);
    }

}
