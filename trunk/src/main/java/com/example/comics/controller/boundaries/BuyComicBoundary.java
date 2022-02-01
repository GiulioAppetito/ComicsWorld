package com.example.comics.controller.boundaries;

import com.example.comics.fakepaypal.FakePayPalControllerG;
import com.example.comics.model.exceptions.InvalidPaymentException;
import com.example.comics.model.fagioli.AccountBean;

public class BuyComicBoundary {

    public BuyComicBoundary(){
        //costruttore
    }


    public void convalidPayment(AccountBean accountBean) throws InvalidPaymentException {
        FakePayPalControllerG fakePayPalControllerG = new FakePayPalControllerG(accountBean.getFirstName(),accountBean.getLastName());

    }
}
