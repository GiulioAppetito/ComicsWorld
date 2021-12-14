package com.example.comics.view1;

import com.example.comics.AccountObserver;
import com.example.comics.AccountSubject;
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

public class AuthorHomeControllerG  extends HomeControllerG implements AccountObserver {

    @FXML
    private HBox userBox;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button btnCategories;

    @FXML
    private Button btnMySeries;

    @FXML
    private Button btnStatistics;

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

    private static final String STYLE = ".button2";
    private static final String STYLE2 = "-fx-background-color: #5DADE2; -fx-background-radius: 20";

    private static AuthorHomeControllerG instance;

    public static synchronized AuthorHomeControllerG getInstance() {
        if(instance == null){
            instance = new AuthorHomeControllerG();
            AccountSubject.attach(instance);
        }
        return instance;
    }

    public void init() throws IOException {

        openFeed();

        lblName.setText(UserLogin.getAccount().getUsername());

        btnMySeries.setOnAction(event -> openMySeries());
        btnCategories.setOnAction(event -> {
            try {
                openCategories();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnStatistics.setOnAction(event -> openStatistics());
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

    private void openStatistics() {

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("statistics");
        mainPane.setCenter(view);

        resetButtons();
        btnStatistics.setStyle(STYLE2);
    }


    public void changeCenter(Pane pane){
        mainPane.setCenter(pane);
    }


    public void openMyCharacters() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("authormycharacter");
        mainPane.setCenter(view);

        resetButtons();
        btnMyCharacters.setStyle(STYLE2);
    }

    public void openMyBadges() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("authormybadges");
        mainPane.setCenter(view);

        resetButtons();
        btnMyBadges.setStyle(STYLE2);
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

        URL fxmlLocation = HomeControllerG.class.getResource("categories.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(categoriesControllerG);

        Pane view = loader.load();
        categoriesControllerG.init();

        mainPane.setCenter(view);

        resetButtons();
        btnCategories.setStyle(STYLE2);
    }

    public void openMySeries() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("authormyseries");
        mainPane.setCenter(view);

        resetButtons();
        btnMySeries.setStyle(STYLE2);

    }


    public void openFeed() throws IOException {

        FeedControllerG feedControllerG = new FeedControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = HomeControllerG.class.getResource("feed.fxml");
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
        btnSettings.setStyle(STYLE);
        btnCategories.setStyle(STYLE);
        btnMySeries.setStyle(STYLE);
        btnMyCharacters.setStyle(STYLE);
        btnTop.setStyle(STYLE);
        btnMyBadges.setStyle(STYLE);
        btnStatistics.setStyle(STYLE);
    }

    @Override
    public void update() {
        lblName.setText(UserLogin.getAccount().getUsername());
    }
}
