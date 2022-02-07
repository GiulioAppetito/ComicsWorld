package com.example.comics.model;

public abstract class Objective{

    protected Levels level;
    protected Discount discount;
    protected Badge badge;

    public abstract void setRequirement(Float requirement);
    public abstract Float getRequirement();
    public abstract String getType();

    public abstract boolean isObjectiveAchieved(Float achievement);

    public Levels getLevel() {return level;}
    public void setLevel(Levels level) {
        this.level = level;
    }

    public Badge getBadge() {
        return badge;
    }
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public Discount getDiscount() {
        return discount;
    }
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

}
