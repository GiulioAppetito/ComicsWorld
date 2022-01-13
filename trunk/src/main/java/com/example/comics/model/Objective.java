package com.example.comics.model;

public abstract class Objective extends ObjectiveSubject{

    private int id;
    private Levels level;
    protected Discount discount;
    protected Badge badge;
    private String seriesTitle;


    public Levels getLevel() {
        return level;
    }
    public void setLevel(Levels level) {
        this.level = level;
    }

    public abstract boolean achieveObjective(int input, Badge badge);
    public abstract String getType();

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

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
