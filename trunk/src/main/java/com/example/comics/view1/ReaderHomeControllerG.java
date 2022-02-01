package com.example.comics.view1;

import com.example.comics.model.AccountObserver;
import com.example.comics.model.AccountSubject;
import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import tools.FxmlLoader;

import java.io.IOException;

public class ReaderHomeControllerG extends HomeControllerG implements AccountObserver{

    @FXML
    private HBox userBox;

    @FXML
    private ImageView proPic;

    @FXML
    private Label lblTitle;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button btnCategories;

    @FXML
    private Button btnFav;

    @FXML
    private Button btnReading;

    @FXML
    private Button btnFollowing;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnToRead;


    @FXML
    private ImageView homeIcon;

    @FXML
    private Label lblName;

    private static final String STYLE = ".button2";
    private static final String STYLE2 = "-fx-background-color: #5DADE2; -fx-background-radius: 20";

    private static ReaderHomeControllerG instance;


    private ReaderHomeControllerG(){
    }

    public static ReaderHomeControllerG getInstance(){
        if(instance==null){
            instance = new ReaderHomeControllerG();
            AccountSubject.attach(instance);
        }
        return instance;
    }

    public void init(){

        openFeed();

        lblTitle.setOnMouseClicked(event -> openFeed());
        lblName.setText(UserLogin.getInstance().getAccount().getUsername());
        proPic.setImage(UserLogin.getInstance().getReader().getProPic());

        btnFav.setOnAction(event -> openFavourites());
        btnFollowing.setOnAction(event-> openFollowing());
        btnCategories.setOnAction(event -> openCategories());
        btnSettings.setOnAction(event -> openSettings());
        btnToRead.setOnAction(event -> openToRead());
        btnReading.setOnAction(event -> openReading());
        userBox.setOnMouseClicked(event -> openProfile());
        homeIcon.setOnMouseClicked(event -> openFeed());

    }

    private void openFollowing() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("followingauthors");
        mainPane.setCenter(view);

        resetButtons();
        btnFollowing.setStyle(STYLE2);
    }

    public void openReading() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("reading");
        mainPane.setCenter(view);

        resetButtons();
        btnReading.setStyle(STYLE2);
    }

    public void openToRead() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("toread");
        mainPane.setCenter(view);

        resetButtons();
        btnToRead.setStyle(STYLE2);
    }

    @Override
    public void changeCenter(Pane pane){
        mainPane.setCenter(pane);
    }

    public void openProfile() {

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("readerprofile");

        mainPane.setCenter(view);
        resetButtons();

    }


    @Override
    public void openSettings() {
        super.openSettings();
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

    public void openFavourites() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favouritecomics");
        mainPane.setCenter(view);

        resetButtons();
        btnFav.setStyle(STYLE2);
    }


    @Override
    public void resetButtons(){
        btnSettings.setStyle(STYLE);
        btnCategories.setStyle(STYLE);
        btnFollowing.setStyle(STYLE);
        btnFav.setStyle(STYLE);
        btnReading.setStyle(STYLE);
        btnToRead.setStyle(STYLE);

    }


    @Override
    public void update() {

        lblName.setText(UserLogin.getInstance().getAccount().getUsername());
        proPic.setImage(UserLogin.getInstance().getAccount().getProPic());
    }

}