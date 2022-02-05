package com.example.comics.view1.beans;

import com.example.comics.model.fagioli.DiscountBean;
import com.example.comics.model.fagioli.DiscountCodeBean;

public abstract class AbstractDiscountCodeBean1 implements DiscountCodeBean {

    @Override
    public abstract String getCode();

    @Override
    public abstract void setCode(String code);

    @Override
    public DiscountBean getDiscountBean() {
        return null;
    }
    @Override
    public void setDiscountBean(DiscountBean discountBean) {

    }
}
