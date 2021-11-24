package com.example.comics.view1;

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
import java.util.Objects;

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
    private Button btnConverter;

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

    String style = ".button2";


    private static HomeControllerG instance;
    private FeedControllerG feedControllerG;
    private CategoriesControllerG categoriesControllerG;


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
        btnConverter.setOnAction(event-> openConverter());
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

        System.out.println("Clicked reading");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("reading");
        mainPane.setCenter(view);

        resetButtons();
        btnReading.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 20");
    }

    public void openToRead() {
        System.out.println("Clicked to read");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("toread");
        mainPane.setCenter(view);

        resetButtons();
        btnToRead.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 20");
    }

    public void changeCenter(Pane pane){
        mainPane.setCenter(pane);
    }


    public void openProfile() {

        String role = UserLogin.getAccount().getRole();
        Pane view;
        System.out.println("Clicked profile: " + role);
        FxmlLoader object = new FxmlLoader();
        if(Objects.equals(role, "reader")){
            view = object.getPage("readerprofile");
        }
        else{
            view = object.getPage("authorprofile");
        }
        mainPane.setCenter(view);

        resetButtons();

    }

    public void openTop(){

        System.out.println("Clicked top comics");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("topcomics");
        mainPane.setCenter(view);

        resetButtons();
        btnTop.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 20");
    }

    public void openSettings() {

        System.out.println("Clicked settings");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("settings");
        mainPane.setCenter(view);

        resetButtons();
        btnSettings.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 20");

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
        btnCategories.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 20");
    }

    public void openFavourites() {
        System.out.println("Clicked favourites");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favourites");
        mainPane.setCenter(view);

        resetButtons();
        btnFav.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 20 ");

    }

    public void openConverter() {
        System.out.println("Clicked converter");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("converter");
        mainPane.setCenter(view);

        resetButtons();
        btnConverter.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 20 ");

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
        btnSettings.setStyle(style);
        btnCategories.setStyle(style);
        btnFav.setStyle(style);
        btnReading.setStyle(style);
        btnTop.setStyle(style);
        btnToRead.setStyle(style);
        btnConverter.setStyle(style);
    }


}