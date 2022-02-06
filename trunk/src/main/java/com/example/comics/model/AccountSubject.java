package com.example.comics.model;


import java.util.ArrayList;
import java.util.List;

public abstract class AccountSubject {

    private static List<AccountObserver> observers = new ArrayList<>();

    public static void attach(AccountObserver observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for (AccountObserver observer : observers) {
            observer.update();
        }
    }

}
