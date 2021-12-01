package com.example.comics.view1;

import com.example.comics.model.Advertisement;
import com.example.comics.model.Series;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FeedControllerG {

    @FXML
    public GridPane feedGrid;

    @FXML
    private HBox hbAds;

    @FXML
    private MenuButton btnFilter;


    public FeedControllerG(){
        //costruttore
    }


    public void init() {

        List<Series> listOfCards = new ArrayList<>(add());
        int size = listOfCards.size();

        int i=1;
        for(int j=0; j<size; j++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
                try {
                    VBox card = fxmlLoader.load();
                    VCardController cardController = fxmlLoader.getController();
                    cardController.setData(listOfCards.get(j).getName());

                    card.setOnMouseClicked(event -> {
                        try {
                            openSerie();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    feedGrid.add(card,j%5,i);
                    if(j%5 == 4){
                        i++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


        //load the ads panel

        List<Advertisement> listOfAds = new ArrayList<>(addAds());

        for (Advertisement listOfAd : listOfAds) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("hcard.fxml"));
            try {
                VBox vbAd = fxmlLoader.load();
                AdController adController = fxmlLoader.getController();
                adController.setData(listOfAd);

                vbAd.setOnMouseClicked(event -> System.out.println("Clicked ad"));

                hbAds.getChildren().add(vbAd);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void openSerie() throws IOException {

        SerieController serieController = new SerieController();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("serie.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(serieController);

        ReaderHomeControllerG readerHomeControllerG = ReaderHomeControllerG.getInstance();
        readerHomeControllerG.changeCenter(loader.load());

        serieController.init();

    }

    private void openCharacter() throws IOException {
        CharacterControllerG characterControllerG = new CharacterControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("character.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(characterControllerG);

        ReaderHomeControllerG readerHomeControllerG = ReaderHomeControllerG.getInstance();
        readerHomeControllerG.changeCenter(loader.load());
    }



    private List<Series> add(){

        List<Series> ls = new ArrayList<>();

        int numSeries = 7;
        int i;

        for(i=0;i<numSeries;i++){
            Series comic = new Series();
            comic.setName("Spiderman");
            comic.setAuthor("Stan Lee");
            ls.add(comic);

        }


        return ls;

    }

    private List<Advertisement> addAds(){

        List<Advertisement> la = new ArrayList<>();
        int i;
        int n = 14;


        for(i=0; i<n; i++){
            Advertisement ad = new Advertisement();
            ad.setTitle("Ad n°" + i);
            la.add(ad);
        }


        return la;

    }

}
