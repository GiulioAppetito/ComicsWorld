package com.example.comics.model;

public class ChapterObjective extends Objective{

    private Float requiredChaptersPercentage;

    public ChapterObjective(Badge badge, Discount discount){
        super(badge, discount);
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
        //L'obiettivo è raggiunto se la percentuale di capitoli della serie letti dal lettore è maggiore o uguale della percentuale richiesta
        Float percentageAchievement = achievement *100;
        return percentageAchievement >= requiredChaptersPercentage;
    }

    @Override
    public final String getType() {
        return "chapters";
    }

}
