package com.example.comics.model;


import com.example.comics.model.fagioli.BadgeBean;

import java.util.ArrayList;
import java.util.List;

public abstract class AccountSubject {

    private static List<AccountObserver> observers = new ArrayList<>();
    private static List<ReaderObserver> orderObservers = new ArrayList<>();
    private static List<ReaderObserver> badgeObservers = new ArrayList<>();

    public static void attach(AccountObserver observer){

            observers.add(observer);

    }

    public static void attach(ReaderObserver observer, String interest){

        if(interest.equals("orders")){
            orderObservers.add(observer);
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

    public void notifyObserversNewBadge(BadgeBean badgeBean){
        for (ReaderObserver observer : badgeObservers) {
            observer.update(badgeBean);
        }
    }

    public void notifyObserversNewOrder(Boolean payment){
        for (ReaderObserver observer : orderObservers) {
            observer.update(payment);
        }
    }

}
