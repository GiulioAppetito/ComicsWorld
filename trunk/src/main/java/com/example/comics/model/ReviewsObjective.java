package com.example.comics.model;


import com.example.comics.model.fagioli.bundle.BadgeBundle;

public class ReviewsObjective extends Objective{

    private int requiredReviews;

    public ReviewsObjective(){
        //costruttore
    }

    public ReviewsObjective(Badge badge, Discount discount, int requiredReviews){
        this.badge = badge;
        this.discount = discount;
        this.requiredReviews = requiredReviews;
    }


    @Override
    public void setRequirement(int requirement) {
        this.requiredReviews = requirement;
    }

    @Override
    public int getRequirement() {
        return requiredReviews;
    }

    @Override
    public boolean achieveObjective(int readersReviews, Badge badge){

        Boolean win = (readersReviews >= requiredReviews);
        if(readersReviews >= requiredReviews){
            BadgeBundle badgeBundle = new BadgeBundle();
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

}
