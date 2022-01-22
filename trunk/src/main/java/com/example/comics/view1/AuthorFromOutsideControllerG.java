package com.example.comics.view1;

import com.example.comics.controller.FollowAuthorController;
import com.example.comics.controller.ResearchController;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class AuthorFromOutsideControllerG {

    @FXML
    private Button btnFollow;

    @FXML
    private Label lblAuthorUsername;

    @FXML
    private Label lblName;

    @FXML
    private BorderPane mainPane;

    @FXML
    private ImageView proPicProfile;

    @FXML
    private VBox vBoxSeries;

    public void init(AuthorBean authorBean){

        lblAuthorUsername.setText(authorBean.getUsername());
        lblName.setText(authorBean.getFirstName() + " " + authorBean.getLastName());
        proPicProfile.setImage(authorBean.getProPic());

        if(!UserLogin.getInstance().getAccount().getRole().equals("author")) {
            FollowAuthorController followAuthorController = new FollowAuthorController();
            if (followAuthorController.isAuthorFollowed(authorBean)) {
                setBtnToUnfollow(authorBean);
            } else {
                setBtnToFollow(authorBean);
            }
        }else{
            btnFollow.setVisible(false);
        }

        ResearchController researchController = new ResearchController();
        List<SeriesBean> series = researchController.getPublishedSeries(authorBean);
        for(SeriesBean seriesBean : series){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("hcard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                CardControllerG cardControllerG = fxmlLoader.getController();
                cardControllerG.setData(seriesBean);

                card.setOnMouseClicked(event -> openSerie(seriesBean));

                vBoxSeries.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void openSerie(SeriesBean seriesBean) {

    }

    private void setBtnToUnfollow(AuthorBean authorBean){
        btnFollow.setText("Unfollow");
        btnFollow.setOnAction(actionEvent -> unfollowAuthor(authorBean));
    }

    private void setBtnToFollow(AuthorBean authorBean){
        btnFollow.setOnAction(actionEvent -> followAuthor(authorBean));
        btnFollow.setText("Follow");
    }

    private void unfollowAuthor(AuthorBean authorBean) {
        FollowAuthorController followAuthorController = new FollowAuthorController();
        followAuthorController.unfollowAuthor(authorBean);
        setBtnToFollow(authorBean);
    }

    private void followAuthor(AuthorBean authorBean) {
        FollowAuthorController followAuthorController = new FollowAuthorController();
        followAuthorController.followAuthor(authorBean);
        setBtnToUnfollow(authorBean);
    }

}