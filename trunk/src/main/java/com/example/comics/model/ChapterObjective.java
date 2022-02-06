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
    public boolean achieveObjective(int readersChapters, Badge badge) {
        return readersChapters >= requiredChaptersPercentage;
    }

    @Override
    public final String getType() {
        return "chapterObjective";
    }


}
