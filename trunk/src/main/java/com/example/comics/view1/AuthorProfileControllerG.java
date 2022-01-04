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
    private BorderPane mainPane;

    @FXML
    private ImageView ivProPic;

    @FXML
    private Button btnEdit;

    @FXML
    private Label lblName;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblBirthday;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCountry;

    @FXML
    private Button btnNewSeries;

    @FXML
    private Button btnNewChapter;

    @FXML
    private Button btnNewBadge;

    @FXML
    public void initialize(){
        lblName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblUsername.setText(UserLogin.getInstance().getAccount().getLastName());
        btnEdit.setOnAction(actionEvent -> edit());
        ivProPic.setImage(UserLogin.getInstance().getAccount().getProPic());
        //lblBirthday.setText(UserLogin.getInstance().getAccount().getBirthday().toString());
        //lblCity.setText(UserLogin.getInstance().getAccount().getCity());

        btnNewSeries.setOnAction(actionEvent -> {
            publishNewSerie();
        });

        btnNewChapter.setOnAction(actionEvent -> {
            publishNewChapter();
        });

        btnNewBadge.setOnAction(actionEvent -> {
            createNewBadge();
        });

        AccountSubject.attach(this);
    }

    @FXML
    public void edit(){

        ReaderHomeControllerG.getInstance().openSettings();

    }

    public void publishNewSerie(){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("newseriesmodule");
        mainPane.setCenter(view);
    }

    public void publishNewChapter(){
    }

    public void createNewBadge(){}

    @Override
    public void update() {
        lblName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblUsername.setText(UserLogin.getInstance().getAccount().getUsername());
    }
}
