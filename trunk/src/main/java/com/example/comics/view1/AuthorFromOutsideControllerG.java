package com.example.comics.view1;

import com.example.comics.controller.FollowAuthorController;
import com.example.comics.controller.ResearchController;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.AuthorBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class AuthorFromOutsideControllerG {
    @FXML
    private BorderPane mainPane;

    @FXML
    private ImageView proPicProfile;

    @FXML
    private Button btnEdit;

    @FXML
    private Label lblName;

    @FXML
    private Label lblUsername;
    @FXML
    private Button btnFollow;

    @FXML
    private Label lblAuthorUsername;

    @FXML
    private Label lblName1;

    @FXML
    private Label lblAuthorPlaceHolder;

    public void init(AccountBean accountBean){
        ResearchController researchController = new ResearchController();
        AuthorBean authorBean = researchController.getAuthor(accountBean);
        lblAuthorUsername.setText(authorBean.getUsername());
        lblAuthorPlaceHolder.setText(authorBean.getUsername());
        lblName.setText(authorBean.getFirstName() + " " + authorBean.getLastName());
        proPicProfile.setImage(authorBean.getProPic());

        FollowAuthorController followAuthorController = new FollowAuthorController();
        if(followAuthorController.isAuthorFollowed(authorBean)){
            setBtnToUnfollow(authorBean);
        }else{
            setBtnToFollow(authorBean);
        }



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
