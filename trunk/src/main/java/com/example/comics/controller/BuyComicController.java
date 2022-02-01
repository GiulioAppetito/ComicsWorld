package com.example.comics.controller;

import com.example.comics.controller.boundaries.BuyComicBoundary;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;

public class BuyComicController {

    public void buyComic(ChapterBean chapterBean, SeriesBean seriesBean, String code){

        BuyComicBoundary buyComicBoundary = new BuyComicBoundary();
        buyComicBoundary.buyThisComic(chapterBean,seriesBean);



    }
}
