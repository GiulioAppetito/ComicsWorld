package com.example.comics.fakepaypal;

import com.example.comics.view1.LoginControllerG;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class FakePayPalControllerG {

    @FXML
    private Button btnBuy;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnCloseErrorPane;

    @FXML
    private TextField tfFirstName;

    @FXML
    private Pane errorPane;

    @FXML
    private Label lblPrice;

    @FXML
    private TextField tfCard;

    @FXML
    private TextField tfLastName;

    private static String FIRSTNAME;
    private static String LASTNAME;

    public FakePayPalControllerG(String firstName, String lastName){
        FIRSTNAME = firstName;
        LASTNAME = lastName;
    }

    @FXML
    public void initialize(){
        errorPane.setVisible(false);
        tfFirstName.setText(FIRSTNAME);
        tfLastName.setText(LASTNAME);
        btnBuy.setOnAction(event -> convalidateCard());
        btnClose.setOnAction(this::close);
        btnCloseErrorPane.setOnAction(event -> closeErrorPane());
    }

    private void close(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void closeErrorPane() {
        errorPane.setVisible(false);
    }

    private boolean convalidateCard() {
        String cardID = tfCard.getText();
        FakePayPal fakePayPal = new FakePayPal();
        return fakePayPal.isCardValid(cardID);
    }

}
