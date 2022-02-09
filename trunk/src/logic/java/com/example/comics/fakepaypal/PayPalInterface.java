package com.example.comics.fakepaypal;

public interface PayPalInterface {

    void startTransaction(String firstName, String lastName, String price);

    int convalidPayment();
}
