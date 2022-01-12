package com.example.comics.view1;

import com.example.comics.model.AccountSubject;
import com.example.comics.controller.CustomizeProfileController;
import com.example.comics.model.AccountObserver;
import com.example.comics.model.fagioli.ProfileBean;
import com.example.comics.model.UserLogin;
import com.example.comics.view1.beans.ProfileBean1;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ProfileSettingsControllerG implements AccountObserver {

    @FXML
    private Button btnCloseEditorProPic;

    @FXML
    private Button btnCloseEditorUsername;

    @FXML
    private Button btnEditProPic;

    @FXML
    private Button btnEditUsername;

    @FXML
    private Pane paneEditProPic;

    @FXML
    private Pane paneEditUsername;

    @FXML
    private Button btnSaveUsername;

    @FXML
    private TextArea taNewUsername;

    @FXML
    private ImageView imgProPic;

    @FXML
    private Label lblUsername;

    public void initialize(){
        paneEditUsername.setVisible(false);
        paneEditProPic.setVisible(false);

        lblUsername.setText(UserLogin.getInstance().getAccount().getUsername());
        //stessa cosa con la propic

        AccountSubject.attach(this);
    }

    @FXML
    void closeEditor() {
        paneEditUsername.setVisible(false);
        paneEditProPic.setVisible(false);
    }

    @FXML
    void editProPic() {
        paneEditUsername.setVisible(false);
        paneEditProPic.setVisible(true);
    }

    @FXML
    void editUsername() {
        paneEditUsername.setVisible(true);
        paneEditProPic.setVisible(false);
    }

    @FXML
    public void saveUsername(){
        ProfileBean profileBean = new ProfileBean1();
        profileBean.setUsername(taNewUsername.getText());

        CustomizeProfileController customizeProfileController = new CustomizeProfileController();
        customizeProfileController.changeUsername(profileBean);
    }

    @Override
    public void update() {
        paneEditUsername.setVisible(false);
        paneEditProPic.setVisible(false);

        lblUsername.setText(UserLogin.getInstance().getAccount().getUsername());
    }
}
