package com.example.comics.view1;

import com.example.comics.PostReviewController;
import com.example.comics.fagioli.ChapterBean;
import com.example.comics.fagioli.ReviewBean;
import com.example.comics.model.Review;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChapterControllerG {

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


    public void init(ChapterBean chapterBean){

        lblAuthor.setText("autore");
        lblChapterTitle.setText(chapterBean.getTitle());
        lblChapterId.setText(chapterBean.getId().toString());

        paneInsertReview.setVisible(false);

        btnCloseEditor.setOnAction(event ->
                closeEditor());

        btnAddReview.setOnAction(event ->
                openEditor());

        btnPostReview.setOnAction(event ->
                postReview());

        ArrayList<ReviewBean> listOfReviews = null;
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
                //feedControllerG.openSerie();
                //per ora rimando poi i penso

                HomeFactory homeFactory = new HomeFactory();
                HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
                homeControllerG.openFeed();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }



    public void postReview(){
        ReviewBean reviewBean = new ReviewBean();
        reviewBean.setComment(txtAreaComment.getText());
        reviewBean.setUsername(UserLogin.getAccount().getUsername());
        //e magari anche la foto
        PostReviewController postReviewController = new PostReviewController();
        postReviewController.post(reviewBean);
    }


    public void openEditor(){
        paneInsertReview.setVisible(true);
    }

    @FXML
    private Button btnCloseEditor;

    @FXML
    void closeEditor() {
        paneInsertReview.setVisible(false);
    }



}
