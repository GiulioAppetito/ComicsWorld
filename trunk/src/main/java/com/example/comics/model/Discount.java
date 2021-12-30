package com.example.comics.model;

import java.util.Date;

public class Discount {
    private Float percentage;
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

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }
}