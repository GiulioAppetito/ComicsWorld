package com.example.comics.view1;

import com.example.comics.model.Advertisement;
import com.example.comics.model.Series;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private List<Series> listOfCards;
    private List<Advertisement> listOfAds;


    public FeedControllerG(){
    }


    public void init() {

        listOfCards = new ArrayList<>(add());
        int rows = listOfCards.size()/4;

        for(int i=1; i<rows; i++){
            for(int j=0; j<5; j++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
                try {
                    VBox card = fxmlLoader.load();
                    VCardController cardController = fxmlLoader.getController();
                    cardController.setData(listOfCards.get(i).getName());

                    card.setOnMouseClicked(event -> {
                        try {
                            openSerie();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    feedGrid.add(card,j,i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //load the ads panel
        listOfAds = new ArrayList<>(addAds());
        int len = listOfAds.size();

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

        HomeControllerG homeControllerG = HomeControllerG.getInstance();
        homeControllerG.changeCenter(loader.load());

        serieController.init();

    }

    private void openCharacter() throws IOException {
        CharacterControllerG characterControllerG = new CharacterControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("character.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(characterControllerG);

        System.out.println("Clicked character");

        HomeControllerG homeControllerG = HomeControllerG.getInstance();
        homeControllerG.changeCenter(loader.load());
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

    private List<Advertisement> addAds(){

        List<Advertisement> la = new ArrayList<>();
        int i;
        int n = 14;


        for(i=0; i<n; i++){
            Advertisement ad = new Advertisement();
            ad.setTitle("Ad n°" + i);
            //comic.setImageSrc(null);
            la.add(ad);
        }


        return la;

    }

}
