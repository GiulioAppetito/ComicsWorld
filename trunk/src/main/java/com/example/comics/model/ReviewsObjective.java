package com.example.comics.model;

import com.example.comics.model.dao.BadgeDAO;

public class ReviewsObjective extends Objective{

    private int requiredReviews;

    public ReviewsObjective(int badgeID){
        BadgeDAO badgeDAO = new BadgeDAO();
        this.badge = badgeDAO.retreiveAssociatedBadge(badgeID);
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
