package com.example.comics;

import com.example.comics.fagioli.LoginBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tools.FxmlLoader;

import java.io.IOException;


public class LoginControllerG {

    @FXML
    public Button btnRegister;

    @FXML
    public BorderPane loginPane;

    @FXML
    public BorderPane registrationPane;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfPassword;

    //register
    @FXML
    private Button btnLogin;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldName;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private TextField textFieldSurname;

    @FXML
    private TextField textFieldUsername;


    public void initialize(){
        clickLogin();
    }

    public void clickRegister(){
        loginPane.setVisible(false);
        registrationPane.setVisible(true);
    }
    public void clickLogin(){
        loginPane.setVisible(true);
        registrationPane.setVisible(false);
    }


    public void clickCancel() {
        tfPassword.setText("");
        tfEmail.setText("");
    }
    public void cancelForm(){
        textFieldName.setText("");
        textFieldSurname.setText("");
        textFieldUsername.setText("");
        textFieldEmail.setText("");
        textFieldPassword.setText("");
    }

    public void login(ActionEvent event) throws IOException {
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
