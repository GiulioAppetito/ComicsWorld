package com.example.comics.model;

public abstract class Objective {

    private Levels level;
    protected Discount discount;
    protected Badge badge;
    private String series_title;


    public Levels getLevel() {
        return level;
    }
    public void setLevel(Levels level) {
        this.level = level;
    }

    public abstract boolean achieveObjective(int input);
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

    public String getSeries_title() {
        return series_title;
    }

    public void setSeries_title(String series_title) {
        this.series_title = series_title;
    }
}
