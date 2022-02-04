package com.example.comics.model;

import com.example.comics.model.fagioli.bundle.BadgeBundle;

public class ChapterObjective extends Objective{

    private int requiredChaptersPercentage;

    public ChapterObjective(Badge badge,Discount discount) {
        this.badge = badge;
        this.discount = discount;
    }

    public ChapterObjective(){}

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

        System.out.println("[ChapterObjective] Now running achieveObjective by chapters.");

        Boolean win = readersChapters >= requiredChaptersPercentage;
        if(win){
            BadgeBundle badgeBundle = new BadgeBundle();
            badgeBundle.setIcon(badge.getIcon());
            badgeBundle.setName(badge.getName());
            notifyObserversNewBadge(badgeBundle);
        }
        return win;
    }

    @Override
    public final String getType() {
        return "chapterObjective";
    }


}
