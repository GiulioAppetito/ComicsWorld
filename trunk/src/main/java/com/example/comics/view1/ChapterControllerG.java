package com.example.comics.view1;

import com.example.comics.PostReviewController;
import com.example.comics.fagioli.ChapterBean;
import com.example.comics.fagioli.ObjectiveBean;
import com.example.comics.fagioli.ReviewBean;
import com.example.comics.model.ReviewObserver;
import com.example.comics.model.ReviewSubject;
import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ChapterControllerG implements ReviewObserver {

    @FXML
    private VBox vbReviews;

    @FXML
    private Button btnAddReview;

    @FXML
    private Pane paneInsertReview;

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
    void closeEditor() {
        paneInsertReview.setVisible(false);
    }


    public void init(ChapterBean chapterBean){

        ReviewSubject.attach(this);

        lblAuthor.setText("autore");
        lblChapterTitle.setText(chapterBean.getTitle());
        lblChapterId.setText(chapterBean.getId().toString());

        paneInsertReview.setVisible(false);
        newBadgeWonPane.setVisible(false);

        btnCloseEditor.setOnAction(event -> closeEditor());
        btnCloseBadge.setOnAction(event -> closeBadgeWon());

        btnAddReview.setOnAction(event -> openEditor());

        btnPostReview.setOnAction(event -> postReview(chapterBean));

        List<ReviewBean> listOfReviews = null;
        try {
            listOfReviews = chapterBean.getReviews();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (ReviewBean reviewBean : listOfReviews) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("review.fxml"));
            try {
                VBox vbRev = fxmlLoader.load();
                ReviewControllerG reviewController = fxmlLoader.getController();
                reviewController.setData(reviewBean);
                vbRev.setOnMouseClicked(event -> System.out.println("Clicked ad"));
                vbReviews.getChildren().add(vbRev);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        btnBack.setOnAction(event -> {

            FeedControllerG feedControllerG = new FeedControllerG();
            try {
                HomeFactory homeFactory = new HomeFactory();
                HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
                homeControllerG.openFeed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void closeBadgeWon() {
        newBadgeWonPane.setVisible(false);
    }

    public void postReview(ChapterBean chapterBean){
        ReviewBean reviewBean = new ReviewBean();
        reviewBean.setComment(txtAreaComment.getText());
        reviewBean.setUsername(UserLogin.getInstance().getAccount().getUsername());
        System.out.println("ChapterControllerG: role: username: "+ UserLogin.getInstance().getAccount().getUsername());
        reviewBean.setSeries(chapterBean.getSeries());
        reviewBean.setChapter(chapterBean.getTitle());
        //e magari anche la foto
        PostReviewController postReviewController = new PostReviewController();
        postReviewController.post(reviewBean);

        paneInsertReview.setVisible(false);
    }

    public void openEditor(){
        paneInsertReview.setVisible(true);
        newBadgeWonPane.setVisible(false);
    }

    @Override
    public void update(ReviewBean reviewBean) {
        //add della review sulla lista
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("review.fxml"));
        try {
            VBox vbRev = fxmlLoader.load();
            ReviewControllerG reviewController = fxmlLoader.getController();
            reviewController.setData(reviewBean);
            vbRev.setOnMouseClicked(event -> System.out.println("Clicked review"));
            vbReviews.getChildren().add(vbRev);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void achievedObjective(ObjectiveBean objectiveBean) {
        //mostra panel con vittoria badge
        newBadgeWonPane.setVisible(true);
        lblBadgeName.setText(objectiveBean.getBadgeName());
        lblBadgeSeries.setText(objectiveBean.getSeriesTitle());
    }
}
