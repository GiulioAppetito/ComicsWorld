package com.example.comics.view1;

import com.example.comics.controller.LoginController;
import com.example.comics.controller.RegistrationController;
import com.example.comics.model.exceptions.FailedLoginException;
import com.example.comics.model.exceptions.FailedRegistrationException;
import com.example.comics.model.exceptions.MalformedEmailException;
import com.example.comics.model.fagioli.LoginBean;
import com.example.comics.model.fagioli.RegistrationBean;
import com.example.comics.view1.beans.LoginBean1;
import com.example.comics.view1.beans.RegistrationBean1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginControllerG {

    @FXML
    public Button btnLogin;
    @FXML
    private Label errorPaneLabel;
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
    private Button registerButton;
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
    @FXML
    private RadioButton radioBtnReader;

    @FXML
    private RadioButton radioBtnAuthor;


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

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        btnBackToRegister.setOnAction(event -> clickRegister());
        registerButton.setOnAction(event -> clickRegisterButton());
        btnBackToLogin.setOnAction(event -> clickLogin());
        btnCancelForm.setOnAction(event -> cancelForm());
        btnCancel.setOnAction(event -> clickCancel());
        btnCloseErrorMessage.setOnAction(event -> closeErrorMessage());

    }

    public void clickRegister(){
        loginPane.setVisible(false);
        registrationPane.setVisible(true);
        cancelForm();
    }

    public void clickRegisterButton(){

        RegistrationBean registrationBean = new RegistrationBean1();

        registrationBean.setFirstName(textFieldName.getText());
        registrationBean.setLastName(textFieldSurname.getText());
        registrationBean.setUsername(textFieldUsername.getText());
        registrationBean.setEmail(textFieldEmail.getText());
        registrationBean.setPassword(textFieldPassword.getText());

        if(radioBtnAuthor.isSelected()){
            registrationBean.setRole("author");
        }else if(radioBtnReader.isSelected()){
            registrationBean.setRole("reader");
        }

        RegistrationController registrationController = new RegistrationController();
        try{
            registrationController.registerNewAccount(registrationBean);
        }catch(FailedRegistrationException e){
            showErrorMessage(e.getMessage());
            textFieldUsername.setText("");
        } catch (MalformedEmailException e) {
            showErrorMessage(e.getMessage());
            textFieldEmail.setText("");
        }
    }


    public void clickLogin(){
        loginPane.setVisible(true);
        registrationPane.setVisible(false);
        clickCancel();
    }

    public void authorSelected(){
        radioBtnReader.setSelected(false);
    }

    public void readerSelected(){
        radioBtnAuthor.setSelected(false);
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
        LoginBean loginBean = new LoginBean1();

        loginBean.setEmail(tfEmail.getText());
        loginBean.setPassword(tfPassword.getText());

        LoginController loginController = new LoginController();
        try {
            if (loginController.login(loginBean)) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Scene scene;

                HomeFactory homeFactory = new HomeFactory();
                HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
                loader.setLocation(homeControllerG.getLocation());
                loader.setController(homeControllerG);
                scene = new Scene(loader.load());
                homeControllerG.init();

                stage.setTitle("ComicsWorld");
                stage.setScene(scene);
                stage.show();
                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            } else {
                clickCancel();
            }
        }catch(FailedLoginException e){
            showErrorMessage(e.getMessage());
        }
    }

    private void showErrorMessage(String message) {
        errorPane.setVisible(true);
        errorPaneLabel.setText(message);
    }

    void closeErrorMessage() {
        errorPane.setVisible(false);
    }

}
