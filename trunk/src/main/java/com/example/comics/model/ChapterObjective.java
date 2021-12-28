package com.example.comics.model;

public class ChapterObjective extends Objective{

    private int requiredChapters;

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
