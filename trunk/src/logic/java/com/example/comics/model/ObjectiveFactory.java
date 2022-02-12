package com.example.comics.model;

public class ObjectiveFactory {

    public Objective createObjective(String type,Badge badge,Discount discount,Levels level,float requirement) throws Exception {

        switch (type) {
            case "reviews" -> {
                return createReviewsObjective(badge,discount,level,requirement);
            }
            case "chapters" -> {
                return createChaptersObjective(badge,discount,level,requirement);
            }
            default -> {
                throw new Exception("Invalid type : " + type);
            }
        }
    }

    public Objective createReviewsObjective(Badge badge,Discount discount,Levels level,float requirement){
        Objective objective;
        objective = new ReviewsObjective(badge, discount);
        objective.setLevel(level);
        objective.setRequirement(requirement);

        return objective;
    }

    public Objective createChaptersObjective(Badge badge,Discount discount,Levels level,float requirement){
        Objective objective;
        objective = new ChapterObjective(badge, discount);
        objective.setLevel(level);
        objective.setRequirement(requirement);

        return objective;
    }
}


