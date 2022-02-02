package com.example.comics.controller;

import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.ReviewBean;
import com.example.comics.model.fagioli.SeriesBean;

public class StatisticsController {


    public Float seriesAverageRating(SeriesBean seriesBean) {

        Float ratingSum = 0f;
        Float numReviews = 0f;

        for (ChapterBean chapterBean : seriesBean.getChapters()) {
            for (ReviewBean reviewBean : chapterBean.getReviews()) {
                ratingSum += reviewBean.getRating();
                numReviews = numReviews+1f;
            }
        }

        if(numReviews == 0f){
            return 0f;
        }
        return ratingSum / numReviews;
    }
}
