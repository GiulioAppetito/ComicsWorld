package com.example.comics.model;

public class ChapterObjective extends Objective{

    private Float requiredChaptersPercentage;

    public ChapterObjective(){
    }

    @Override
    public void setRequirement(Float requirement) {
        this.requiredChaptersPercentage = requirement;
    }

    @Override
    public Float getRequirement() {
        return this.requiredChaptersPercentage;
    }

    @Override
    public boolean isObjectiveAchieved(Float achievement){
        System.out.println("* * * * * Verifying chapters objective. * * * * * ");
        Float percentageAchievement = achievement *100;
        return percentageAchievement >= requiredChaptersPercentage;
    }

    @Override
    public final String getType() {
        return "chapters";
    }

}
