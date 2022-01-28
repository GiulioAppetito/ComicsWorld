package com.example.comics.model;

import com.example.comics.model.fagioli.ReviewBean;

import java.util.ArrayList;

public abstract class ChapterSubject {

    private static final ArrayList<ChapterObserver> observers = new ArrayList<>();

    public static void attach(ChapterObserver observer){
        observers.add(observer);
    }

    public void detach(ChapterObserver observer){
        observers.remove(observer);
    }

    public void notifyObserversNewReview(ReviewBean reviewBean){
        for (ChapterObserver observer : observers) {
                observer.updateReviews(reviewBean);

        }
    }

}
