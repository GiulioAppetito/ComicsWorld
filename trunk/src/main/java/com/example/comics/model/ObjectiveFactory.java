package com.example.comics.model;

public class ObjectiveFactory {

    public Objective createObjective(String type,Badge badge,Discount discount,Levels level,Float requirement){

        System.out.println("[FACTORY] Type = "+type);
        Objective objective;

        switch (type){
            case "reviews":
                System.out.println("[FACTORY] Creating reviewsObjective");
                objective = new ReviewsObjective();
                objective.setBadge(badge);
                objective.setDiscount(discount);
                objective.setLevel(level);
                objective.setRequirement(requirement);
                break;
            case "chapter":
                System.out.println("[FACTORY] Creating chaptersObjective");
                objective = new ChapterObjective();
                objective.setBadge(badge);
                objective.setDiscount(discount);
                objective.setLevel(level);
                objective.setRequirement(requirement);
                break;
            default:
                System.out.println("[FACTORY] Creating null");
                objective = null;
                break;
        }
        return objective;
    }

    public Objective retreiveObjective(String type,Badge badge,Discount discount,Levels level,Float requirement){

        Objective objective;
        System.out.println("[FACTORY]In retreive, type = "+type);

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
                System.out.println("[FACTORY] Obbjective non è di nessun tipo....");
                objective = null;
        }
        return objective;
        }

}


