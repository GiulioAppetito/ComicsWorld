package com.example.comics.fakeamazon;

public class FakeAmazon {
    public boolean isDiscountCodeValid(String discountCode){
        return Math.random() < 0.5;
    }
}
