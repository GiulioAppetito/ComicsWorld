package com.example.comics.model;


import com.example.comics.model.fagioli.bundle.BadgeBundle;

public class ReviewsObjective extends Objective{

    private int requiredReviews;

    public ReviewsObjective(Badge badge, Discount discount){
        this.badge = badge;
        this.discount = discount;
    }

    @Override
    public boolean achieveObjective(int readersReviews, Badge badge){
        Boolean win = (readersReviews >= requiredReviews);
        if(win == true){
            BadgeBundle badgeBundle = new BadgeBundle();
            System.out.println("vittoria badge");
            badgeBundle.setIcon(badge.getIcon());
            badgeBundle.setName(badge.getName());
            notifyObserversNewBadge(badgeBundle);
        }
        return win;
    }

    @Override
    public final String getType() {
        return "reviewsObjective";
    }

    public int getRequiredReviews(){
        return requiredReviews;
    }
    public void setRequiredReviews(int num){
        this.requiredReviews = num;
    }

}
