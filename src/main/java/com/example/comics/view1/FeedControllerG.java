package com.example.comics.view1;

import com.example.comics.model.Advertisement;
import com.example.comics.model.Comic;
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

    private List<Comic> listOfCards;
    private List<Advertisement> listOfAds;


    public FeedControllerG(){
    }


    public void init() {

        listOfCards = new ArrayList<>(add());
        int rows = listOfCards.size()/4;

        for(int i=0; i<rows+1; i++){
            for(int j=0; j<4; j++) {
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

                    feedGrid.add(card, i, j);
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

    private void openSerie() throws IOException {

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



    private List<Comic> add(){

        List<Comic> ls = new ArrayList<>();

        Comic comic = new Comic();
        comic.setName("Spiderman");
        comic.setAuthor("Stan Lee");
        //comic.setImageSrc(null);
        ls.add(comic);

        Comic comic2 = new Comic();
        comic2.setName("Superman");
        comic2.setAuthor("Stan Lee");
        //comic2.setImageSrc(null);
        ls.add(comic2);

        Comic comic3 = new Comic();
        comic3.setName("Spiderman");
        comic3.setAuthor("Stan Lee");
        //comic3.setImageSrc(null);
        ls.add(comic3);

        Comic comic4 = new Comic();
        comic4.setName("Superman");
        comic4.setAuthor("Stan Lee");
        //comic4.setImageSrc(null);
        ls.add(comic4);

        Comic comic5= new Comic();
        comic5.setName("Spiderman");
        comic5.setAuthor("Stan Lee");
        //comic5.setImageSrc(null);
        ls.add(comic5);

        Comic comic6 = new Comic();
        comic6.setName("Superman");
        comic6.setAuthor("Stan Lee");
        //comic6.setImageSrc(null);
        ls.add(comic6);

        Comic comic7 = new Comic();
        comic7.setName("Spiderman");
        comic7.setAuthor("Stan Lee");
        //comic7.setImageSrc(null);
        ls.add(comic7);

        Comic comic8 = new Comic();
        comic8.setName("Superman");
        comic8.setAuthor("Stan Lee");
        //comic8.setImageSrc(null);
        ls.add(comic8);

        return ls;

    }

    private List<Advertisement> addAds(){

        List<Advertisement> la = new ArrayList<>();
        int i;
        int n = 20;


        for(i=0; i<n; i++){
            Advertisement ad = new Advertisement();
            ad.setTitle("Ad n°" + i);
            //comic.setImageSrc(null);
            la.add(ad);
        }


        return la;

    }

}
