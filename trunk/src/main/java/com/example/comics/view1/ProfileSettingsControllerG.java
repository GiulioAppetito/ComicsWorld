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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileSettingsControllerG implements AccountObserver {

    @FXML
    private ImageView imgProPic;

    @FXML
    private Button btnEditProPic;

    @FXML
    private Label lblUsername;

    @FXML
    private Button btnEditUsername;

    @FXML
    private Pane paneEditUsername;

    @FXML
    private Button btnCloseEditorUsername;

    @FXML
    private TextArea taNewUsername;

    @FXML
    private Button btnSaveUsername;

    private String imageCoverPath;

    public void initialize(){
        paneEditUsername.setVisible(false);

        lblUsername.setText(UserLogin.getInstance().getAccount().getUsername());
        //stessa cosa con la propic
        btnEditProPic.setOnAction(event -> changeProPic());
        imgProPic.setImage(UserLogin.getInstance().getAccount().getProPic());

        AccountSubject.attach(this);
    }

    private void changeProPic() {
        FileChooser fc = new FileChooser();
        InputStream inputStream = null;
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.png","*.png"));
        File f = fc.showOpenDialog(null);
        if(f!=null){
            imageCoverPath = f.getAbsolutePath();
            System.out.println("Cover path is : "+imageCoverPath);

            try {
                inputStream = new FileInputStream(imageCoverPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image cover = new Image(inputStream);
            imgProPic.setImage(cover);
            UserLogin.getInstance().getAccount().setProPic(cover);
        }
        CustomizeProfileController controller = new CustomizeProfileController();
        ProfileBean profileBean = new ProfileBean1();
        profileBean.setInputStream(inputStream);
        profileBean.setProPic(imgProPic.getImage());
        controller.changeProPic(profileBean);

    }

    @FXML
    void closeEditor() {
        paneEditUsername.setVisible(false);

    }

    @FXML
    void editProPic() {
        paneEditUsername.setVisible(false);

    }

    @FXML
    void editUsername() {
        paneEditUsername.setVisible(true);}

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

        lblUsername.setText(UserLogin.getInstance().getAccount().getUsername());
    }
}
