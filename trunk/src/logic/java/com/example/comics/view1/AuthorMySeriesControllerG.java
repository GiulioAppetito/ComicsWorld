package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class AuthorMySeriesControllerG {

    @FXML
    private GridPane gpMySeries;

    public void initialize(){

        ResearchController researchController = new ResearchController();
        List<SeriesBean> listOfCards = researchController.getPublishedSeries();

        FXMLLoader fxmlLoader;

        int size = listOfCards.size();
        int columns = 3;
        int i=1;
        for(int j=0; j<size; j++) {

            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                CardControllerG cardController = fxmlLoader.getController();
                cardController.setData(listOfCards.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> openSerie(listOfCards.get(finalJ)));

                gpMySeries.add(card,j%columns,i);
                if(j%columns == columns-1){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void openSerie(SeriesBean seriesBean){
        HomeControllerG homeControllerG = HomeFactory.getHomeControllerG();
        homeControllerG.openSeries(seriesBean);

    }
}
