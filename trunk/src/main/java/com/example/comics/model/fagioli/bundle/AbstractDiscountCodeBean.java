package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.fagioli.DiscountBean;
import com.example.comics.model.fagioli.DiscountCodeBean;

public abstract class AbstractDiscountCodeBean implements DiscountCodeBean {

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
