package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class ToReadControllerG {

    @FXML
    private GridPane gpComics;


    public void initialize(){

        ResearchController researchController = new ResearchController();

        List<SeriesBean> listOfSeriesCards = researchController.getToReadSeries();

        int columns = 3;
        int i=1;
        for(int j=0; j<listOfSeriesCards.size(); j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                CardControllerG cardController = fxmlLoader.getController();
                cardController.setData(listOfSeriesCards.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> openSerie(listOfSeriesCards.get(finalJ)));

                gpComics.add(card,j%columns,i);
                if(j%columns == columns-1){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void openSerie(SeriesBean seriesBean){
        ReaderHomeControllerG readerHomeControllerG = ReaderHomeControllerG.getInstance();
        readerHomeControllerG.openSeries(seriesBean);
    }
}