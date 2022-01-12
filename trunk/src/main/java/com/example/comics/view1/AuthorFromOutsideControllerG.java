package com.example.comics.view1;

import com.example.comics.controller.FollowAuthorController;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.view1.bean1.AuthorBean1;
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

    public void init(AuthorBean authorBean1){

        lblAuthorUsername.setText(authorBean1.getUsername());
        //lblAuthorPlaceHolder.setText(authorBean.getUsername());
        lblName.setText(authorBean1.getFirstName() + " " + authorBean1.getLastName());
        proPicProfile.setImage(authorBean1.getProPic());
        //display serie autore che stanno nel bean

        if(UserLogin.getInstance().getAccount().getRole()!="author") {
            FollowAuthorController followAuthorController = new FollowAuthorController();
            if (followAuthorController.isAuthorFollowed(authorBean1)) {
                setBtnToUnfollow(authorBean1);
            } else {
                setBtnToFollow(authorBean1);
            }
        }else{
            btnFollow.setVisible(false);
        }



    }

    private void setBtnToUnfollow(AuthorBean authorBean1){
        btnFollow.setText("Unfollow");
        btnFollow.setOnAction(actionEvent -> unfollowAuthor(authorBean1));
    }

    private void setBtnToFollow(AuthorBean authorBean1){
        btnFollow.setOnAction(actionEvent -> followAuthor(authorBean1));
        btnFollow.setText("Follow");
    }

    private void unfollowAuthor(AuthorBean authorBean1) {
        FollowAuthorController followAuthorController = new FollowAuthorController();
        followAuthorController.unfollowAuthor(authorBean1);
        setBtnToFollow(authorBean1);
    }

    private void followAuthor(AuthorBean authorBean1) {
        FollowAuthorController followAuthorController = new FollowAuthorController();
        followAuthorController.followAuthor(authorBean1);
        setBtnToUnfollow(authorBean1);
    }

}
