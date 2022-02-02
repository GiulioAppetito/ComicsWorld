package com.example.comics.view1;

import com.example.comics.controller.BuyComicController;
import com.example.comics.controller.MarkChapterAsReadController;
import com.example.comics.controller.PostReviewController;
import com.example.comics.model.*;
import com.example.comics.model.exceptions.DiscountCodeException;
import com.example.comics.model.exceptions.InvalidPaymentException;
import com.example.comics.model.fagioli.*;
import com.example.comics.view1.beans.ReviewBean1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;

public class ChapterControllerG implements ChapterObserver, ObjectiveObserver {

    @FXML
    private VBox vbReviews;

    @FXML
    private Button btnSkip;

    @FXML
    private ImageView chapterCoverIV;

    @FXML
    private Button btnAddReview;

    @FXML
    private Pane paneInsertReview;

    @FXML
    private Button btnChapterRead;

    @FXML
    private ImageView badgeIconView;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnPostReview;

    @FXML
    private TextArea txtAreaComment;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblChapterId;

    @FXML
    private Label lblChapterTitle;

    @FXML
    private Pane newBadgeWonPane;

    @FXML
    private Button btnCloseEditor;

    @FXML
    private Button btnCloseBadge;

    @FXML
    private Label lblBadgeName;

    @FXML
    private Label lblBadgeSeries;

    @FXML
    private Label lblBadgeType;

    @FXML
    private Button btnStar01;

    @FXML
    private Button btnStar02;

    @FXML
    private Button btnStar03;

    @FXML
    private Button btnStar04;

    @FXML
    private Button btnStar05;

    @FXML
    private Button btnStar11;

    @FXML
    private Button btnStar12;

    @FXML
    private Button btnStar13;

    @FXML
    private Button btnStar14;

    @FXML
    private Button btnStar15;

    @FXML
    private Button btnStar21;

    @FXML
    private Button btnStar22;

    @FXML
    private Button btnStar23;

    @FXML
    private Button btnStar24;

    @FXML
    private Button btnStar25;

    @FXML
    private Button btnStar31;

    @FXML
    private Button btnStar32;

    @FXML
    private Button btnStar33;

    @FXML
    private Button btnStar34;

    @FXML
    private Button btnStar35;

    @FXML
    private Button btnStar41;

    @FXML
    private Button btnStar42;

    @FXML
    private Button btnStar43;

    @FXML
    private Button btnStar44;

    @FXML
    private Button btnStar45;

    @FXML
    private Button btnStar51;

    @FXML
    private Button btnStar52;

    @FXML
    private Button btnStar53;

    @FXML
    private Button btnStar54;

    @FXML
    private Button btnStar55;

    @FXML
    private HBox hbox0;

    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3;

    @FXML
    private HBox hbox4;

    @FXML
    private HBox hbox5;

    @FXML
    private ImageView imgStar01;

    @FXML
    private ImageView imgStar02;

    @FXML
    private ImageView imgStar03;

    @FXML
    private ImageView imgStar04;

    @FXML
    private ImageView imgStar05;

    @FXML
    private ImageView imgStar11;

    @FXML
    private ImageView imgStar12;

    @FXML
    private ImageView imgStar13;

    @FXML
    private ImageView imgStar14;

    @FXML
    private ImageView imgStar15;

    @FXML
    private ImageView imgStar21;

    @FXML
    private ImageView imgStar22;

    @FXML
    private ImageView imgStar23;

    @FXML
    private ImageView imgStar24;

    @FXML
    private ImageView imgStar25;

    @FXML
    private ImageView imgStar31;

    @FXML
    private ImageView imgStar32;

    @FXML
    private ImageView imgStar33;

    @FXML
    private ImageView imgStar34;

    @FXML
    private ImageView imgStar35;

    @FXML
    private ImageView imgStar41;

    @FXML
    private ImageView imgStar42;

    @FXML
    private ImageView imgStar43;

    @FXML
    private ImageView imgStar44;

    @FXML
    private ImageView imgStar45;

    @FXML
    private ImageView imgStar51;

    @FXML
    private ImageView imgStar52;

    @FXML
    private ImageView imgStar53;

    @FXML
    private ImageView imgStar54;

    @FXML
    private ImageView imgStar55;


    @FXML
    private Button btnApply;


    @FXML
    private Button btnClosePayment;


    @FXML
    private ChoiceBox<String> choiceBoxCodes;


    @FXML
    private Pane paymentPane;


    @FXML
    public void zeroStars(){
        reviewRating = 0;
        hbox0.setVisible(true);
        hbox1.setVisible(false);
        hbox2.setVisible(false);
        hbox3.setVisible(false);
        hbox4.setVisible(false);
        hbox5.setVisible(false);
    }
    @FXML
    public void oneStar(){
        reviewRating = 1;
        hbox0.setVisible(false);
        hbox1.setVisible(true);
        hbox2.setVisible(false);
        hbox3.setVisible(false);
        hbox4.setVisible(false);
        hbox5.setVisible(false);
    }
    @FXML
    public void twoStars(){
        reviewRating = 2;
        hbox0.setVisible(false);
        hbox1.setVisible(false);
        hbox2.setVisible(true);
        hbox3.setVisible(false);
        hbox4.setVisible(false);
        hbox5.setVisible(false);
    }
    @FXML
    public void threeStars(){
        reviewRating = 3;
        hbox0.setVisible(false);
        hbox1.setVisible(false);
        hbox2.setVisible(false);
        hbox3.setVisible(true);
        hbox4.setVisible(false);
        hbox5.setVisible(false);
    }
    @FXML
    public void fourStars(){
        reviewRating = 4;
        hbox0.setVisible(false);
        hbox1.setVisible(false);
        hbox2.setVisible(false);
        hbox3.setVisible(false);
        hbox4.setVisible(true);
        hbox5.setVisible(false);
    }
    @FXML
    public void fiveStars(){
        reviewRating = 5;
        hbox0.setVisible(false);
        hbox1.setVisible(false);
        hbox2.setVisible(false);
        hbox3.setVisible(false);
        hbox4.setVisible(false);
        hbox5.setVisible(true);
    }

    @FXML
    private TextArea taDescription;

    @FXML
    private Button btnBuyComics;

    private int reviewRating = 0;

    @FXML
    void closeEditor() {
        paneInsertReview.setVisible(false);
    }


    private static final String STYLE = ".button2";
    private static final String STYLE2 = "-fx-background-color: #5DADE2; -fx-background-radius: 20";

    private String chapterSeries;


    public void init(ChapterBean chapterBean, SeriesBean seriesBean){

        ChapterSubject.attach(this);
        ObjectiveSubject.attach(this);
        lblAuthor.setText("autore");
        lblChapterTitle.setText(chapterBean.getTitle());
        taDescription.setText(chapterBean.getDescription());
        taDescription.setEditable(false);
        chapterSeries = seriesBean.getTitle();

        System.out.println("Ho letto il chapter: "+chapterBean.getRead());

        if(Boolean.TRUE.equals(chapterBean.getRead())){
            System.out.println("[ChapterControllerG] You have read this chapter.");
            btnChapterRead.setStyle(STYLE2);
            btnChapterRead.setOnAction(event -> removeChapterFromRead(seriesBean,chapterBean));
        }else{
            System.out.println("[ChapterControllerG] You didn't read this chapter.");
            btnChapterRead.setStyle(STYLE);
            btnChapterRead.setOnAction(event -> markChapterAsRead(seriesBean,chapterBean));
        }

        chapterCoverIV.setImage(chapterBean.getCover());

        btnCloseEditor.setOnAction(event -> closeEditor());
        btnCloseBadge.setOnAction(event -> closeBadgeWon());

        paneInsertReview.setVisible(false);
        newBadgeWonPane.setVisible(false);
        btnPostReview.setOnAction(event -> postReview(chapterBean, seriesBean));
        btnBuyComics.setOnAction(event -> buyComics());

        if(UserLogin.getInstance().getAccount().getRole().equals("reader")) {
            btnAddReview.setOnAction(event -> openEditor());
        }else{
            btnAddReview.setVisible(false);
        }
        List<ReviewBean> listOfReviews = chapterBean.getReviews();

        for (ReviewBean reviewBean : listOfReviews) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("review.fxml"));
            try {
                VBox vbRev = fxmlLoader.load();
                ReviewControllerG reviewController = fxmlLoader.getController();
                reviewController.setData(reviewBean);
                vbReviews.getChildren().add(vbRev);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        btnBack.setOnAction(event -> {


                HomeFactory homeFactory = new HomeFactory();
                HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
                homeControllerG.openFeed();
        });

        btnStar01.setOnAction(event -> oneStar());
        btnStar11.setOnAction(event -> oneStar());
        btnStar21.setOnAction(event -> oneStar());
        btnStar31.setOnAction(event -> oneStar());
        btnStar41.setOnAction(event -> oneStar());
        btnStar51.setOnAction(event -> oneStar());
        imgStar01.setOnMouseClicked(event -> oneStar());
        imgStar11.setOnMouseClicked(event -> oneStar());
        imgStar21.setOnMouseClicked(event -> oneStar());
        imgStar31.setOnMouseClicked(event -> oneStar());
        imgStar41.setOnMouseClicked(event -> oneStar());
        imgStar51.setOnMouseClicked(event -> oneStar());

        btnStar02.setOnAction(event -> twoStars());
        btnStar12.setOnAction(event -> twoStars());
        btnStar22.setOnAction(event -> twoStars());
        btnStar32.setOnAction(event -> twoStars());
        btnStar42.setOnAction(event -> twoStars());
        btnStar52.setOnAction(event -> twoStars());
        imgStar02.setOnMouseClicked(event -> twoStars());
        imgStar12.setOnMouseClicked(event -> twoStars());
        imgStar22.setOnMouseClicked(event -> twoStars());
        imgStar32.setOnMouseClicked(event -> twoStars());
        imgStar42.setOnMouseClicked(event -> twoStars());
        imgStar52.setOnMouseClicked(event -> twoStars());

        btnStar03.setOnAction(event -> threeStars());
        btnStar13.setOnAction(event -> threeStars());
        btnStar23.setOnAction(event -> threeStars());
        btnStar33.setOnAction(event -> threeStars());
        btnStar43.setOnAction(event -> threeStars());
        btnStar53.setOnAction(event -> threeStars());
        imgStar03.setOnMouseClicked(event -> threeStars());
        imgStar13.setOnMouseClicked(event -> threeStars());
        imgStar23.setOnMouseClicked(event -> threeStars());
        imgStar33.setOnMouseClicked(event -> threeStars());
        imgStar43.setOnMouseClicked(event -> threeStars());
        imgStar53.setOnMouseClicked(event -> threeStars());

        btnStar04.setOnAction(event -> fourStars());
        btnStar14.setOnAction(event -> fourStars());
        btnStar24.setOnAction(event -> fourStars());
        btnStar34.setOnAction(event -> fourStars());
        btnStar44.setOnAction(event -> fourStars());
        btnStar54.setOnAction(event -> fourStars());
        imgStar04.setOnMouseClicked(event -> fourStars());
        imgStar14.setOnMouseClicked(event -> fourStars());
        imgStar24.setOnMouseClicked(event -> fourStars());
        imgStar34.setOnMouseClicked(event -> fourStars());
        imgStar44.setOnMouseClicked(event -> fourStars());
        imgStar54.setOnMouseClicked(event -> fourStars());

        btnStar05.setOnAction(event -> fiveStars());
        btnStar15.setOnAction(event -> fiveStars());
        btnStar25.setOnAction(event -> fiveStars());
        btnStar35.setOnAction(event -> fiveStars());
        btnStar45.setOnAction(event -> fiveStars());
        btnStar55.setOnAction(event -> fiveStars());
        imgStar05.setOnMouseClicked(event -> fiveStars());
        imgStar15.setOnMouseClicked(event -> fiveStars());
        imgStar25.setOnMouseClicked(event -> fiveStars());
        imgStar35.setOnMouseClicked(event -> fiveStars());
        imgStar45.setOnMouseClicked(event -> fiveStars());
        imgStar55.setOnMouseClicked(event -> fiveStars());

        closePaymentPane();
        btnClosePayment.setOnAction(event -> closePaymentPane());
        btnApply.setOnAction(event -> applyDiscountCode(chapterBean,seriesBean));
        btnSkip.setOnAction(event -> applyNoDiscountCode(chapterBean,seriesBean));
    }

    private void applyNoDiscountCode(ChapterBean chapterBean, SeriesBean seriesBean) {
        BuyComicController buyComicController = new BuyComicController();
        try {
            buyComicController.buyComic(chapterBean,seriesBean, "");
        } catch (InvalidPaymentException | DiscountCodeException e) {
            e.printStackTrace();
        }
    }

    private void applyDiscountCode(ChapterBean chapterBean,SeriesBean seriesBean)  {
        BuyComicController buyComicController = new BuyComicController();
        try {
            buyComicController.buyComic(chapterBean,seriesBean, choiceBoxCodes.getValue());
        } catch (InvalidPaymentException | DiscountCodeException e) {
            e.printStackTrace();
        }
    }

    private void closePaymentPane() {
        paymentPane.setVisible(false);
    }
    private void buyComics() {
        paymentPane.setVisible(true);
    }

    private void removeChapterFromRead(SeriesBean seriesBean, ChapterBean chapterBean) {
        System.out.println("[ChapterControllerG] : doing removeChapterFromRead ");
        btnChapterRead.setStyle(STYLE);
        MarkChapterAsReadController controller = new MarkChapterAsReadController();
        controller.unmarkChapterAsRead(seriesBean,chapterBean);
        btnChapterRead.setOnAction(event -> markChapterAsRead(seriesBean,chapterBean));

    }
    private void markChapterAsRead(SeriesBean seriesBean,ChapterBean chapterBean) {
        System.out.println("Doing add chapter as read");
        MarkChapterAsReadController controller = new MarkChapterAsReadController();
        controller.markChapterAsRead(seriesBean,chapterBean);
        btnChapterRead.setStyle(STYLE2);
        btnChapterRead.setOnAction(event -> removeChapterFromRead(seriesBean,chapterBean));
    }

    public void closeBadgeWon() {
        newBadgeWonPane.setVisible(false);
    }

    public void postReview(ChapterBean chapterBean, SeriesBean seriesBean){

        ReviewBean reviewBean = new ReviewBean1();
        reviewBean.setComment(txtAreaComment.getText());
        reviewBean.setAccount(UserLogin.getInstance().getAccount());
        reviewBean.setRating(reviewRating);
        //e magari anche la foto
        PostReviewController postReviewController = new PostReviewController();
        postReviewController.post(reviewBean, chapterBean, seriesBean);

        paneInsertReview.setVisible(false);

    }

    public void openEditor(){

        zeroStars();
        paneInsertReview.setVisible(true);
        txtAreaComment.setText("");
        newBadgeWonPane.setVisible(false);

    }
    @Override
    public void updateReviews(ReviewBean reviewBean) {
        //add della review sulla lista
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("review.fxml"));
        try {
            VBox vbRev = fxmlLoader.load();
            ReviewControllerG reviewControllerG = fxmlLoader.getController();
            reviewControllerG.setData(reviewBean);
            vbReviews.getChildren().add(vbRev);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(BadgeBean badgeBean) {
        newBadgeWonPane.setVisible(true);
        lblBadgeName.setText(badgeBean.getName());
        badgeIconView.setImage(badgeBean.getIcon());
        lblBadgeSeries.setText(chapterSeries);
    }

}
