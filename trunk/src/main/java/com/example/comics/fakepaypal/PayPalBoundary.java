package com.example.comics.fakepaypal;

import com.example.comics.view1.LoginControllerG;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class PayPalBoundary implements PayPalInterface{

    private FakePayPalControllerG fakePayPalControllerG = null;

    //magari per fare i fighetti mettiamo qualcosa di diverso da due stupide stringhe
    @Override
    public void startTransaction(String firstName, String lastName) {
        //facciamo partire la finta scene
        fakePayPalControllerG = new FakePayPalControllerG(firstName,lastName);

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

        if (res == null) {
            System.out.println("PAYPALBOUNDARY: "+ "null");
            return 0;
        }
        if(res.equals("right")){
            System.out.println("PAYPALBOUNDARY: "+ "right");
            return 1;
        }
        if(res.equals("wrong")){
            System.out.println("PAYPALBOUNDARY: "+ "wrong");
            return 2;
        }else{
            return 0;
        }

    }
}
