package com.example.comics;

import java.util.ArrayList;
import java.util.List;

public abstract class AccountSubject {

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
