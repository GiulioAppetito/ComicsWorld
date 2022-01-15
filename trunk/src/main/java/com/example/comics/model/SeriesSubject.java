package com.example.comics.model;

import java.util.ArrayList;

public class SeriesSubject {

    private static ArrayList<SeriesObserver> observers = new ArrayList<>();

    public static void attach(SeriesObserver observer){
        observers.add(observer);
    }

    public void detach(SeriesObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (SeriesObserver observer : observers) {
            observer.update();
        }
    }
}
