package com.example.comics.view2;

import com.example.comics.controller.LoginController;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.LoginBean;
import com.example.comics.view1.AuthorHomeControllerG;
import com.example.comics.view1.ReaderHomeControllerG;
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

    public void login(ActionEvent event) throws Exception {
        LoginBean loginBean = new LoginBean2();

        loginBean.setPassword(txtFieldPassword.getText());
        loginBean.setEmail(txtFieldUsername.getText());

        LoginController loginController = new LoginController();

        if(loginController.login(loginBean)) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation;
            Scene scene;

            String role = UserLogin.getInstance().getAccount().getRole();

            if(role.equals("reader")) {
                ReaderHomeControllerG readerHomeControllerG = ReaderHomeControllerG.getInstance();

                fxmlLocation = ReaderHomeControllerG.class.getResource("readerhome.fxml");
                loader.setLocation(fxmlLocation);
                loader.setController(readerHomeControllerG);
                scene = new Scene(loader.load());
                readerHomeControllerG.init();
            }
            else{
                AuthorHomeControllerG authorHomeControllerG = AuthorHomeControllerG.getInstance();

                fxmlLocation = AuthorHomeControllerG.class.getResource("authorhome.fxml");
                loader.setLocation(fxmlLocation);
                loader.setController(authorHomeControllerG);
                scene = new Scene(loader.load());
                authorHomeControllerG.init();
            }

            stage.setTitle("ComicsWorld");
            stage.setScene(scene);
            stage.show();


            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
    }

    public void clickRegister(){
        //TO-DO
    }
}

