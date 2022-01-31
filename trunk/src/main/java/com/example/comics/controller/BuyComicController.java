package com.example.comics.controller;

import com.example.comics.controller.boundaries.BuyComicBoundary;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;

public class BuyComicController {

    public void buyComic(ChapterBean chapterBean, SeriesBean seriesBean){

        BuyComicBoundary buyComicBoundary = new BuyComicBoundary();
        buyComicBoundary.buyThisComic(chapterBean,seriesBean);

    }

    //ma serve davvero che facciamo amazon?
    //se tipo siamo noi i sellers, magari tutta la roba di generare noi il codice
    //diventa più interessante,
    //poi ci occupiamo noi delle verifiche e tutto
    //e possiamo anche conservare la roba del db (senza impazzire sul file)
    //e magari come cosa esterna usiamo paypal o google pay per verificare l'acquisto e basta

}
