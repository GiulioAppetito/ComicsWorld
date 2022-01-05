package com.example.comics.model;

public class ChapterObjective extends Objective{

    private int requiredChapters;

    public ChapterObjective(Badge badge,Discount discount) {
        this.badge = badge;
        this.discount = discount;
    }

    @Override
    public boolean achieveObjective(int input) {
        return false;
    }

    @Override
    public String getType() {
        return "chapterObjective";
    }


    public int getRequiredChapters() {
        return requiredChapters;
    }

    public void setRequiredChapters(int requiredChapters) {
        this.requiredChapters = requiredChapters;
    }
}
