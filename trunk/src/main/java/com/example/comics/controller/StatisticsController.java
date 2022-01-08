package com.example.comics.controller;

import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;

public class StatisticsController {


    public int seriesAverageRating(SeriesBean seriesBean) {


        System.out.println("calcolo average rating");

        int averageRating = 0;
        int numOfChapters = 0;

        for (ChapterBean chapterBean : seriesBean.getChapters()) {
            System.out.println("cap: " + chapterBean.getTitle());
            averageRating = averageRating + chapterBean.getAverageRating();
            System.out.println("rating medio per capitolo: " + chapterBean.getAverageRating());
            numOfChapters++;

        }

        if(numOfChapters==0){
            return numOfChapters;
        }

        return averageRating/numOfChapters;
    }

}
