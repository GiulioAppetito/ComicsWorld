package com.example.comics.controller;

import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.ReviewBean;
import com.example.comics.model.fagioli.SeriesBean;

public class StatisticsController {


    public Float seriesAverageRating(SeriesBean seriesBean) {


        System.out.println("calcolo average rating");

        Float ratingSum = 0f;
        Float numReviews = 0f;

        for (ChapterBean chapterBean : seriesBean.getChapters()) {
            for (ReviewBean reviewBean : chapterBean.getReviews()) {
                System.out.println("[Statistics Controller] Chapter : "+chapterBean.getTitle()+" review stars : "+reviewBean.getRating());
                ratingSum += reviewBean.getRating();
                numReviews++;
            }
        }

        if(numReviews == 0f){
            return 0f;
        }
        return ratingSum / numReviews;
    }
}
