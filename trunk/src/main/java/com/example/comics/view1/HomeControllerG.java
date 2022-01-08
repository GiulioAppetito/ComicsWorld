package com.example.comics.view1;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public abstract class HomeControllerG {

    public abstract void changeCenter(Pane pane);

    public abstract void resetButtons();

    public void openFeed(){
        FeedControllerG feedControllerG = FeedControllerG.getInstance();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = HomeControllerG.class.getResource("feed.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(feedControllerG);

        Pane view = null;
        try {
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        feedControllerG.init();

        changeCenter(view);

        resetButtons();
    };

}
