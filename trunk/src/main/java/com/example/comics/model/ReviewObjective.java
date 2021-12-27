package com.example.comics.model;

public class ReviewObjective extends Objective{

    private Integer numberOfReviews = 0;

    @Override
    public boolean achieveObjective() {
        if(numberOfReviews==3){
            setLevel(Levels.BEGINNER);
            return true;
        }
        if(numberOfReviews==5){
            setLevel(Levels.INTERMEDIATE);
            return true;
        }
        if(numberOfReviews==10){
            setLevel(Levels.EXPERT);
            return true;
        }
        return false;
    }

}
