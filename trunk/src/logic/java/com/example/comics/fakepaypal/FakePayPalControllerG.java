package com.example.comics.fakepaypal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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

    private String firstname;
    private String lastname;
    private String expense;

    private String result = null;

    public FakePayPalControllerG(String firstName, String lastName, String expense){
        firstname = firstName;
        lastname = lastName;
        this.expense = expense;
    }
    public String getResult(){
        return result;
    }

    @FXML
    public void initialize(){
        tfFirstName.setText(firstname);
        tfLastName.setText(lastname);
        lblPrice.setText("Payment: "+ expense +"€");
        btnBuy.setOnAction(this::convalidateCard);
    }

    private void convalidateCard(ActionEvent event) {
        String cardID = tfCard.getText();
        FakePayPal fakePayPal = new FakePayPal();
        if(fakePayPal.isCardValid(cardID)){
            result = "right";
            //magari prima fai tipo vedere che è andata bene bla bla
            close(event);
        }else{
            result = "wrong";
            close(event);
        }
    }

    public void close(ActionEvent event){
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

}
