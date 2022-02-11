package com.example.comics.view1.beans;

import com.example.comics.model.fagioli.DiscountBean;

public class DiscountBean1 implements DiscountBean {

    int limitDays;
    Float percentage;

    @Override
    public int getLimitDays() {
        return this.limitDays;
    }

    @Override
    public void setLimitDays(int limitDays) {
        this.limitDays = limitDays;
    }

    public void setLimitDays(String limitDays) {
        this.limitDays = Integer.valueOf(limitDays);
    }

    @Override
    public Float getPercentage() {
        return this.percentage;
    }

    @Override
    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }


    public void setPercentage(String percentage) {
        this.percentage = Float.valueOf(percentage);
    }
}
