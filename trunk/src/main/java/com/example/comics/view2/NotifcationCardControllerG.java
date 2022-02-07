package com.example.comics.view2;

import com.example.comics.model.fagioli.BadgeBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class NotifcationCardControllerG {

    @FXML
    private ImageView badgeCover;

    @FXML
    private Label badgeName;

    @FXML
    private Label messageLabel;

    public void setData(String message, BadgeBean badgeBean){
        badgeCover.setImage(badgeBean.getIcon());
        badgeName.setText(badgeBean.getName());
        messageLabel.setText(message);
    }


    public void setData(String message){
        badgeCover.setVisible(false);
        badgeName.setVisible(false);
        messageLabel.setText(message);
    }


}
