package com.example.comics.view1;

import com.example.comics.AccountObserver;
import com.example.comics.AccountSubject;
import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import tools.FxmlLoader;

public class AuthorProfileControllerG implements AccountObserver {

    @FXML
    private Button btnEdit;
    @FXML
    public Label lblName;
    @FXML
    public Label lblUsername;
    @FXML
    public Button btnNewSeries;
    @FXML
    private BorderPane mainPane;

    @FXML
    public void initialize(){
        lblName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblUsername.setText(UserLogin.getInstance().getAccount().getLastName());
        btnEdit.setOnAction(actionEvent -> edit());
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


    @Override
    public void update() {
        lblName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblUsername.setText(UserLogin.getInstance().getAccount().getUsername());
    }
}
