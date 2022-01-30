package com.example.comics.controller;

import com.example.comics.controller.boundaries.BuyComicBoundary;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;

public class BuyComicController {

    public void buyComic(ChapterBean chapterBean, SeriesBean seriesBean){

        BuyComicBoundary buyComicBoundary = new BuyComicBoundary();
        buyComicBoundary.buyThisComic(chapterBean,seriesBean);

        //contattare la boundary che poi contatterà amazon e gli chiederà di elaborare parte del caso d'uso
        //attendere (O RICEVERE) risposta
        // che eventualmente la boundary dovrà elaborare dinuovo in una risposta
        //e poi il controller applicativo farà cose
        //tipo invio mail con successo per l'acquisto
        //tipo controllo se si è vinto qualche badge
        //(anche se abbiamo detto no perché somiglia troppo a postReview poi)
        //ed infine rispondere sulla piattaforma stessa magari esce il pop up sulla view
        //quindi serve una risposta o un observer che notifichi
    }

    //ma serve davvero che facciamo amazon?
    //se tipo siamo noi i sellers, magari tutta la roba di generare noi il codice
    //diventa più interessante,
    //poi ci occupiamo noi delle verifiche e tutto
    //e possiamo anche conservare la roba del db (senza impazzire sul file)
    //e magari come cosa esterna usiamo paypal o google pay per verificare l'acquisto e basta

}
