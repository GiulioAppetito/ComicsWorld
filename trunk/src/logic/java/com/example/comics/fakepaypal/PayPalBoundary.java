package com.example.comics.fakepaypal;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayPalBoundary implements PayPalInterface{

    private FakePayPalControllerG fakePayPalControllerG = null;

    @Override
    public void startTransaction(String firstName, String lastName, String expense) {
        //facciamo partire la finta scene
        fakePayPalControllerG = new FakePayPalControllerG(firstName,lastName, expense);

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = PayPalBoundary.class.getResource("fakepaypal.fxml");
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

    @Override
    public int convalidPayment() {

        String res = fakePayPalControllerG.getResult();

        if(res == null){

            Logger.getGlobal().log(Level.FINEST, "thread waiting");
            Logger.getAnonymousLogger().log(Level.FINE, "waiting");

            return 0;
        }
        if(res.equals("right")){
            return 1;
        }
        if(res.equals("wrong")){
            return 2;
        }else{
            return 0;
        }

    }
}
