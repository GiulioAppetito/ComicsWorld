package com.example.comics.view2.beans;

import com.example.comics.model.fagioli.AbstractDiscountCodeBean;

public class DiscountCodeBean2 extends AbstractDiscountCodeBean {

    private String code;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
}
