package com.example.comics.model;

public class ChapterObjective extends Objective{

    private int requiredChaptersPercentage;


    public ChapterObjective(){
        //costruttore
    }

    public ChapterObjective(Badge badge, Discount discount, int requirement) {
        this.badge = badge;
        this.discount = discount;
        this.requiredChaptersPercentage = requirement;
    }

    @Override
    public void setRequirement(int requirement) {
        this.requiredChaptersPercentage = requirement;
    }

    @Override
    public int getRequirement() {
        return this.requiredChaptersPercentage;
    }

    @Override
    public boolean isObjectiveAchieved(int achievement){
        int percentageAchievement = achievement *100;
        return percentageAchievement >= requiredChaptersPercentage;
    }

    @Override
    public final String getType() {
        return "chapterObjective";
    }


}
