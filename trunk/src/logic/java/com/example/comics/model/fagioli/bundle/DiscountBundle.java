package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.fagioli.DiscountBean;

public class DiscountBundle implements DiscountBean {

    private int limitDays;
    private Float percentage;

    @Override
    public int getLimitDays() {
        return limitDays;
    }

    @Override
    public void setLimitDays(int limitDays) {
        this.limitDays = limitDays;
    }

    @Override
    public Float getPercentage() {
        return percentage;
    }

    @Override
    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }
}
