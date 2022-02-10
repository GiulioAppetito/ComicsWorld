package com.example.comics.model.fagioli;

public abstract class AbstractDiscountCodeBean implements DiscountCodeBean {

    private DiscountBean discountBean;

    @Override
    public DiscountBean getDiscountBean() {
        return discountBean;
    }

    @Override
    public void setDiscountBean(DiscountBean discountBean) {
        this.discountBean = discountBean;
    }
}
