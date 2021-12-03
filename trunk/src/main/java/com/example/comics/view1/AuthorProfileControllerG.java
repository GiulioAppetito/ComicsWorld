package com.example.comics.view1;

import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import tools.FxmlLoader;

public class AuthorProfileControllerG {

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
        lblName.setText(UserLogin.getAccount().getFirstName());
        lblUsername.setText(UserLogin.getAccount().getLastName());
        btnEdit.setOnAction(actionEvent -> edit());
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



}
