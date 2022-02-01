package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.fagioli.DiscountCodeBean;

import java.time.LocalDate;

public class DiscountCodeBundle implements DiscountCodeBean {

    private String code;
    private Float percentage;
    private LocalDate expiringDate;
    private int limitDays;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    @Override
    public Float getPercentage() {
        return this.percentage;
    }

    @Override
    public LocalDate getExpiringDate() {
        return this.expiringDate;
    }

    @Override
    public void setExpiringDate(LocalDate expiringDate) {
        this.expiringDate = expiringDate;
    }

    @Override
    public int getLimitDays() {
        return this.limitDays;
    }

    @Override
    public void setLimitDays(int limitDays) {
        this.limitDays = limitDays;
    }
}
