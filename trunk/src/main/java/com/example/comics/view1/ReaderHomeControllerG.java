package com.example.comics.view1;

import com.example.comics.AccountObserver;
import com.example.comics.AccountSubject;
import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import tools.FxmlLoader;

import java.io.IOException;
import java.net.URL;

public class ReaderHomeControllerG implements AccountObserver {

    @FXML
    private HBox userBox;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button btnCategories;

    @FXML
    private Button btnFav;


    @FXML
    private Button btnReading;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnToRead;

    @FXML
    private Button btnTop;

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

    public void init() throws IOException {

        openFeed();

        lblName.setText(UserLogin.getAccount().getFirstName());

        btnFav.setOnAction(event -> openFavourites());
        btnCategories.setOnAction(event -> {
            try {
                openCategories();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnSettings.setOnAction(event -> openSettings());
        btnTop.setOnAction(event -> openTop());
        btnToRead.setOnAction(event -> openToRead());
        btnReading.setOnAction(event -> openReading());
        userBox.setOnMouseClicked(event -> openProfile());
        homeIcon.setOnMouseClicked(event -> {
            try {
                openFeed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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

    public void changeCenter(Pane pane){
        mainPane.setCenter(pane);
    }


    public void openProfile() {

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("readerprofile");

        mainPane.setCenter(view);
        resetButtons();

    }

    public void openTop(){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("topcomics");
        mainPane.setCenter(view);

        resetButtons();
        btnTop.setStyle(STYLE2);
    }

    public void openSettings() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("settings");
        mainPane.setCenter(view);

        resetButtons();
        btnSettings.setStyle(STYLE2);

    }

    public void openCategories() throws IOException {

        CategoriesControllerG categoriesControllerG = new CategoriesControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = FeedControllerG.class.getResource("categories.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(categoriesControllerG);

        Pane view = loader.load();
        categoriesControllerG.init();

        mainPane.setCenter(view);

        resetButtons();
        btnCategories.setStyle(STYLE2);
    }

    public void openFavourites() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favourites");
        mainPane.setCenter(view);

        resetButtons();
        btnFav.setStyle(STYLE2);

    }


    public void openFeed() throws IOException {

        FeedControllerG feedControllerG = new FeedControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = FeedControllerG.class.getResource("feed.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(feedControllerG);

        Pane view = loader.load();
        feedControllerG.init();

        mainPane.setCenter(view);

        resetButtons();
    }

    private void resetButtons(){
        btnSettings.setStyle(STYLE);
        btnCategories.setStyle(STYLE);
        btnFav.setStyle(STYLE);
        btnReading.setStyle(STYLE);
        btnTop.setStyle(STYLE);
        btnToRead.setStyle(STYLE);

    }


    @Override
    public void update() {
        lblName.setText(UserLogin.getAccount().getUsername());
    }
}