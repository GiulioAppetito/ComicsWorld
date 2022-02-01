package com.example.comics.controller.boundaries;

import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;
public class BuyComicBoundary {

    public BuyComicBoundary(){
        //costruttore
    }

    public void buyThisComic(ChapterBean chapterBean, SeriesBean seriesBean) {
        //Hey Jeff, it's us, we would like to include you in our new project
        //since Amazon is a great platform, could you sell our clients your products?

        //FakeAmazonControllerG fakeAmazonControllerG = new FakeAmazonControllerG();
        //fakeAmazonControllerG.initialize(chapterBean, seriesBean);
    }

    public void boughtTheComics() {
        //signal back to the logic that we got that bread
    }
}
