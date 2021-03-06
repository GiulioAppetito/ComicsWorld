package com.example.comics.view1;

import com.example.comics.model.AccountObserver;
import com.example.comics.model.AccountSubject;
import com.example.comics.controller.CustomizeProfileController;
import com.example.comics.model.UserLogin;
import com.example.comics.view1.beans.AccountBean1;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class AccountSettingsControllerG implements AccountObserver {
    @FXML
    private Label lblFirstName;

    @FXML
    private Button btnEditProPic;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblEmail;

    @FXML
    private Pane paneEditFirstName;

    @FXML
    private Button btnCloseEditorFirstName;

    @FXML
    private TextArea taNewFirstName;

    @FXML
    private Button btnSaveFirstName;

    @FXML
    private Pane paneEditEmail;

    @FXML
    private Button btnCloseEditorEmail;

    @FXML
    private TextArea taNewEmail;

    @FXML
    private Button btnSaveEmail;

    @FXML
    private Pane paneEditLastName;

    @FXML
    private Button btnCloseEditorLastName;

    @FXML
    private TextArea taNewLastName;

    @FXML
    private Button btnSaveLastName;


    @FXML
    public void initialize(){
        closeEditor();

        lblFirstName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblLastName.setText(UserLogin.getInstance().getAccount().getLastName());
        lblEmail.setText(UserLogin.getInstance().getAccount().getEmail());
        //stessa cosa con la propic

        AccountSubject.attach(this);
    }



    @FXML
    void editFirstName(){
        paneEditEmail.setVisible(false);
        paneEditFirstName.setVisible(true);
        paneEditLastName.setVisible(false);
    }

    @FXML
    void closeEditor() {
        paneEditEmail.setVisible(false);
        paneEditFirstName.setVisible(false);
        paneEditLastName.setVisible(false);
    }

    @FXML
    void saveFirstName() {
        AccountBean1 accountBean = new AccountBean1();
        accountBean.setFirstName(taNewFirstName.getText());

        CustomizeProfileController customizeProfileController = new CustomizeProfileController();
        customizeProfileController.changeFirstName(accountBean);

    }

    @FXML
    void editLastName(){
        paneEditEmail.setVisible(false);
        paneEditFirstName.setVisible(false);
        paneEditLastName.setVisible(true);
    }

    @FXML
    void saveLastName() {
        AccountBean1 accountBean = new AccountBean1();
        accountBean.setLastName(taNewLastName.getText());

        CustomizeProfileController customizeProfileController = new CustomizeProfileController();
        customizeProfileController.changeLastName(accountBean);
    }

    @FXML
    void editEmail(){
        paneEditEmail.setVisible(true);
        paneEditFirstName.setVisible(false);
        paneEditLastName.setVisible(false);
    }

    @FXML
    void saveEmail() {
        AccountBean1 accountBean = new AccountBean1();
        accountBean.setEmail(taNewEmail.getText());

        CustomizeProfileController customizeProfileController = new CustomizeProfileController();
        customizeProfileController.changeEmail(accountBean);
    }

    @Override
    public void update() {
        paneEditEmail.setVisible(false);
        paneEditLastName.setVisible(false);
        paneEditFirstName.setVisible(false);
        lblFirstName.setText(UserLogin.getInstance().getAccount().getFirstName());
        lblLastName.setText(UserLogin.getInstance().getAccount().getLastName());
        lblEmail.setText(UserLogin.getInstance().getAccount().getEmail());
    }

}
