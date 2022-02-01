package com.example.comics.fakepaypal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;

public class FakePayPalControllerG {

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private TextField tfCard;

    @FXML
    private Button btnConvalidate;

    public FakePayPalControllerG(String firstName, String lastName){
        lblFirstName.setText(firstName);
        lblLastName.setText(lastName);
        btnConvalidate.setOnAction(event -> convalidateCard());
    }

    private boolean convalidateCard() {
        String cardID = tfCard.getText();
        FakePayPal fakePayPal = new FakePayPal();
        return fakePayPal.isCardValid(cardID);
    }


}
