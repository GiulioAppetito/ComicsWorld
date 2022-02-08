package com.example.comics.model;

public class ReviewsObjective extends Objective{

    private Float requiredReviews;

    public ReviewsObjective(){
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
        System.out.println("* * * * * Verifying reviews objective. * * * * * ");
        System.out.println("readersReviews : "+readersReviews+"  >= requiredReviews : "+requiredReviews +"?");

        return readersReviews >= requiredReviews ;
    }

    @Override
    public final String getType() {
        return "reviews";
    }


}
