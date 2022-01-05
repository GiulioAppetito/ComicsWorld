package com.example.comics.model;

import com.example.comics.model.fagioli.ObjectiveBean;
import com.example.comics.model.fagioli.ReviewBean;

import java.util.ArrayList;

public abstract class ReviewSubject {

      private static final ArrayList<ReviewObserver> observers = new ArrayList<>();
      public static void attach(ReviewObserver observer){
  observers.add(observer);
 }
      public void detach(ReviewObserver observer){
  observers.remove(observer);
 }

      public void notifyObservers(ReviewBean reviewBean){
        for (ReviewObserver observer : observers) {
          observer.update(reviewBean);
        }
      }

      public void notifyAchievedReviewObjective(ObjectiveBean objectiveBean){
          for (ReviewObserver observer : observers) {
              observer.achievedObjective(objectiveBean);
          }
      }

}
