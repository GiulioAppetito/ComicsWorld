package com.example.comics.view2;

import com.example.comics.controller.LoginController;
import com.example.comics.model.exceptions.FailedLoginException;
import com.example.comics.model.fagioli.LoginBean;
import com.example.comics.view2.beans.LoginBean2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginControllerG2 {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private HBox hBoxToRegistration;

    public void init(){
        btnLogin.setOnAction(event -> {
            try {
                login(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        hBoxToRegistration.setOnMouseClicked(event -> clickRegister());
    }

    public void login(ActionEvent event) {
        LoginBean loginBean = new LoginBean2();

        loginBean.setPassword(txtFieldPassword.getText());
        loginBean.setEmail(txtFieldUsername.getText());

        LoginController loginController = new LoginController();

        try {
            if(loginController.login(loginBean)) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                URL fxmlLocation;
                Scene scene;

                    FeedControllerG2 feedControllerG2 = FeedControllerG2.getInstance();

                    fxmlLocation = FeedControllerG2.class.getResource("feed2.fxml");
                    loader.setLocation(fxmlLocation);
                    loader.setController(feedControllerG2);
                    scene = new Scene(loader.load());
                    feedControllerG2.init();


                stage.setTitle("ComicsWorld Mobile");
                stage.setScene(scene);
                stage.setMaxHeight(705);
                stage.setMaxWidth(425);
                stage.show();


                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            }
        } catch (FailedLoginException | IOException e) {
            e.printStackTrace();
        }
    }

    public void clickRegister(){
        //TO-DO
    }
}

