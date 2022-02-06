package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.AccountObserver;
import com.example.comics.model.AccountSubject;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.BadgeBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class ReaderProfileControllerG implements AccountObserver {

    @FXML
    private GridPane gpBadges;

    @FXML
    private ImageView ivProPic;

    @FXML
    private Label lblName;

    @FXML
    private Label lblUsername;

    @FXML
    private Button btnEdit;



    @FXML
    public void initialize() {
        btnEdit.setOnAction(event -> openSettings());
        lblName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblUsername.setText(UserLogin.getInstance().getAccount().getLastName());
        ivProPic.setImage(UserLogin.getInstance().getAccount().getProPic());


        AccountSubject.attach(this, "user");

        loadBadges();
    }

    private void openSettings() {
        HomeFactory homeFactory = new HomeFactory();
        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.openSettings();
    }


    public void loadBadges(){

        ResearchController researchController = new ResearchController();
        List<BadgeBean> listOfBadges = researchController.getUserBadges();
        int columns=3;
        int i=1;

        for (int j = 0; j < listOfBadges.size(); j++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("badge.fxml"));
            try {
                VBox card = fxmlLoader.load();
                BadgeCardControllerG badgeCardControllerG = fxmlLoader.getController();
                badgeCardControllerG.setData(listOfBadges.get(j));
                gpBadges.add(card, j%columns, i);
                if(j%columns == columns -1 ){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }




    @Override
    public void update() {
        lblName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblUsername.setText(UserLogin.getInstance().getAccount().getUsername());
    }

}