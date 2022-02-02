package com.example.comics.controller.boundaries;

import com.example.comics.fakepaypal.FakePayPalControllerG;
import com.example.comics.model.exceptions.InvalidPaymentException;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.view1.LoginControllerG;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class BuyComicBoundary {

    public BuyComicBoundary(){
        //costruttore
    }


    public void convalidPayment(AccountBean accountBean) throws InvalidPaymentException {

        FakePayPalControllerG fakePayPalControllerG = new FakePayPalControllerG(accountBean.getFirstName(),accountBean.getLastName());

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = LoginControllerG.class.getResource("paypal.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(fakePayPalControllerG);

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Paypal");
        stage.setScene(scene);
        stage.show();

    }
}
