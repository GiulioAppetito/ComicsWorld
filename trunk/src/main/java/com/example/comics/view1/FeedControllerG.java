package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.Advertisement;
import com.example.comics.model.fagioli.SeriesBean;
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

    private static FeedControllerG instance;
    private static List<SeriesBean> latestSeries;

    private FeedControllerG(){
        loadLatestSeries();
    }

    public static synchronized FeedControllerG getInstance(){
        if(instance == null){
            instance = new FeedControllerG();
        }
        return instance;
    }

    public static void loadLatestSeries(){
        ResearchController researchController = new ResearchController();
        latestSeries = researchController.getLatestSeries();
    }


    public void init() {

        System.out.println("feed init");
        int size = latestSeries.size();
        int i=1;
        for(int j=0; j<size; j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                CardControllerG cardController = fxmlLoader.getController();
                cardController.setData(latestSeries.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> {
                    try {
                        openSerie(latestSeries.get(finalJ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                feedGrid.add(card,j%4,i);
                if(j%4 == 3){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openSerie(SeriesBean seriesBean) throws IOException {

        SeriesControllerG serieControllerG = new SeriesControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = SeriesControllerG.class.getResource("serie.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(serieControllerG);

        HomeFactory homeFactory = new HomeFactory();
        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.changeCenter(loader.load());

        serieControllerG.setData(seriesBean);

    }

}
