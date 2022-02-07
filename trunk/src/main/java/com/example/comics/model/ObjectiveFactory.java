package com.example.comics.model;

public class ObjectiveFactory {

    public Objective createObjective(String type){

        switch (type){
            case "reviews":
                return new ReviewsObjective();
            case "chapters":
                return new ChapterObjective();
            default:
                return null;
        }
    }
}
