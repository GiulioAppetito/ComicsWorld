package com.example.comics.fakeamazon;

public class FakeAmazon {

    public boolean isDiscountCodeValid(String discountCode){
        //check in our db/fle system
        return Math.random() < 0.5;
    }

    public static String getPrice(){
        return String.valueOf(Math.random()*10)+ "," + String.valueOf(Math.random() + "€");
    }

    //per chi te lo chiede tipo dopo post review
    public String generateDiscountCode(){
        return "";
    }

}
