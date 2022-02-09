package com.example.comics.fakepaypal;

public class FakePayPal {
    public boolean isCardValid(String cardID){
        return cardID.length() >= 8;
    }
}
