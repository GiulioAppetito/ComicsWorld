package com.example.comics.model;


import java.util.ArrayList;
import java.util.List;

public abstract class AccountSubject {

    private static List<AccountObserver> observers = new ArrayList<>();
    private static List<AccountObserver> orderObservers = new ArrayList<>();
    private static List<AccountObserver> badgeObservers = new ArrayList<>();

    public static void attach(AccountObserver observer, String interest){
        if(interest.equals("orders")){
            orderObservers.add(observer);
        }
        if(interest.equals("user")){
            observers.add(observer);
        }
        if(interest.equals("badges")){
            badgeObservers.add(observer);
        }

    }


    public void notifyObservers(){
        for (AccountObserver observer : observers) {
            observer.update();
        }
    }

    public void notifyObserversNewOrder(){
        for (AccountObserver observer : orderObservers) {
            observer.update();
        }
    }

}
