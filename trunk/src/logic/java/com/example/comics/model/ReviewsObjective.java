package com.example.comics.model;

public class ReviewsObjective extends Objective{

    private Float requiredReviews;

    public ReviewsObjective(){
        //costruttore
    }

    @Override
    public void setRequirement(Float requirement) {
        this.requiredReviews = requirement;
    }

    @Override
    public Float getRequirement() {
        return requiredReviews;
    }

    @Override
    public boolean isObjectiveAchieved(Float readersReviews){
        //L'obiettivo è raggiunto se il numero di reviews lasciate dal lettore è maggiore o uguale del numero richiesto
        return readersReviews >= requiredReviews ;
    }

    @Override
    public final String getType() {
        return "reviews";
    }


}