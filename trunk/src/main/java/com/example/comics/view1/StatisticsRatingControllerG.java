package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.util.List;

public class StatisticsRatingControllerG {

    @FXML
    private BarChart<String, Integer> barChartRatings;


    public void initialize(){

        ResearchController researchController = new ResearchController();
        List<SeriesBean> publishedSeries = researchController.getPublishedSeries();

        //Defining the y Axis
        NumberAxis yAxis = new NumberAxis(0, 5, 1);
        yAxis.setLabel("Rating units");

        //Prepare XYChart.Series objects by setting data
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();

        for(SeriesBean seriesBean : publishedSeries) {
            series1.getData().add(new XYChart.Data<>(seriesBean.getTitle(), seriesBean.getAverageRating()));
        }

        //Setting the XYChart.Series objects to area chart
        barChartRatings.getData().addAll(series1);
    }

}