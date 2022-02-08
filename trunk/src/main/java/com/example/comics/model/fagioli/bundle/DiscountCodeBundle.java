package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.fagioli.DiscountBean;
import com.example.comics.model.fagioli.DiscountCodeBean;

public class DiscountCodeBundle implements DiscountCodeBean {

    private String code;
    private DiscountBean discountBean;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public DiscountBean getDiscountBean() {
        return discountBean;
    }

    @Override
    public void setDiscountBean(DiscountBean discountBean) {
        this.discountBean = discountBean;
    }
}
