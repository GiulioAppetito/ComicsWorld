package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.fagioli.SeriesBean;
import com.example.comics.model.Advertisement;
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

public class FeedControllerG{

    @FXML
    public GridPane feedGrid;

    @FXML
    private HBox hbAds;

    @FXML
    private MenuButton btnFilter;


    private static FeedControllerG instance;
    private static List<SeriesBean> latestSeries;

    private FeedControllerG(){
        //costruttore
    }

    public static synchronized FeedControllerG getInstance(){
        if(instance == null){
            instance = new FeedControllerG();
            loadLatestSeries();
        }
        return instance;
    }

    public static void loadLatestSeries(){
        ResearchController researchController = new ResearchController();
        latestSeries = researchController.getLatestSeries();
    }


    public void init() {

        int size = latestSeries.size();
        int i=1;
        for(int j=0; j<size; j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                VCardControllerG cardController = fxmlLoader.getController();
                cardController.setData(latestSeries.get(j).getTitle(),latestSeries.get(j).getCover());

                int finalJ = j;
                card.setOnMouseClicked(event -> {
                    try {
                        openSerie(latestSeries.get(finalJ));
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
                AdControllerG adController = fxmlLoader.getController();
                adController.setData(listOfAd);
                vbAd.setOnMouseClicked(event -> System.out.println("Clicked ad"));
                hbAds.getChildren().add(vbAd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void openSerie(SeriesBean seriesBean) throws IOException {

        SeriesControllerG serieControllerG = new SeriesControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("serie.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(serieControllerG);

        HomeFactory homeFactory = new HomeFactory();
        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.changeCenter(loader.load());

        serieControllerG.init(seriesBean);

    }

    private List<Advertisement> addAds(){

        List<Advertisement> la = new ArrayList<>();
        int i;
        int n = 7;


        for(i=0; i<n; i++){
            Advertisement ad = new Advertisement();
            ad.setTitle("Ad n°" + i);
            la.add(ad);
        }


        return la;

    }

}
