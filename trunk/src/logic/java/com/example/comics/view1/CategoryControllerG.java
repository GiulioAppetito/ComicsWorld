package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.Genres;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class CategoryControllerG {

    @FXML
    public Label lblcategory;
    @FXML
    public GridPane gpComic;


    public void init(Genres genres) {

        ResearchController researchController = new ResearchController();
        List<SeriesBean> series = researchController.getCategorySeries(genres);

        int columns = 3;
        int i=1;
        for(int j=0; j<series.size(); j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("vcard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                CardControllerG cardController = fxmlLoader.getController();
                cardController.setData(series.get(j));

                int finalJ = j;
                card.setOnMouseClicked(event -> openCategorySerie(series.get(finalJ)));

                gpComic.add(card,j%columns,i);
                if(j%columns == columns-1){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void openCategorySerie(SeriesBean seriesBean){
        HomeControllerG homeControllerG = HomeFactory.getHomeControllerG();
        homeControllerG.openSeries(seriesBean);
    }

    public void setData(Genres genre){
        lblcategory.setText(genre.name());
    }

    public void back(){
        HomeControllerG homeControllerG = HomeFactory.getHomeControllerG();
        try {
            homeControllerG.openCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
