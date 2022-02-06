package com.example.comics.model;

import com.example.comics.model.fagioli.ReviewBean;

import java.util.ArrayList;
import java.util.List;

public abstract class ChapterSubject {

    private static final List<ChapterObserver> observers = new ArrayList<>();
    private static final List<ChapterObserver> observersReview = new ArrayList<>();

    public static void attach(ChapterObserver observer, String interest){
        if(interest.equals("reviews")){
            observersReview.add(observer);
        }
        else{
            observers.add(observer);
        }
    }

    public void notifyObserversNewReview(ReviewBean reviewBean){
        for (ChapterObserver observer : observersReview) {
                observer.update(reviewBean);
        }
    }


}
