package com.example.comics.model;

import com.example.comics.model.fagioli.bundle.BadgeBundle;

import java.util.ArrayList;

public abstract class ObjectiveSubject {

    private static ArrayList<ObjectiveObserver> observers = new ArrayList<>();

    public static void attach(ObjectiveObserver observer){
        observers.add(observer);
    }

    public void detach(ObjectiveObserver observer){
        observers.remove(observer);
    }

    public void notifyObserversNewBadge(BadgeBundle badgeBundle){
        for (ObjectiveObserver observer : observers) {
            System.out.println("sto notificando");
            observer.update(badgeBundle);
        }
    }

}
