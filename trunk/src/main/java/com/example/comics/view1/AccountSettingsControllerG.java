package com.example.comics.view1;

import com.example.comics.AccountObserver;
import com.example.comics.AccountSubject;
import com.example.comics.CustomizeProfileController;
import com.example.comics.fagioli.AccountBean;
import com.example.comics.fagioli.ProfileBean;
import com.example.comics.model.Account;
import com.example.comics.model.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class AccountSettingsControllerG implements AccountObserver {
    @FXML
    private Button btnCloseEditorEmail;

    @FXML
    private Button btnCloseEditorFirstName;

    @FXML
    private Button btnCloseEditorLastName;

    @FXML
    private Button btnSaveEmail;

    @FXML
    private Button btnSaveFirstName;

    @FXML
    private Button btnSaveLastName;

    @FXML
    private Pane paneEditEmail;

    @FXML
    private Pane paneEditFirstName;

    @FXML
    private Pane paneEditLastName;

    @FXML
    private TextArea taNewEmail;

    @FXML
    private TextArea taNewFirstName;

    @FXML
    private TextArea taNewLastName;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    public void initialize(){
        paneEditEmail.setVisible(false);
        paneEditFirstName.setVisible(false);
        paneEditLastName.setVisible(false);

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
    void closeFirstNameEditor() {
        paneEditEmail.setVisible(false);
        paneEditFirstName.setVisible(false);
        paneEditLastName.setVisible(false);
    }

    @FXML
    void saveFirstName() {
        AccountBean accountBean = new AccountBean();
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
    void closeLastNameEditor() {
        paneEditEmail.setVisible(false);
        paneEditFirstName.setVisible(false);
        paneEditLastName.setVisible(false);
    }

    @FXML
    void saveLastName() {
        AccountBean accountBean = new AccountBean();
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
    void closeEmailEditor() {
        paneEditEmail.setVisible(false);
        paneEditFirstName.setVisible(false);
        paneEditLastName.setVisible(false);
    }

    @FXML
    void saveEmail() {
        AccountBean accountBean = new AccountBean();
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
