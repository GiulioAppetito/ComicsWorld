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

public class FavouriteComicsControllerG {
    @FXML
    private GridPane gpFavComic;


    public void initialize(){

        ResearchController researchController = new ResearchController();
        //poi ovviamente sarà na cosa diversa da latest
        List<SeriesBean> listOfCards = researchController.getFavouriteSeries();
        int size = listOfCards.size();
        int columns = 3;
        int i=1;
        for(int j=0; j<size; j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                VCardControllerG cardController = fxmlLoader.getController();
                cardController.setData(listOfCards.get(j).getTitle(),listOfCards.get(j).getCover());

                int finalJ = j;
                card.setOnMouseClicked(event -> {
                    try {
                        openSerie(listOfCards.get(finalJ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                gpFavComic.add(card,j%columns,i);
                if(j%columns == columns-1){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void openSerie(SeriesBean seriesBean) throws IOException {

        SeriesControllerG serieController = new SeriesControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("serie.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(serieController);

        ReaderHomeControllerG readerHomeControllerG = ReaderHomeControllerG.getInstance();
        readerHomeControllerG.changeCenter(loader.load());

        serieController.setData(seriesBean);

    }
}
