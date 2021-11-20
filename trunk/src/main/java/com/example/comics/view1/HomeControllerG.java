package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import tools.FxmlLoader;

import java.io.IOException;
import java.net.URL;

public class HomeControllerG {

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

    String STYLE = ".button";

    public static HomeControllerG instance;
    public FeedControllerG feedControllerG;
    public CategoriesControllerG categoriesControllerG;


    private HomeControllerG(){
    }

    public static HomeControllerG getInstance(){
        if(instance==null){
            instance = new HomeControllerG();
        }
        return instance;
    }

    public void init() throws IOException {

        openFeed();

        btnFav.setOnAction(event -> openFavourites());
        btnCategories.setOnAction(event -> {
            try {
                openCategories();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnSettings.setOnAction(event -> openSettings());
        userBox.setOnMouseClicked(event -> openProfile());
        homeIcon.setOnMouseClicked(event -> {
            try {
                openFeed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void changeCenter(Pane pane){
        mainPane.setCenter(pane);
    }


    public void openProfile() {
        System.out.println("Clicked profile");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("profile");
        mainPane.setCenter(view);

        resetButtons();
    }

    public void openSettings() {
        System.out.println("Clicked settings");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("settings");
        mainPane.setCenter(view);

        resetButtons();
        btnSettings.setStyle("-fx-background-color: #5DADE2; ");

    }

    public void openCategories() throws IOException {

        categoriesControllerG = new CategoriesControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = FeedControllerG.class.getResource("categories.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(categoriesControllerG);

        Pane view = loader.load();
        categoriesControllerG.init();

        mainPane.setCenter(view);

        resetButtons();
        btnCategories.setStyle("-fx-background-color: #5DADE2; ");
    }

    public void openFavourites() {
        System.out.println("Clicked favourites");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favourites");
        mainPane.setCenter(view);

        resetButtons();
        btnFav.setStyle("-fx-background-color: #5DADE2; ");

    }

    public void openFeed() throws IOException {

        feedControllerG = new FeedControllerG();
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


}