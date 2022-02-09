package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class FavouritesControllerG {

    @FXML
    private GridPane gpFavComic;

    public void initialize(){

        ResearchController researchController = new ResearchController();
        List<SeriesBean> listOfCards = researchController.getFavouriteSeries();
        FXMLLoader fxmlLoader;
        VBox favCard;

        int i=1;
        int columns = 3;
        for(int k=0; k<listOfCards.size(); k++) {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
            try {
                favCard = fxmlLoader.load();
                CardControllerG cardController = fxmlLoader.getController();
                cardController.setData(listOfCards.get(k));

                int finalK = k;
                favCard.setOnMouseClicked(event -> openFavSerie(listOfCards.get(finalK)));

                gpFavComic.add(favCard, k%columns,i);
                if(k%columns == columns-1){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void openFavSerie(SeriesBean seriesBean){
        ReaderHomeControllerG readerHomeControllerG = ReaderHomeControllerG.getInstance();
        readerHomeControllerG.openSeries(seriesBean);
    }

}
