package com.example.comics.view1;

import com.example.comics.model.Series;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FavouriteComicsControllerG {

    @FXML
    private GridPane gpFavComics;

    public void init() {



            List<Series> listOfCards = new ArrayList<>(add());
            int rows = listOfCards.size() / 4;

            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < 4; j++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
                    try {
                        VBox card = fxmlLoader.load();
                        VCardController cardController = fxmlLoader.getController();
                        cardController.setData(listOfCards.get(i).getName());
                        gpFavComics.add(card, j, i);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


    }
    private List<Series> add(){

        List<Series> ls = new ArrayList<>();

        int numSeries = 17;
        int i;

        for(i=0;i<numSeries;i++){
            Series comic = new Series();
            comic.setName("Spiderman");
            comic.setAuthor("Stan Lee");
            //comic.setImageSrc(null);
            ls.add(comic);

        }


        return ls;

    }
}
