package com.example.comics.controller;

import com.example.comics.fakeamazon.FakeAmazonBoundary;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class BuyComicController {
    public void init(ChapterBean chapterBean, SeriesBean seriesBean){
        openFakeAmazonWindow(chapterBean,seriesBean);
    }

    private void openFakeAmazonWindow(ChapterBean chapterBean,SeriesBean seriesBean) {
        Stage stage = new Stage();
        FakeAmazonBoundary fakeAmazonBoundary = new FakeAmazonBoundary();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = FakeAmazonBoundary.class.getResource("fakeamazon.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(fakeAmazonBoundary);

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fakeAmazonBoundary.init(chapterBean,seriesBean);

        stage.setTitle("FakeAmazon");
        stage.setScene(scene);
        stage.show();
    }


}
