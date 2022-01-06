package com.example.comics.model;


public class ReviewsObjective extends Objective{

    private int requiredReviews;

    public ReviewsObjective(Badge badge, Discount discount){
        this.badge = badge;
        this.discount = discount;
    }

    @Override
    public boolean achieveObjective(int readersReviews){
        return readersReviews >= requiredReviews;
    }

    @Override
    public String getType() {
        return "reviewsObjective";
    }

    public int getRequiredReviews(){
        return requiredReviews;
    }
    public void setRequiredReviews(int num){
        this.requiredReviews = num;
    }
}
