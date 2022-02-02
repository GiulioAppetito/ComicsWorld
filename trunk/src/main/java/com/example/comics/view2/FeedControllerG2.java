package com.example.comics.view2;

import com.example.comics.controller.*;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.ReviewBean;
import com.example.comics.model.fagioli.SeriesBean;
import com.example.comics.view1.beans.ReviewBean1;
import com.example.comics.view2.beans.ReviewBean2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeedControllerG2 {

    @FXML
    private Button author;

    @FXML
    private VBox authorMenu;

    @FXML
    private ImageView authorPropic;

    @FXML
    private Label authorusername;

    @FXML
    private VBox boxFeed;

    @FXML
    private VBox boxNoChapters;

    @FXML
    private VBox boxNoReviews;

    @FXML
    private VBox boxNoSeries;

    @FXML
    private VBox boxProfile;

    @FXML
    private Button btnAddNewSeries;

    @FXML
    private Button btnBackFromChaptee;

    @FXML
    private Button btnBuy;

    @FXML
    private Button btnFav;

    @FXML
    private Button btnFeed;

    @FXML
    private Button btnFollow;

    @FXML
    private Button btnFollowing;

    @FXML
    private Button btnLikeSerie;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnMyBadges;

    @FXML
    private Button btnMySeries;

    @FXML
    private Button btnPostReview;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnReadChapter;

    @FXML
    private Button btnReading;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnStatistics;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnToRead;

    @FXML
    private Button btnToReadSerie;

    @FXML
    private ImageView chapterCover;

    @FXML
    private Label chapterTitle;

    @FXML
    private ImageView cover;

    @FXML
    private Label description;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Pane paneMenu;

    @FXML
    private ImageView propic;

    @FXML
    private PieChart ratingChart;

    @FXML
    private VBox readerMenu;

    @FXML
    private ChoiceBox<String> seriesPicker;

    @FXML
    private TextArea taComment;

    @FXML
    private Label title;

    @FXML
    private VBox vBoxAuthorFromOutside;

    @FXML
    private VBox vBoxChapter;

    @FXML
    private VBox vBoxChapters;

    @FXML
    private VBox vBoxFav;

    @FXML
    private VBox vBoxFavSeries;

    @FXML
    private VBox vBoxFollowing;

    @FXML
    private VBox vBoxFollowingAuthors;

    @FXML
    private VBox vBoxMySeries;

    @FXML
    private VBox vBoxMySeriesSeries;

    @FXML
    private VBox vBoxOriginalSeries;

    @FXML
    private VBox vBoxPostReview;

    @FXML
    private VBox vBoxReading;

    @FXML
    private VBox vBoxReadingSeries;

    @FXML
    private VBox vBoxReviews;

    @FXML
    private VBox vBoxSerie;

    @FXML
    private VBox vBoxSeries;

    @FXML
    private VBox vBoxSettings;

    @FXML
    private VBox vBoxStats;

    @FXML
    private VBox vBoxToRead;

    @FXML
    private VBox vBoxToReadSeries;

    @FXML
    private Slider ratingSlider;


    private static final String READER = "reader";
    private static final String BORDER_STYLE = "-fx-border-color: #9b55dc; -fx-background-radius: 20";
    private static final String PLAIN_STYLE = "-fx-border-color: #ffffff;";

    private boolean openMenu = true;
    private static FeedControllerG2 instance;
    private static List<SeriesBean> latestSeries;
    private List<SeriesBean> mySeries;

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

        if(UserLogin.getInstance().getAccount().getRole().equals(READER)){
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

    private void displayListOfSeries(List<SeriesBean> series, VBox box){

        box.getChildren().clear();

        int size = series.size();
        if(size == 0){
            boxNoSeries.setVisible(true);
            vBoxOriginalSeries.setVisible(false);
            return;
        }else{
            vBoxOriginalSeries.setVisible(true);
            boxNoSeries.setVisible(false);
        }
        for(int j=0; j<size; j++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("seriesCard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                SeriesCardControllerG cardController = fxmlLoader.getController();
                cardController.setData(series.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> openSerie(series.get(finalJ)));

                box.getChildren().add(card);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void displayListOfChapters(List<ChapterBean> chapters, SeriesBean seriesBean, VBox box){

        int actualSize = chapters.size();
        
        box.getChildren().clear();

        boxNoChapters.setVisible(false);
        if(actualSize==0){
            boxNoChapters.setVisible(true);
            return;
        }
        for(int j=0; j<actualSize; j++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("chapterCard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                ChapterCardControllerG cardController = fxmlLoader.getController();
                cardController.setData(chapters.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> openChapter(chapters.get(finalJ), seriesBean));
                box.getChildren().add(card);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void displayListOfReviews(List<ReviewBean> reviews, VBox box) {

        box.getChildren().clear();
        int actualSize = reviews.size();

        boxNoReviews.setVisible(false);
        if(actualSize==0){
            boxNoReviews.setVisible(true);
            return;
        }
        for(int j=0; j<actualSize; j++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("reviewCard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                ReviewCardControllerG cardController = fxmlLoader.getController();
                cardController.setData(reviews.get(j));
                box.getChildren().add(card);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //reader
    private void openFav() {
        openMenu();
        closeAll();
        vBoxFav.setVisible(true);

        ResearchController researchController = new ResearchController();
        List<SeriesBean> favouriteSeries = researchController.getFavouriteSeries();
        displayListOfSeries(favouriteSeries, vBoxFavSeries);
    }
    private void openFollowing() {
        openMenu();
        closeAll();
        vBoxFollowing.setVisible(true);

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
    private void openReading() {
        openMenu();
        closeAll();
        vBoxReading.setVisible(true);

        ResearchController researchController = new ResearchController();
        List<SeriesBean> readingSeries = researchController.getReadingSeries();
        displayListOfSeries(readingSeries, vBoxReadingSeries);
    }
    private void openToRead() {
        openMenu();
        closeAll();
        vBoxToRead.setVisible(true);


        ResearchController researchController = new ResearchController();
        List<SeriesBean> toReadSeries = researchController.getToReadSeries();
        displayListOfSeries(toReadSeries, vBoxToReadSeries);
    }
    //author
    private void openStats(){
        openMenu();
        closeAll();
        vBoxStats.setVisible(true);

        List<String> myTitles = new ArrayList<>();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        PieChart.Data startingData = new PieChart.Data("", 100);
        pieChartData.add(startingData);
        for(SeriesBean seriesBean : mySeries){
            myTitles.add(seriesBean.getTitle());
            StatisticsController statisticsController = new StatisticsController();
            Float rating = statisticsController.seriesAverageRating(seriesBean);
            PieChart.Data data = new PieChart.Data(seriesBean.getTitle(), rating);
            pieChartData.add(data);
        }
        seriesPicker.getItems().setAll(myTitles);
    }
    private void openMySeries(){
        openMenu();
        closeAll();
        vBoxMySeries.setVisible(true);

        ResearchController researchController = new ResearchController();
        mySeries = researchController.getPublishedSeries();
        displayListOfSeries(mySeries, vBoxMySeriesSeries);
    }
    //both
    private void openSettings() {
        openMenu();
        closeAll();
        vBoxSettings.setVisible(true);
    }
    public void openProfile(){
        closeAll();
        boxProfile.setVisible(true);
    }
    public void openFeed(){
        closeAll();
        boxFeed.setVisible(true);
        vBoxSeries.setVisible(true);
    }

    private void likeSeries(SeriesBean seriesBean) {
        FavouritesController favouritesController = new FavouritesController();
        if (favouritesController.isSeriesFavourite(seriesBean)) {
            btnLikeSerie.setStyle(BORDER_STYLE);
            btnLikeSerie.setOnAction(event -> removeLike(seriesBean));
        } else {
            btnLikeSerie.setStyle(PLAIN_STYLE);
            btnLikeSerie.setOnAction(event -> addFavourite(seriesBean));
        }
    }
    private void addFavourite(SeriesBean seriesBean) {
        FavouritesController favouritesController = new FavouritesController();
        favouritesController.addSeriesToFavourites(seriesBean);
        btnLikeSerie.setOnAction(event -> removeLike(seriesBean));
        btnLikeSerie.setStyle(BORDER_STYLE);
    }
    private void removeLike(SeriesBean seriesBean) {
        FavouritesController favouritesController = new FavouritesController();
        favouritesController.removeSeriesFromFavourites(seriesBean);
        btnLikeSerie.setOnAction(event -> addFavourite(seriesBean));
        btnLikeSerie.setStyle(PLAIN_STYLE);
    }
    private void addToRead(SeriesBean seriesBean) {
        ToReadController toReadController = new ToReadController();
        if (toReadController.isSeriesAddedToRead(seriesBean)) {
            btnToReadSerie.setStyle(BORDER_STYLE);
            btnToReadSerie.setOnAction(event -> removeToRead(seriesBean));
        } else {
            btnToReadSerie.setStyle(PLAIN_STYLE);
            btnToReadSerie.setOnAction(event -> addSeriesToRead(seriesBean));
        }
    }
    private void addSeriesToRead(SeriesBean seriesBean) {
        ToReadController toReadController = new ToReadController();
        toReadController.addSeriesToToRead(seriesBean);
        btnToReadSerie.setOnAction(event -> removeToRead(seriesBean));
        btnToReadSerie.setStyle(BORDER_STYLE);
    }
    private void removeToRead(SeriesBean seriesBean) {
        ToReadController toReadController = new ToReadController();
        toReadController.removeSeriesFromToRead(seriesBean);
        btnToReadSerie.setOnAction(event -> addSeriesToRead(seriesBean));
        btnToReadSerie.setStyle(PLAIN_STYLE);
    }

    private void areYouFollowing(AuthorBean authorBean) {
        FollowAuthorController followAuthorController = new FollowAuthorController();
        if(!followAuthorController.isAuthorFollowed(authorBean)){
            btnFollow.setText("follow");
            btnFollow.setStyle(PLAIN_STYLE);
            btnFollow.setOnAction(event -> followAuthor(authorBean));
        }else{
            btnFollow.setText("unfollow");
            btnFollow.setStyle(BORDER_STYLE);
            btnFollow.setOnAction(event -> unfollowAuthor(authorBean));
        }
    }
    private void unfollowAuthor(AuthorBean authorBean) {
        FollowAuthorController followAuthorController = new FollowAuthorController();
        followAuthorController.unfollowAuthor(authorBean);
        btnFollow.setStyle(PLAIN_STYLE);
        btnFollow.setText("follow");
        btnFollow.setOnAction(event -> followAuthor(authorBean));
    }
    private void followAuthor(AuthorBean authorBean) {
        FollowAuthorController followAuthorController = new FollowAuthorController();
        followAuthorController.followAuthor(authorBean);
        btnFollow.setStyle(BORDER_STYLE);
        btnFollow.setText("unfollow");
        btnFollow.setOnAction(event -> unfollowAuthor(authorBean));
    }

    private void openChapter(ChapterBean chapterBean, SeriesBean seriesBean) {
        closeAll();
        vBoxChapter.setVisible(true);

        chapterTitle.setText(chapterBean.getTitle());
        chapterCover.setImage(chapterBean.getCover());
        description.setText(chapterBean.getDescription());

        displayListOfReviews(chapterBean.getReviews(), vBoxReviews);
        if(UserLogin.getInstance().getAccount().getRole().equals(READER)) {
            readChapter(chapterBean);
            btnPostReview.setOnAction(event -> openBoxReview(chapterBean, seriesBean));
        }else{
            btnBuy.setVisible(false);
            btnPostReview.setVisible(false);
        }
    }

    private void openBoxReview(ChapterBean chapterBean, SeriesBean seriesBean) {
        closeAll();
        vBoxPostReview.setVisible(true);
        btnSubmit.setOnAction(event -> postReview(chapterBean, seriesBean));
        btnBackFromChaptee.setOnAction(event -> openChapter(chapterBean, seriesBean));
    }

    public void postReview(ChapterBean chapterBean, SeriesBean seriesBean){
        ReviewBean reviewBean = new ReviewBean2();
        reviewBean.setComment(taComment.getText());
        reviewBean.setAccount(UserLogin.getInstance().getAccount());
        reviewBean.setRating(1);
        //e magari anche la foto
        PostReviewController postReviewController = new PostReviewController();
        postReviewController.post(reviewBean, chapterBean, seriesBean);

        taComment.setText("");
        openChapter(chapterBean, seriesBean);
    }

    public void openSerie(SeriesBean seriesBean){

        closeAll();
        vBoxSerie.setVisible(true);

        title.setText(seriesBean.getTitle());
        cover.setImage(seriesBean.getCover());
        author.setText(seriesBean.getAuthor().getUsername());
        author.setOnAction(event -> openAuthor(seriesBean.getAuthor()));

        displayListOfChapters(seriesBean.getChapters(), seriesBean, vBoxChapters);
        if(UserLogin.getInstance().getAccount().getRole().equals(READER)) {
            likeSeries(seriesBean);
            addToRead(seriesBean);
        }else{
            btnLikeSerie.setVisible(false);
            btnToReadSerie.setVisible(false);
        }
    }
    private void openAuthor(AuthorBean authorBean) {

        closeAll();
        vBoxAuthorFromOutside.setVisible(true);

        authorusername.setText(authorBean.getUsername());
        authorPropic.setImage(authorBean.getProPic());

        ResearchController researchController = new ResearchController();
        List<SeriesBean> publishedSeries = researchController.getPublishedSeries(authorBean);
        displayListOfSeries(publishedSeries, vBoxOriginalSeries);
        if(UserLogin.getInstance().getAccount().getRole().equals(READER)) {
            areYouFollowing(authorBean);
        }else{
            btnFollow.setVisible(false);
        }
    }

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


    private void readChapter(ChapterBean chapterBean) {
        //TO-DO
    }

    private void closeAll(){
        vBoxSeries.setVisible(false);
        boxProfile.setVisible(false);
        boxFeed.setVisible(false);
        vBoxFav.setVisible(false);
        vBoxToRead.setVisible(false);
        vBoxReading.setVisible(false);
        vBoxMySeries.setVisible(false);
        vBoxFollowing.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxSerie.setVisible(false);
        vBoxSettings.setVisible(false);
        vBoxAuthorFromOutside.setVisible(false);
        vBoxChapter.setVisible(false);
        vBoxPostReview.setVisible(false);
    }

}
