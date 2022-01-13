package com.example.comics.view2;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.Advertisement;
import com.example.comics.model.fagioli.SeriesBean;
import com.example.comics.view1.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class FeedControllerG2 {

    @FXML
    private HBox hBoxAds;

    @FXML
    private VBox vBoxSeries;

    private static FeedControllerG2 instance;
    private static List<SeriesBean> latestSeries;

    private FeedControllerG2(){
        loadLatestSeries();
        //load ads
    }

    public static synchronized FeedControllerG2 getInstance(){
        if(instance == null){
            instance = new FeedControllerG2();
        }
        return instance;
    }

    public static void loadLatestSeries(){
        ResearchController researchController = new ResearchController();
        latestSeries = researchController.getLatestSeries();
    }


    public void init() {

        System.out.println("feed2 init");
        int size = latestSeries.size();
        for(int j=0; j<size; j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("seriesCard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                SeriesCardControllerG cardController = fxmlLoader.getController();
                cardController.setData(latestSeries.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> {
                    try {
                        openSerie(latestSeries.get(finalJ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                vBoxSeries.getChildren().add(card);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //load the ads panel

        List<Advertisement> listOfAds = new ArrayList<>(addAds());

        for (Advertisement ad : listOfAds) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("adCard.fxml"));
            try {
                VBox vbAd = fxmlLoader.load();
                AdControllerG2 adController2 = fxmlLoader.getController();
                adController2.setData(ad);
                vbAd.setOnMouseClicked(event -> System.out.println("Clicked ad"));
                hBoxAds.getChildren().add(vbAd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void openSerie(SeriesBean seriesBean) throws IOException {
/*
        SeriesControllerG serieControllerG = new SeriesControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("serie.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(serieControllerG);

        HomeFactory homeFactory = new HomeFactory();
        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.changeCenter(loader.load());

        serieControllerG.setData(seriesBean);
 */
    }


    private List<Advertisement> addAds(){

        List<Advertisement> la = new ArrayList<>();
        int i;
        int n = 10;


        for(i=0; i<n; i++){
            Advertisement ad = new Advertisement();
            ad.setTitle("Ad n°" + i);
            la.add(ad);
        }


        return la;

    }


}
