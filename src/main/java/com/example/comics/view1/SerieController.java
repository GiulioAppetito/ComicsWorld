package com.example.comics.view1;

import com.example.comics.view1.HomeControllerG;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;

public class SerieController {

    @FXML
    public Button btnBack;

    @FXML
    private Button btnCheck;

    public void init() {

        btnBack.setOnAction(event -> {
            //ha senso che serie conosca home per tornare sul feed?
            //non so, secondo me basterebbe andare dinuovo su feed facendo tipo un refresh
            //però che ne sa il feed che deve sostituire il centro della home?
            //boh
            HomeControllerG homeControllerG = HomeControllerG.getInstance();
            try {
                homeControllerG.openFeed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnCheck.setOnAction(event ->
        {
            try {
                openChapter();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        );
    }

    private void openChapter() throws IOException {

        ChapterControllerG chapterControllerG = new ChapterControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = ChapterControllerG.class.getResource("comicchapter.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(chapterControllerG);

        HomeControllerG homeControllerG = HomeControllerG.getInstance();
        homeControllerG.changeCenter(loader.load());

        chapterControllerG.init();

    }
}
