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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class LoginControllerG {

    @FXML
    public Button btnLogin;

    @FXML
    public Button btnCancel;

    @FXML
    public Button btnBackToRegister;

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
    private Button btnBackToLogin;

    @FXML
    private Button btnCancelForm;

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


    public LoginControllerG(){
    }

    public void init(){
        clickLogin();
        btnLogin.setOnAction(event ->
        {
            try {
                login(event);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        btnBackToRegister.setOnAction(event -> clickRegister());
        btnBackToLogin.setOnAction(event -> clickLogin());
        btnCancelForm.setOnAction(event -> cancelForm());
        btnCancel.setOnAction(event -> clickCancel());
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

            HomeControllerG homeControllerG = HomeControllerG.getInstance();
            FXMLLoader loader = new FXMLLoader();

            URL fxmlLocation = HomeControllerG.class.getResource("home.fxml");
            loader.setLocation(fxmlLocation);
            loader.setController(homeControllerG);

            Scene scene = new Scene(loader.load());
            homeControllerG.init();
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
