package com.example.comics.model;


public class Discount {
    private final Float percentage;
    private int limitDays;

    public Discount(Float percentage) {
        this.percentage = percentage;
    }

    public int getLimitDays() {
        return limitDays;
    }

    public void setLimitDays(int limitDays) {
        this.limitDays = limitDays;
    }

    public Float getPercentage() {
        return percentage;
    }

}