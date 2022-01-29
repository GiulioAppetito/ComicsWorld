package com.example.comics.view2;

import com.example.comics.controller.ResearchController;
import com.example.comics.controller.StatisticsController;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeedControllerG2 {

    @FXML
    private VBox vBoxSerie;


    @FXML
    private VBox boxNoChapters;

    @FXML
    private Button author;

    @FXML
    private Button btnLikeSerie;

    @FXML
    private Button btnReadingSerie;

    @FXML
    private Button btnToReadSerie;

    @FXML
    private VBox vBoxChapters;

    @FXML
    private ImageView cover;

    @FXML
    private Label title;

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

    //MySeries
    @FXML
    private Button btnAddNewSeries;

    @FXML
    private VBox vBoxMySeries;

    @FXML
    private VBox vBoxMySeriesSeries;

    //following
    @FXML
    private VBox vBoxFollowing;

    @FXML
    private VBox vBoxFollowingAuthors;

    //statistics
    @FXML
    private VBox vBoxStats;
    @FXML
    private PieChart ratingChart;
    @FXML
    private ChoiceBox<String> seriesPicker;

    //settings

    @FXML
    private VBox vBoxSettings;


    private static int previous_size = 0;
    private boolean openMenu = true;
    private static FeedControllerG2 instance;
    private static List<SeriesBean> latestSeries;
    private static List<SeriesBean> mySeries;

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


        btnProfile.setOnAction(event -> openProfile());
        btnFeed.setOnAction(event -> openFeed());
        btnMenu.setOnAction(event -> openMenu());
        btnSettings.setOnAction(event -> openSettings());

        if(UserLogin.getInstance().getAccount().getRole().equals("reader")){
            readerMenu();
            initFav();
            initToRead();
            initReading();
            initFollowing();
        }else{
            authorMenu();
            initMySeries();
            initStats();
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
    private void initFollowing(){
        ResearchController researchController = new ResearchController();
        List<AuthorBean> following = researchController.getFollowedAuthors();

        for(int j=0; j<following.size(); j++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("authorCard.fxml"));
            try {

                VBox card = fxmlLoader.load();
                AuthorCardControllerG cardController = fxmlLoader.getController();
                cardController.setData(following.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> openAuthor(following.get(finalJ)));

                vBoxFollowingAuthors.getChildren().add(card);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initMySeries(){
        ResearchController researchController = new ResearchController();
        mySeries = researchController.getPublishedSeries();
        displayListOfSeries(mySeries, vBoxMySeriesSeries);
    }
    private void initStats(){
        List<String> myTitles = new ArrayList<>();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        PieChart.Data starting_data = new PieChart.Data("", 100);
        pieChartData.add(starting_data);
        for(SeriesBean seriesBean : mySeries){
            myTitles.add(seriesBean.getTitle());
            StatisticsController statisticsController = new StatisticsController();
            Float rating = statisticsController.seriesAverageRating(seriesBean);
            PieChart.Data data = new PieChart.Data(seriesBean.getTitle(), rating);
            pieChartData.add(data);
        }
        seriesPicker.getItems().setAll(myTitles);

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

    private void displayListOfChapters(List<ChapterBean> chapters, VBox box){

        int actual_size = chapters.size();
        for(int i=0; i<previous_size; i++){
            box.getChildren().remove(0);
        }
        previous_size = actual_size;
        if(actual_size==0){
            boxNoChapters.setVisible(true);
        }else{
            boxNoChapters.setVisible(false);
        }
        for(int j=0; j<actual_size; j++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("chapterCard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                ChapterCardControllerG cardController = fxmlLoader.getController();
                cardController.setData(chapters.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> openChapter(chapters.get(finalJ)));
                box.getChildren().add(card);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openChapter(ChapterBean chapterBean) {
        vBoxSeries.setVisible(false);
    }

    //reader
    private void openFav() {
        openMenu();
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(true);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
        vBoxMySeries.setVisible(false);
        vBoxFollowing.setVisible(false);
        vBoxSeries.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxSettings.setVisible(false);
        vBoxSerie.setVisible(false);
    }
    private void openFollowing() {
        openMenu();
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
        vBoxMySeries.setVisible(false);
        vBoxFollowing.setVisible(true);
        vBoxStats.setVisible(false);
        vBoxSettings.setVisible(false);
        vBoxSeries.setVisible(false);
        vBoxSerie.setVisible(false);
    }
    private void openReading() {
        openMenu();
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxSerie.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(true);
        vBoxMySeries.setVisible(false);
        vBoxFollowing.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxSettings.setVisible(false);
        vBoxSeries.setVisible(false);
    }
    private void openToRead() {
        openMenu();
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(true);
        vBoxReading.setVisible(false);
        vBoxMySeries.setVisible(false);
        vBoxFollowing.setVisible(false);
        vBoxSeries.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxSettings.setVisible(false);
        vBoxSerie.setVisible(false);
    }
    //author
    private void openStats(){
        openMenu();
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
        vBoxMySeries.setVisible(false);
        vBoxSerie.setVisible(false);
        vBoxFollowing.setVisible(false);
        vBoxStats.setVisible(true);
        vBoxSettings.setVisible(false);
        vBoxSeries.setVisible(false);
    }
    private void openMySeries(){
        openMenu();
        boxProfile.setVisible(false);
        vBoxSeries.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
        vBoxMySeries.setVisible(true);
        vBoxFollowing.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxSettings.setVisible(false);
        vBoxSerie.setVisible(false);
    }
    //both
    private void openSettings() {
        openMenu();
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxSeries.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
        vBoxMySeries.setVisible(false);
        vBoxFollowing.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxSettings.setVisible(true);
        vBoxSerie.setVisible(false);
    }
    public void openProfile(){
        boxProfile.setVisible(true);
        vBoxSeries.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
        vBoxMySeries.setVisible(false);
        vBoxFollowing.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxSettings.setVisible(false);
        vBoxSerie.setVisible(false);
    }
    public void openFeed(){
        vBoxSeries.setVisible(true);
        boxProfile.setVisible(false);
        boxFeed.setVisible(true);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
        vBoxMySeries.setVisible(false);
        vBoxFollowing.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxSettings.setVisible(false);
        vBoxSerie.setVisible(false);
    }

    public void openSerie(SeriesBean seriesBean) throws IOException {

        vBoxSeries.setVisible(false);
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
        vBoxMySeries.setVisible(false);
        vBoxFollowing.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxSerie.setVisible(true);
        vBoxSettings.setVisible(false);

        title.setText(seriesBean.getTitle());
        cover.setImage(seriesBean.getCover());
        author.setText(seriesBean.getAuthor().getUsername());
        author.setOnAction(event -> openAuthor(seriesBean.getAuthor()));

        displayListOfChapters(seriesBean.getChapters(), vBoxChapters);

        btnLikeSerie.setOnAction(event -> likeSeries());
        btnToReadSerie.setOnAction(event -> addToRead());
        btnReadingSerie.setOnAction(event -> addToReading());
    }
    private void likeSeries() {
    }
    private void addToRead() {
    }
    private void addToReading() {
    }

    private void openAuthor(AuthorBean authorBean) {}

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
