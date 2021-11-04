package com.example.comics;

import com.example.comics.fagioli.LoginBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tools.FxmlLoader;

import java.io.IOException;

public class LoginControllerG {

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfPassword;


    public void clickCancel() {
        tfPassword.setText("");
        tfEmail.setText("");
    }

    public void clickLogin(ActionEvent event) throws IOException {
        LoginBean loginBean = new LoginBean();
        loginBean.setEmail(tfEmail.getText());
        loginBean.setPassword(tfPassword.getText());
        if(loginBean.validate()) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
            stage.setTitle("ComicsWorld");
            stage.setScene(scene);
            stage.show();

            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
        else{
            clickCancel();
        }
    }

}
