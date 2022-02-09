package com.example.comics.view1.beans;

import com.example.comics.model.fagioli.AbstractDiscountCodeBean;

public class DiscountCodeBean1 extends AbstractDiscountCodeBean {

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
