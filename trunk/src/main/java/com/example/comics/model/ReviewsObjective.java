package com.example.comics.model;

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

    public boolean isObjectiveAchieved(int readersReviews){
        return readersReviews >= requiredReviews;
    }

    @Override
    public final String getType() {
        return "reviewsObjective";
    }

}
