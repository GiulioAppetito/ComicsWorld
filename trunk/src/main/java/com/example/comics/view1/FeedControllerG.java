package com.example.comics.view1;

import animatefx.animation.Bounce;
import com.example.comics.controller.ResearchController;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class FeedControllerG{


    @FXML
    private Pane animationPane;

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;

    @FXML
    private GridPane feedGrid;


    private static FeedControllerG instance;
    private static List<SeriesBean> latestSeries;

    private FeedControllerG(){
        //costruttore
    }

    public static synchronized FeedControllerG getInstance(){
        if(instance == null){
            instance = new FeedControllerG();
        }
        return instance;
    }

    public void loadLatestSeries(){
        ResearchController researchController = new ResearchController();
        latestSeries = researchController.getLatestSeries();
    }
/*
    private void waitingAnimation() {
        if(animationPane != null) {
            animationPane.setVisible(true);
            new Bounce(circle1).setCycleCount(4).setCycleCount(14).setDelay(Duration.valueOf("500ms")).play();
            new Bounce(circle1).setCycleCount(14).setDelay(Duration.valueOf("150ms")).play();
            new Bounce(circle1).setCycleCount(14).setDelay(Duration.valueOf("200ms")).play();
        }
    }

    private void stopWaiting(){
        animationPane.setVisible(false);
    }

 */
    public void init() {

        animationPane.setVisible(false);

        loadLatestSeries();
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

}
