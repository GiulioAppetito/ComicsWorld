package com.example.comics.model;


import com.example.comics.model.fagioli.bundle.BadgeBundle;

public class ReviewsObjective extends Objective{

    private int requiredReviews;

    public ReviewsObjective(Badge badge, Discount discount){
        this.badge = badge;
        this.discount = discount;
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
