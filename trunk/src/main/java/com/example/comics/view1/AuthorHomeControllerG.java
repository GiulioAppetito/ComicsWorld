package com.example.comics.view1;

import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tools.FxmlLoader;

import java.io.IOException;
import java.net.URL;

public class AuthorHomeControllerG {

    @FXML
    private HBox userBox;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button btnCategories;

    @FXML
    private Button btnMySeries;


    @FXML
    private Button btnMyCharacters;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnMyBadges;

    @FXML
    private Button btnTop;

    @FXML
    private ImageView homeIcon;

    @FXML
    private Label lblName;

    String style = ".button2";

    public static AuthorHomeControllerG instance;
    private FeedControllerG feedControllerG;
    private CategoriesControllerG categoriesControllerG;

    public static synchronized AuthorHomeControllerG getInstance() {
        if(instance == null){
            instance = new AuthorHomeControllerG();
        }
        return instance;
    }

    public void init() throws IOException {

        openFeed();

        lblName.setText(UserLogin.getAccount().getFirstName());

        btnMySeries.setOnAction(event -> openMySeries());
        btnCategories.setOnAction(event -> {
            try {
                openCategories();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnSettings.setOnAction(event -> openSettings());
        btnTop.setOnAction(event -> openTop());
        btnMyBadges.setOnAction(event -> openMyBadges());
        btnMyCharacters.setOnAction(event -> openMyCharacters());
        userBox.setOnMouseClicked(event -> openProfile());
        homeIcon.setOnMouseClicked(event -> {
            try {
                openFeed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void openMyCharacters() {

        System.out.println("Clicked my characters");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("authormycharacter");
        mainPane.setCenter(view);

        resetButtons();
        btnMyCharacters.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 20");
    }

    public void openMyBadges() {
        System.out.println("Clicked my badges");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("authormybadges");
        mainPane.setCenter(view);

        resetButtons();
        btnMyBadges.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 20");
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

    public void openMySeries() {
        System.out.println("Clicked my series");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("authormyseries");
        mainPane.setCenter(view);

        resetButtons();
        btnMySeries.setStyle("-fx-background-color: #5DADE2; -fx-background-radius: 20 ");

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

    public void openProfile() {

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("authorprofile");

        mainPane.setCenter(view);
        resetButtons();

    }

    private void resetButtons(){
        btnSettings.setStyle(style);
        btnCategories.setStyle(style);
        btnMySeries.setStyle(style);
        btnMyCharacters.setStyle(style);
        btnTop.setStyle(style);
        btnMyBadges.setStyle(style);
    }
}
