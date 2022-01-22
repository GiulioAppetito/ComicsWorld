package com.example.comics.model;

public class ChapterObjective extends Objective{

    private int requiredChapters;

    public ChapterObjective(Badge badge,Discount discount) {
        this.badge = badge;
        this.discount = discount;
    }

    public ChapterObjective(Badge badge, Discount discount, int requirement) {
        this.badge = badge;
        this.discount = discount;
        this.requiredChapters = requirement;
    }

    @Override
    public boolean achieveObjective(int input, Badge badge) {
        return false;
    }

    @Override
    public final String getType() {
        return "chapterObjective";
    }


    public int getRequiredChapters() {
        return requiredChapters;
    }

    public void setRequiredChapters(int requiredChapters) {
        this.requiredChapters = requiredChapters;
    }
}
