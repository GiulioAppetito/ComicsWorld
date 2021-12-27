package com.example.comics.view1;

import com.example.comics.fagioli.SeriesBean;
import com.example.comics.model.Author;
import com.example.comics.model.Series;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FavouriteAuthorsControllerG {

    @FXML
    private GridPane gpFavAuthors;

    public void initialize(){

        List<Author> listOfCards = new ArrayList<>(add());
        int size = listOfCards.size();
        int columns = 3;
        int i=1;
        for(int j=0; j<size; j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("authorcard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                gpFavAuthors.add(card,j%columns,i);
                if(j%columns == columns-1){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private List<Author> add(){

        List<Author> la = new ArrayList<>();
        ArrayList<Series> series = null;
        int dummyNumAuthors = 12;
        int i;

        for(i=0;i<dummyNumAuthors;i++){
            Author author = new Author(series);
            author.setUsername("stanlee");
            la.add(author);
        }
        return la;
    }

    public void openSerie(SeriesBean seriesBean) throws IOException {

        SeriesControllerG serieController = new SeriesControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("serie.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(serieController);

        HomeFactory homeFactory = new HomeFactory();
        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.changeCenter(loader.load());

        serieController.init(seriesBean);

    }

}
