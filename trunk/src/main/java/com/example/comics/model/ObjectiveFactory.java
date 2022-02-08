package com.example.comics.model;

public class ObjectiveFactory {

    public Objective createObjective(String type,Badge badge,Discount discount,Levels level,Float requirement){

        Objective objective;

        switch (type){
            case "reviews":
                objective = new ReviewsObjective();
                objective.setBadge(badge);
                objective.setDiscount(discount);
                objective.setLevel(level);
                objective.setRequirement(requirement);
                break;
            case "chapters":
                objective = new ChapterObjective();
                objective.setBadge(badge);
                objective.setDiscount(discount);
                objective.setLevel(level);
                objective.setRequirement(requirement);
                break;
            default:
                objective = null;
                break;
        }
        return objective;
    }
}


