package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.controller.StatisticsController;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

public class StatisticsRatingControllerG {

    @FXML
    private BarChart<?, ?> barChartRatings;


    public void initialize(){

        ResearchController researchController = new ResearchController();
        List<SeriesBean> publishedSeries = researchController.getPublishedSeries();

        StatisticsController statisticsController = new StatisticsController();
        //Defining the y Axis
        NumberAxis yAxis = new NumberAxis(0, 5, 1);
        yAxis.setLabel("Rating units");
        //areaChartRatings.setTitle("Ratings during one week");

        //Prepare XYChart.Series objects by setting data
        XYChart.Series series1 = new XYChart.Series();
        //series1.setName();

        for(SeriesBean series : publishedSeries) {
            System.out.println(series.getTitle());
            //int rating = statisticsController.seriesAverageRating(series);
            System.out.println("StRaCoG averageRating per series: " + series.getAverageRating());
            series1.getData().add(new XYChart.Data(series.getTitle(), series.getAverageRating()));
        }

        //Setting the XYChart.Series objects to area chart
        barChartRatings.getData().addAll(series1);
    }

}