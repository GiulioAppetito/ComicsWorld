package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class ReadingControllerG {

    @FXML
    private GridPane gpComic;

    public void initialize(){

        ResearchController researchController = new ResearchController();
        List<SeriesBean> listOfCards = researchController.getReadingSeries();

        int size = listOfCards.size();
        int columns = 3;
        int i=1;
        for(int j=0; j<size; j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                CardControllerG cardController = fxmlLoader.getController();
                cardController.setData(listOfCards.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> openReadingSerie(listOfCards.get(finalJ)));

                gpComic.add(card,j%columns,i);
                if(j%columns == columns-1){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void openReadingSerie(SeriesBean seriesBean){
        ReaderHomeControllerG readerHomeControllerG = ReaderHomeControllerG.getInstance();
        readerHomeControllerG.openSeries(seriesBean);
    }

}
