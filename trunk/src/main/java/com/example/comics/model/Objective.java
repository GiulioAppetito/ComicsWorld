package com.example.comics.model;

public abstract class Objective{

    protected Levels level;
    protected Discount discount;
    protected Badge badge;
    protected int id;

    public abstract void setRequirement(int requirement);
    public abstract int getRequirement();
    public abstract String getType();

    public abstract boolean isObjectiveAchieved(int achievement);

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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
