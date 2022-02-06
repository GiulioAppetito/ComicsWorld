package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.AccountObserver;
import com.example.comics.model.AccountSubject;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class FeedControllerG implements AccountObserver {

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
            AccountSubject.attach(instance, "orders");
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
                CardControllerG cardController = fxmlLoader.getController();
                cardController.setData(latestSeries.get(j));


                int finalJ = j;
                card.setOnMouseClicked(event -> openSerie(latestSeries.get(finalJ)));

                feedGrid.add(card,j%4,i);
                if(j%4 == 3){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openSerie(SeriesBean seriesBean){
        HomeFactory homeFactory = new HomeFactory();
        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.openSeries(seriesBean);
    }

    @Override
    public void update() {

    }
}
