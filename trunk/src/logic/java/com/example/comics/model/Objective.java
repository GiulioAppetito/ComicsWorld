package com.example.comics.model;

public abstract class Objective{

    protected Levels level;
    protected Discount discount;
    protected Badge badge;

    protected Objective(Badge badge, Discount discount){
        this.badge = badge;
        this.discount = discount;
    }

    public abstract void setRequirement(float requirement);
    public abstract float getRequirement();
    public abstract String getType();

    public abstract boolean isObjectiveAchieved(Float achievement);

    public Levels getLevel() {return level;}
    public void setLevel(Levels level) {
        this.level = level;
    }

    public Badge getBadge() {
        return badge;
    }
    public Discount getDiscount() {
        return discount;
    }

}
