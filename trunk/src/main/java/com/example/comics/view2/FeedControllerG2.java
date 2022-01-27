package com.example.comics.view2;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class FeedControllerG2 {


    @FXML
    private VBox boxFeed;

    @FXML
    private VBox boxProfile;

    @FXML
    private VBox vBoxSeries;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnFeed;

    //user profile
    @FXML
    private Label lblDate;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private ImageView propic;

    @FXML
    private Pane paneMenu;

    //reader menu
    @FXML
    private VBox readerMenu;

    @FXML
    private Button btnMyBadges;

    @FXML
    private Button btnReading;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnToRead;

    @FXML
    private Button btnFav;

    @FXML
    private Button btnFollowing;

    //author menu
    @FXML
    private VBox authorMenu;

    @FXML
    private Button btnMySeries;

    @FXML
    private Button btnStatistics;

    //favourites
    @FXML
    private VBox vBoxFav;

    @FXML
    private VBox vBoxFavSeries;

    //toread
    @FXML
    private VBox vBoxToRead;

    @FXML
    private VBox vBoxToReadSeries;

    //reading
    @FXML
    private VBox vBoxReading;

    @FXML
    private VBox vBoxReadingSeries;


    private boolean openMenu = true;
    private static FeedControllerG2 instance;
    private static List<SeriesBean> latestSeries;

    private FeedControllerG2(){
        loadLatestSeries();
    }

    public static synchronized FeedControllerG2 getInstance(){
        if(instance == null){
            instance = new FeedControllerG2();
        }
        return instance;
    }

    public static void loadLatestSeries(){
        ResearchController researchController = new ResearchController();
        latestSeries = researchController.getLatestSeries();
    }


    public void init() {

        openFeed();
        openMenu();
        initProfile();
        initFav();
        initToRead();
        initReading();

        btnProfile.setOnAction(event -> openProfile());
        btnFeed.setOnAction(event -> openFeed());
        btnMenu.setOnAction(event -> openMenu());
        btnSettings.setOnAction(event -> openSettings());

        if(UserLogin.getInstance().getAccount().getRole().equals("reader")){
            readerMenu();
        }else{
            authorMenu();
        }

        //reader menu
        btnFav.setOnAction(event -> openFav());
        btnFollowing.setOnAction(event -> openFollowing());
        btnReading.setOnAction(event -> openReading());
        btnToRead.setOnAction(event -> openToRead());

        //author menu
        btnStatistics.setOnAction(event -> openStats());
        btnMySeries.setOnAction(event -> openMySeries());

        displayListOfSeries(latestSeries, vBoxSeries);

    }

    private void initFav() {
        ResearchController researchController = new ResearchController();
        List<SeriesBean> favouriteSeries = researchController.getFavouriteSeries();
        displayListOfSeries(favouriteSeries, vBoxFavSeries);
    }
    private void initToRead(){
        ResearchController researchController = new ResearchController();
        List<SeriesBean> toReadSeries = researchController.getToReadSeries();
        displayListOfSeries(toReadSeries, vBoxToReadSeries);
    }
    private void initReading(){
        ResearchController researchController = new ResearchController();
        List<SeriesBean> readingSeries = researchController.getReadingSeries();
        displayListOfSeries(readingSeries, vBoxReadingSeries);
    }

    private void displayListOfSeries(List<SeriesBean> series, VBox box){

        int size = series.size();
        for(int j=0; j<size; j++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("seriesCard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                SeriesCardControllerG cardController = fxmlLoader.getController();
                cardController.setData(series.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> {
                    try {
                        openSerie(series.get(finalJ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                box.getChildren().add(card);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //reader
    private void openFav() {
        openMenu();
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(true);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
    }
    private void openFollowing() {
    }
    private void openReading() {
        openMenu();
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(true);
    }
    private void openToRead() {
        openMenu();
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(true);
        vBoxReading.setVisible(false);
    }
    //author
    private void openStats(){
    }
    private void openMySeries(){
    }

    private void openSettings() {
        //TO-DO
    }

    public void openProfile(){
        boxProfile.setVisible(true);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
    }

    public void openFeed(){
        boxProfile.setVisible(false);
        boxFeed.setVisible(true);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
    }

    public void openSerie(SeriesBean seriesBean) throws IOException {}

    private void initProfile(){
        lblFirstName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblLastName.setText(UserLogin.getInstance().getAccount().getLastName());
        propic.setImage(UserLogin.getInstance().getAccount().getProPic());
    }

    private void openMenu(){
        if(openMenu){
            paneMenu.setVisible(false);
            openMenu = false;
            return;
        }
        paneMenu.setVisible(true);
        openMenu = true;
    }

    private void readerMenu(){
        readerMenu.setVisible(true);
        authorMenu.setVisible(false);
    }

    private void authorMenu(){
        readerMenu.setVisible(false);
        authorMenu.setVisible(true);
    }

}
