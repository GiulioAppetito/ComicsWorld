package com.example.comics.view2;

import com.example.comics.controller.*;
import com.example.comics.model.*;
import com.example.comics.model.exceptions.AlreadyExistingSeriesException;
import com.example.comics.model.exceptions.DiscountCodeException;
import com.example.comics.model.exceptions.FailedProfileCustomizationException;
import com.example.comics.model.exceptions.IncompleteReviewException;
import com.example.comics.model.fagioli.*;
import com.example.comics.view1.beans.BadgeBean1;
import com.example.comics.view1.beans.DiscountBean1;
import com.example.comics.view1.beans.ObjectiveBean1;
import com.example.comics.view1.beans.SeriesBean1;
import com.example.comics.view2.beans.AccountBean2;
import com.example.comics.view2.beans.DiscountCodeBean2;
import com.example.comics.view2.beans.ReviewBean2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeedControllerG2 implements ChapterObserver, AccountObserver, ReaderObserver {

    @FXML
    private Button author;

    @FXML
    private VBox authorMenu;

    @FXML
    private ImageView authorPropic;

    @FXML
    private Label authorusername;

    @FXML
    private ImageView badgeIcon;

    @FXML
    private TextField badgeLimitDaysNewSeries1;

    @FXML
    private Label badgeName;

    @FXML
    private TextField badgeNameNewSeries1;

    @FXML
    private TextField badgePercNewSeries1;

    @FXML
    private TextField badgeReqNewSeries1;

    @FXML
    private VBox boxFeed;

    @FXML
    private VBox boxNoBadges;

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
    private Button btnBackFromChaptee1;

    @FXML
    private Button btnBackFromNewSeries;

    @FXML
    private Button btnBackFromNewSeries2;

    @FXML
    private Button btnBackFromNewSeries3;

    @FXML
    private Button btnBuy;

    @FXML
    private Button btnChangeEmail;

    @FXML
    private Button btnChangeFirstName;

    @FXML
    private Button btnChangeLastName;

    @FXML
    private Button btnChangeUsername;

    @FXML
    private Button btnCloseFailure;

    @FXML
    private Button btnCloseNewBadgeWon;

    @FXML
    private Button btnCloseNotif;

    @FXML
    private Button btnCloseorder;

    @FXML
    private Button btnFav;

    @FXML
    private Button btnFeed;

    @FXML
    private Button btnFollow;

    @FXML
    private Button btnFollowing;

    @FXML
    private Button btnGoToPayment;

    @FXML
    private Button btnLikeSerie;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnMyBadges;

    @FXML
    private Button btnMyOrders;

    @FXML
    private Button btnMySeries;

    @FXML
    private Button btnNewSeriesNext1;

    @FXML
    private Button btnNewSeriesNext2;

    @FXML
    private Button btnPickCover;

    @FXML
    private Button btnPostReview;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnPublishSeries;

    @FXML
    private Button btnReadChapter;

    @FXML
    private Button btnReading;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSkipNewSeries;

    @FXML
    private Button btnStatistics;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnToRead;

    @FXML
    private Button btnToReadSerie;

    @FXML
    private Button btnpickBadge1NewSeriesCover;

    @FXML
    private ImageView chapterCover;

    @FXML
    private Label chapterTitle;

    @FXML
    private ChoiceBox<Genres> choiceBoxGenre1;

    @FXML
    private ChoiceBox<Genres> choiceBoxGenre2;

    @FXML
    private ChoiceBox<Genres> choiceBoxGenre3;

    @FXML
    private ChoiceBox<Levels> choiceBoxLevel1;

    @FXML
    private ChoiceBox<String> choiceBoxType1;

    @FXML
    private ImageView cover;

    @FXML
    private Label description;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private ImageView newSeiresCover;

    @FXML
    private Label notifMessage;

    @FXML
    private Label notifTitle;

    @FXML
    private Pane paneBadgeWon;

    @FXML
    private Pane paneFailure;

    @FXML
    private Pane paneMenu;

    @FXML
    private Pane paneNotifications;

    @FXML
    private Pane paneOrder;

    @FXML
    private Pane panePayment;

    @FXML
    private ImageView propic;

    @FXML
    private PieChart ratingChart;

    @FXML
    private Slider ratingSlider;

    @FXML
    private VBox readerMenu;

    @FXML
    private ChoiceBox<String> seriesPicker;

    @FXML
    private TextArea taComment;

    @FXML
    private TextArea taDescriptionNewSeries;

    @FXML
    private TextField tfDiscountCode;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfTitleNewSieries;

    @FXML
    private TextField tfUsername;

    @FXML
    private Label title;

    @FXML
    private VBox vBoxAuthorFromOutside;

    @FXML
    private GridPane vBoxBadgesList;

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
    private VBox vBoxMyBadges;

    @FXML
    private VBox vBoxMyOrders;

    @FXML
    private VBox vBoxMySeries;

    @FXML
    private VBox vBoxMySeriesSeries;

    @FXML
    private VBox vBoxOrderedSeries;

    @FXML
    private VBox vBoxOriginalSeries;

    @FXML
    private VBox vBoxPostReview;

    @FXML
    private VBox vBoxPublishSeries1;

    @FXML
    private VBox vBoxPublishSeries2;

    @FXML
    private VBox vBoxPublishSeries3;

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


    private static final String READER = "reader";
    private static final String BORDER_STYLE = "-fx-border-color: #9b55dc; -fx-background-radius: 20";
    private static final String PLAIN_STYLE = "-fx-border-color: #ffffff;";

    private boolean openMenu = true;
    private boolean openNotifs = true;
    private static FeedControllerG2 instance;
    private static List<SeriesBean> latestSeries;
    private List<SeriesBean> mySeries;

    private FeedControllerG2(){
        //costruttore
    }
    public static synchronized FeedControllerG2 getInstance(){
        if(instance == null){
            instance = new FeedControllerG2();
            ChapterSubject.attach(instance, "reviews");
            AccountSubject.attach(instance, "badges");
            AccountSubject.attach(instance, "orders");
            AccountSubject.attach(instance);
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
        openNotifications();
        initProfile();

        choiceBoxGenre1.getItems().setAll(Genres.values());
        choiceBoxGenre2.getItems().setAll(Genres.values());
        choiceBoxGenre3.getItems().setAll(Genres.values());

        choiceBoxType1.getItems().setAll("REVIEWS","CHAPTERS");

        choiceBoxLevel1.getItems().setAll(Levels.values());


        btnProfile.setOnAction(event -> openProfile());
        btnFeed.setOnAction(event -> openFeed());
        btnMenu.setOnAction(event -> openMenu());
        btnSettings.setOnAction(event -> openSettings());

        if(UserLogin.getInstance().getAccount().getRole().equals(READER)){
            readerMenu();
        }else{
            authorMenu();
            btnBuy.setVisible(false);
            btnPostReview.setVisible(false);
            btnFollow.setVisible(false);
        }

        //reader menu
        btnFav.setOnAction(event -> openFav());
        btnFollowing.setOnAction(event -> openFollowing());
        btnReading.setOnAction(event -> openReading());
        btnToRead.setOnAction(event -> openToRead());
        btnMyBadges.setOnAction(event -> openMyBadges());
        btnMyOrders.setOnAction(event -> openMyOrders());
        btnCloseFailure.setOnAction(event -> paneFailure.setVisible(false));
        btnCloseorder.setOnAction(event -> paneOrder.setVisible(false));
        btnCloseNewBadgeWon.setOnAction(event -> paneBadgeWon.setVisible(false));
        btnCloseNotif.setOnAction(event -> openNotifications());
        btnBackFromNewSeries.setOnAction(event -> openFeed());
        btnBackFromNewSeries2.setOnAction(event -> openFeed());
        btnBackFromNewSeries3.setOnAction(event -> openFeed());

        //author menu
        btnStatistics.setOnAction(event -> openStats());
        btnMySeries.setOnAction(event -> openMySeries());
        btnPublishSeries.setOnAction(event -> openPublishSeriesForm());

    }

    private void openPublishSeriesForm() {
        vBoxPublishSeries1.setVisible(true);
        //img
        btnNewSeriesNext1.setOnAction(event -> nextStepPublishingSeries(tfTitleNewSieries.getText(), taDescriptionNewSeries.getText()));
    }

    private void nextStepPublishingSeries(String title, String description) {
        vBoxPublishSeries2.setVisible(true);
        btnNewSeriesNext2.setOnAction(event -> nextStepPublishingSeries2(title, description, choiceBoxGenre1.getValue().toString(), choiceBoxGenre2.getValue().toString(), choiceBoxGenre3.getValue().toString()));
    }

    private void nextStepPublishingSeries2(String title, String description, String genre1, String genre2, String genre3) {
        vBoxPublishSeries3.setVisible(true);
        SeriesBean1 seriesBean1 = new SeriesBean1();
        seriesBean1.setDescription(description);
        seriesBean1.setTitle(title);
        seriesBean1.setGenre1(Genres.valueOf(genre1));
        seriesBean1.setGenre2(Genres.valueOf(genre2));
        seriesBean1.setGenre3(Genres.valueOf(genre3));

        if(genre1.equals("") || genre2.equals("") || genre3.equals("")){
            notifTitle.setText("ops!");
            notifMessage.setText("sorry, there was a mistake!");
            paneNotifications.setVisible(true);
            openFeed();
        }else{
            vBoxPublishSeries2.setVisible(true);
            btnSkipNewSeries.setOnAction(event -> publishSeries(seriesBean1));
        }

    }

    private void publishSeries(SeriesBean1 seriesBean1) {


        List<ObjectiveBean> list = new ArrayList<>();
        ObjectiveBean1 objectiveBean1;
        DiscountBean1 discountBean1;
        BadgeBean1 badgeBean1;

        if(choiceBoxLevel1.getValue()!=null && choiceBoxType1.getValue()!=null && badgeNameNewSeries1.getText()!=null && badgeLimitDaysNewSeries1.getText()!=null){

                                objectiveBean1 = new ObjectiveBean1();
                                objectiveBean1.setRequirement(badgeReqNewSeries1.getText());
                                objectiveBean1.setLevel(choiceBoxLevel1.getValue());
                                objectiveBean1.setType(choiceBoxType1.getValue());

                                discountBean1 = new DiscountBean1();
                                discountBean1.setLimitDays(badgeLimitDaysNewSeries1.getText());
                                discountBean1.setPercentage(badgePercNewSeries1.getText());
                                objectiveBean1.setDiscountBean(discountBean1);

                                badgeBean1 = new BadgeBean1();
                                badgeBean1.setName(badgeNameNewSeries1.getText());

                                objectiveBean1.setBadgeBean(badgeBean1);

                                list.add(objectiveBean1);

            PublishSeriesController publishSeriesController = new PublishSeriesController();
            try {
                publishSeriesController.publishSeries(seriesBean1, list);
            } catch (AlreadyExistingSeriesException e) {
                notifTitle.setText("ops!");
                notifMessage.setText(e.getMessage());
                paneNotifications.setVisible(true);
            }

        }else{
            notifTitle.setText("ops!");
            notifMessage.setText("sorry, there was a mistake!");
            paneNotifications.setVisible(true);
            closeAll();
            openFeed();
        }

    }


    private void displayListOfBadges() {
        vBoxBadgesList.getChildren().clear();

        ResearchController researchController = new ResearchController();
        List<BadgeBean> badges = researchController.getUserBadges();
        int size = badges.size();
        if(size == 0){
            boxNoBadges.setVisible(true);
            vBoxBadgesList.setVisible(false);
            return;
        }else{
            vBoxBadgesList.setVisible(true);
            boxNoBadges.setVisible(false);
        }

        int columns=3;
        int i=1;

        for (int j = 0; j < size; j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("badgeCard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                BadgeCardControllerG2 cardController = fxmlLoader.getController();
                cardController.setData(badges.get(j));

                vBoxBadgesList.add(card, j%columns, i);
                if(j%columns == columns -1 ){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    private void displayListOfOrders(List<OrderBean> orders, VBox box) {

        box.getChildren().clear();
        int actualSize = orders.size();

        for(int j=0; j<actualSize; j++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("orderCard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                OrderCardControllerG2 cardController = fxmlLoader.getController();
                cardController.setData(orders.get(j));
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
    private void openMyOrders(){
        openMenu();
        closeAll();
        vBoxMyOrders.setVisible(true);

        ResearchController researchController = new ResearchController();
        List<OrderBean> myOrders = researchController.getUserOrders();
        displayListOfOrders(myOrders, vBoxOrderedSeries);
    }
    private void openMyBadges() {
        closeAll();
        vBoxMyBadges.setVisible(true);
        displayListOfBadges();
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

        tfFirstName.setText(UserLogin.getInstance().getAccount().getFirstName());
        tfLastName.setText(UserLogin.getInstance().getAccount().getLastName());
        tfUsername.setText(UserLogin.getInstance().getAccount().getUsername());
        tfEmail.setText(UserLogin.getInstance().getAccount().getEmail());

        btnChangeEmail.setOnAction(event -> changeEmail());
        btnChangeFirstName.setOnAction(event -> changeFirstName());
        btnChangeLastName.setOnAction(event -> changeLastName());
        btnChangeUsername.setOnAction(event -> changeUsername());

    }
    public void openProfile(){
        closeAll();
        boxProfile.setVisible(true);
    }
    public void openFeed(){
        closeAll();
        boxFeed.setVisible(true);
        vBoxSeries.setVisible(true);

        loadLatestSeries();
        displayListOfSeries(latestSeries, vBoxSeries);
    }

    private void changeUsername() {
        AccountBean2 accountBean = new AccountBean2();
        accountBean.setUsername(tfUsername.getText());

        CustomizeProfileController customizeProfileController = new CustomizeProfileController();
        try {
            customizeProfileController.changeUsername(accountBean);
        } catch (FailedProfileCustomizationException e) {
            badgeIcon.setVisible(false);
            notifTitle.setText("ops!!!");
            notifMessage.setText(e.getMessage());
            paneNotifications.setVisible(true);
        }
    }
    private void changeLastName() {
        AccountBean2 accountBean = new AccountBean2();
        accountBean.setLastName(tfLastName.getText());

        CustomizeProfileController customizeProfileController = new CustomizeProfileController();
        customizeProfileController.changeLastName(accountBean);
    }
    private void changeFirstName() {
        AccountBean2 accountBean = new AccountBean2();
        accountBean.setFirstName(tfFirstName.getText());

        CustomizeProfileController customizeProfileController = new CustomizeProfileController();
        customizeProfileController.changeFirstName(accountBean);
    }
    private void changeEmail() {
        AccountBean2 accountBean = new AccountBean2();
        accountBean.setEmail(tfEmail.getText());

        CustomizeProfileController customizeProfileController = new CustomizeProfileController();
        customizeProfileController.changeEmail(accountBean);
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
    private void openChapter(ChapterBean chapterBean, SeriesBean seriesBean) {
        closeAll();
        vBoxChapter.setVisible(true);

        currentChapter = chapterBean;

        chapterTitle.setText(chapterBean.getTitle());
        chapterCover.setImage(chapterBean.getCover());
        description.setText(chapterBean.getDescription());

        displayListOfReviews(chapterBean.getReviews(), vBoxReviews);
        if(UserLogin.getInstance().getAccount().getRole().equals(READER)) {
            if(Boolean.TRUE.equals(chapterBean.getRead())){
                btnReadChapter.setOnAction(event -> unmarkReadChapter(seriesBean, chapterBean));
                btnReadChapter.setStyle(BORDER_STYLE);
            }else{
                btnReadChapter.setOnAction(event->readChapter(seriesBean, chapterBean));
                btnReadChapter.setStyle(PLAIN_STYLE);
            }

            btnBuy.setOnAction(event -> openBoxPayment(chapterBean, seriesBean));
            btnPostReview.setOnAction(event -> openBoxReview(chapterBean, seriesBean));
        }else{
            btnBuy.setVisible(false);
            btnPostReview.setVisible(false);
        }
    }

    private void openBoxPayment(ChapterBean chapterBean, SeriesBean seriesBean){
        closeAll();
        vBoxChapter.setVisible(true);
        panePayment.setVisible(true);

        btnGoToPayment.setOnAction(event -> buyComic(chapterBean, seriesBean));
        btnBackFromChaptee1.setOnAction(event -> openChapter(chapterBean, seriesBean));
    }

    private void buyComic(ChapterBean chapterBean, SeriesBean seriesBean) {

        BuyComicController buyComicController = new BuyComicController();
        DiscountCodeBean2 discountCodeBean2 = new DiscountCodeBean2();

        String code = tfDiscountCode.getText();
        if(code.equals("")){
            discountCodeBean2 = null;
        }else {
            discountCodeBean2.setCode(code);
        }

        try {
            buyComicController.buyComic(seriesBean, chapterBean, discountCodeBean2);
        } catch (DiscountCodeException e) {
            panePayment.setVisible(true);

        }

        panePayment.setVisible(false);
    }

    private void openBoxReview(ChapterBean chapterBean, SeriesBean seriesBean) {
        closeAll();
        vBoxChapter.setVisible(true);
        vBoxPostReview.setVisible(true);
        btnSubmit.setOnAction(event -> postReview(chapterBean, seriesBean));
        btnBackFromChaptee.setOnAction(event -> openChapter(chapterBean, seriesBean));
    }

    public void postReview(ChapterBean chapterBean, SeriesBean seriesBean){
        try{
            ReviewBean2 reviewBean = new ReviewBean2();
            reviewBean.setComment(taComment.getText());
            reviewBean.setAccount(UserLogin.getInstance().getAccount());
            reviewBean.setRating(ratingSlider.getValue());
            //e magari anche la foto
            PostReviewController postReviewController = new PostReviewController();
            postReviewController.post(reviewBean, chapterBean, seriesBean);

            taComment.setText("");
            vBoxPostReview.setVisible(false);
        }catch(IncompleteReviewException e){
            notifTitle.setText("No comment!");
            badgeIcon.setVisible(false);
            notifMessage.setText(e.getMessage());
            paneNotifications.setVisible(true);
        }

    }
    
    private void initProfile(){
        lblFirstName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblLastName.setText(UserLogin.getInstance().getAccount().getLastName());
        lblEmail.setText(UserLogin.getInstance().getAccount().getEmail());
        propic.setImage(UserLogin.getInstance().getAccount().getProPic());
    }

    private void openNotifications() {
        if(openNotifs){
            paneNotifications.setVisible(false);
            openNotifs = false;
            return;
        }
        paneNotifications.setVisible(true);
        openNotifs = true;
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
    
    private void readChapter(SeriesBean seriesBean, ChapterBean chapterBean) {
        MarkChapterAsReadController markChapterAsReadController = new MarkChapterAsReadController();
        markChapterAsReadController.markChapterAsRead(seriesBean, chapterBean);

        btnReadChapter.setOnAction(event -> unmarkReadChapter(seriesBean, chapterBean));
        btnReadChapter.setStyle(BORDER_STYLE);
        btnReadChapter.setText("Read");
    }

    public void unmarkReadChapter(SeriesBean seriesBean, ChapterBean chapterBean){
        MarkChapterAsReadController markChapterAsReadController = new MarkChapterAsReadController();
        markChapterAsReadController.unmarkChapterAsRead(seriesBean, chapterBean);

        btnReadChapter.setOnAction(event -> readChapter(seriesBean, chapterBean));
        btnReadChapter.setStyle(PLAIN_STYLE);
        btnReadChapter.setText("Mark as read");
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
        vBoxMyOrders.setVisible(false);
        vBoxStats.setVisible(false);
        vBoxSerie.setVisible(false);
        vBoxSettings.setVisible(false);
        vBoxAuthorFromOutside.setVisible(false);
        vBoxChapter.setVisible(false);
        vBoxPostReview.setVisible(false);
        vBoxMyBadges.setVisible(false);
        vBoxMyOrders.setVisible(false);
        paneNotifications.setVisible(false);
        panePayment.setVisible(false);
        paneFailure.setVisible(false);
        paneOrder.setVisible(false);
        paneBadgeWon.setVisible(false);
        vBoxPublishSeries1.setVisible(false);
        vBoxPublishSeries2.setVisible(false);
        vBoxPublishSeries3.setVisible(false);
    }

    private ChapterBean currentChapter = null;

    @Override
    public void update(ReviewBean reviewBean) {
        if(currentChapter!=null){
            List<ReviewBean> reviews = currentChapter.getReviews();
            reviews.add(reviewBean);
            displayListOfReviews(reviews, vBoxReviews);
        }
    }

    @Override
    public void update() {
        //to-do
    }

    @Override
    public void update(BadgeBean badgeBean) {
        badgeIcon.setImage(badgeBean.getIcon());
        badgeName.setText(badgeBean.getName());
        badgeIcon.setImage(badgeBean.getIcon());

        paneBadgeWon.setVisible(true);
    }

    @Override
    public void update(Boolean payment) {
        badgeIcon.setVisible(false);
        if(Boolean.TRUE.equals(payment)) {
            paneOrder.setVisible(true);
        }else{
            paneFailure.setVisible(true);
        }
    }
}
