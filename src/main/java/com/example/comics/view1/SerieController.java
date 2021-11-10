package com.example.comics.view1;

import com.example.comics.view1.HomeControllerG;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SerieController {

    @FXML
    public Button btnBack;

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
    }
}
