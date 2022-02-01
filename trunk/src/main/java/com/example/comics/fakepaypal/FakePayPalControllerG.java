package com.example.comics.fakepaypal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


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

    public FakePayPalControllerG(String firstName, String lastName){
        errorPane.setVisible(false);
        tfFirstName.setText(firstName);
        tfLastName.setText(lastName);
        btnBuy.setOnAction(event -> convalidateCard());
        btnClose.setOnAction(event -> close(event));
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
