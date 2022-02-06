package com.example.comics.view1;

import com.example.comics.model.AccountObserver;
import com.example.comics.model.AccountSubject;
import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tools.FxmlLoader;


import java.io.IOException;

public class AuthorHomeControllerG  extends HomeControllerG implements AccountObserver {

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label lblTitle;

    @FXML
    private ImageView homeIcon;

    @FXML
    private HBox userBox;

    @FXML
    private Label lblName;

    @FXML
    private ImageView ivUpperProPic;

    @FXML
    private Button btnCategories;

    @FXML
    private Button btnMySeries;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnStatistics;

    @FXML
    private ImageView smallProPic;



    private static final String STYLE = ".button2";
    private static final String STYLE2 = "-fx-background-color: #5DADE2; -fx-background-radius: 20";

    private static AuthorHomeControllerG instance;

    public static synchronized AuthorHomeControllerG getInstance() {
        if(instance == null){
            instance = new AuthorHomeControllerG();
            AccountSubject.attach(instance, "user");
        }
        return instance;
    }



    public void init(){

        openFeed();

        lblTitle.setOnMouseClicked(event -> openFeed());
        smallProPic.setImage(UserLogin.getInstance().getAccount().getProPic());
        lblName.setText(UserLogin.getInstance().getAccount().getUsername());

        btnMySeries.setOnAction(event -> openMySeries());
        btnCategories.setOnAction(event -> openCategories());
        btnStatistics.setOnAction(event -> openStatistics());

        btnSettings.setOnAction(event -> openSettings());
        userBox.setOnMouseClicked(event -> openProfile());
        homeIcon.setOnMouseClicked(event -> openFeed());

    }

    private void openStatistics() {

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("statistics");
        mainPane.setCenter(view);

        resetButtons();
        btnStatistics.setStyle(STYLE2);
    }


    @Override
    public void changeCenter(Pane pane){
        mainPane.setCenter(pane);
    }


    public void openTop(){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("topcomics");
        mainPane.setCenter(view);

        resetButtons();
    }

    @Override
    public void openSettings() {

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("settings");
        mainPane.setCenter(view);

        resetButtons();
        btnSettings.setStyle(STYLE2);

    }

    @Override
    public void openCategories(){
        try {
            super.openCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnCategories.setStyle(STYLE2);
    }

    public void openMySeries() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("authormyseries");
        mainPane.setCenter(view);

        resetButtons();
        btnMySeries.setStyle(STYLE2);

    }

    public void openProfile() {

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("authorprofile");

        mainPane.setCenter(view);
        resetButtons();

    }

    @Override
    public void resetButtons(){
        btnSettings.setStyle(STYLE);
        btnCategories.setStyle(STYLE);
        btnMySeries.setStyle(STYLE);
        btnStatistics.setStyle(STYLE);
    }

    @Override
    public void update() {
        lblName.setText(UserLogin.getInstance().getAccount().getUsername());
        smallProPic.setImage(UserLogin.getInstance().getAccount().getProPic());
    }
}
