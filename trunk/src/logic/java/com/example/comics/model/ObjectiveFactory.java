package com.example.comics.model;

public class ObjectiveFactory {

    public Objective createObjective(String type,Badge badge,Discount discount,Levels level,Float requirement){

        Objective objective = null;

        switch (type) {
            case "reviews" -> {
                objective = new ReviewsObjective(badge, discount);
                objective.setLevel(level);
                objective.setRequirement(requirement);
            }
            case "chapters" -> {
                objective = new ChapterObjective(badge, discount);
                objective.setLevel(level);
                objective.setRequirement(requirement);
            }
            default -> {
                //nothing
            }
        }
        return objective;
    }
}


