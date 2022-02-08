package com.example.comics.view1;

import com.example.comics.model.AccountObserver;
import com.example.comics.model.AccountSubject;
import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import tools.FxmlLoader;

public class AuthorProfileControllerG implements AccountObserver {

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnNewChapter;

    @FXML
    private Button btnNewSeries;

    @FXML
    private Label lblName;

    @FXML
    private Label lblUsername;

    @FXML
    private BorderPane mainPane;

    @FXML
    private ImageView proPicProfile;


    @FXML
    public void initialize(){
        lblName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblUsername.setText(UserLogin.getInstance().getAccount().getLastName());
        btnEdit.setOnAction(actionEvent -> edit());
        proPicProfile.setImage(UserLogin.getInstance().getAccount().getProPic());
        btnNewSeries.setOnAction(actionEvent -> publishNewSerie());

        btnNewChapter.setOnAction(actionEvent -> publishNewChapter());


        AccountSubject.attach(this);
    }

    @FXML
    private void publishNewChapter() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("publishchapter");
        mainPane.setCenter(view);
    }

    @FXML
    public void edit(){

        HomeControllerG homeControllerG = HomeFactory.getHomeControllerG();
        homeControllerG.openSettings();

    }


    @FXML
    private void publishNewSerie(){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("publishseries");
        mainPane.setCenter(view);
    }

    @Override
    public void update() {
        lblName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblUsername.setText(UserLogin.getInstance().getAccount().getUsername());
    }
}
