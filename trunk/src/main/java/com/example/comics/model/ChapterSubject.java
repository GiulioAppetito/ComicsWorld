package com.example.comics.model;

import com.example.comics.model.AccountObserver;

import java.util.ArrayList;

public abstract class ChapterSubject {

    private static ArrayList<AccountObserver> observers = new ArrayList<>();

    public static void attach(AccountObserver observer){
        observers.add(observer);
    }

    public void detach(AccountObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (AccountObserver observer : observers) {
            observer.update();
        }
    }

}
