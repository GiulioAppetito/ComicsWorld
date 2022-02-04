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
    private TextField tfFirstName;


    @FXML
    private Label lblPrice;

    @FXML
    private TextField tfCard;

    @FXML
    private TextField tfLastName;

    private static String FIRSTNAME;
    private static String LASTNAME;
    private static String EXPENSE;

    private String result = null;

    public FakePayPalControllerG(String firstName, String lastName, String expense){
        FIRSTNAME = firstName;
        LASTNAME = lastName;
        EXPENSE = expense;
    }
    public String getResult(){
        return result;
    }

    @FXML
    public void initialize(){
        tfFirstName.setText(FIRSTNAME);
        tfLastName.setText(LASTNAME);
        lblPrice.setText("Payment: "+EXPENSE+"€");
        btnBuy.setOnAction(event -> convalidateCard(event));
    }

    private void convalidateCard(ActionEvent event) {
        System.out.println("convalidating card");
        String cardID = tfCard.getText();
        FakePayPal fakePayPal = new FakePayPal();
        if(fakePayPal.isCardValid(cardID)){
            result = "right";
            //magari prima fai tipo vedere che è andata bene bla bla
            close(event);
        }else{
            result = "wrong";
        }
        System.out.println("result: "+result);
    }

    public void close(ActionEvent event){
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

}
