package com.example.comics.view1;

import com.example.comics.LoginController;
import com.example.comics.fagioli.LoginBean;
import com.example.comics.fagioli.WrongCredentialException;
import com.example.comics.model.UserLogin;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


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

    //error pane
    @FXML
    private Pane errorPane;
    @FXML
    private Button btnCloseErrorMessage;

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
        //costruttore
    }

    public void init(){
        clickLogin();
        errorPane.setVisible(false);
        btnLogin.setOnAction(event ->
        {
            try {
                login(event);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        btnBackToRegister.setOnAction(event -> clickRegister());
        btnBackToLogin.setOnAction(event -> clickLogin());
        btnCancelForm.setOnAction(event -> cancelForm());
        btnCancel.setOnAction(event -> clickCancel());
        btnCloseErrorMessage.setOnAction(event -> closeErrorMessage());

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


    public void login(ActionEvent event) throws Exception {
        LoginBean loginBean = new LoginBean();

        try {
            loginBean.setEmail(tfEmail.getText());
        } catch (WrongCredentialException e) {
            showErrorMessage();
            return;
        }
        loginBean.setPassword(tfPassword.getText());
        LoginController loginController = new LoginController();

        if(loginController.login(loginBean)) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation;
            Scene scene;

            String role = UserLogin.getAccount().getRole();
            if(Objects.equals(role, "reader")) {
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
        else{
            clickCancel();
        }
    }

    private void showErrorMessage() {
        errorPane.setVisible(true);
    }

    void closeErrorMessage() {
        errorPane.setVisible(false);
    }

}
