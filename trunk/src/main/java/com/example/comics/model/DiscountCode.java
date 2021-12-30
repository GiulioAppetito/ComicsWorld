package com.example.comics.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DiscountCode {

    private String code;
    private LocalDateTime expiringDate;
    private final Discount discount;

    private final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int lenght = 6;


    public DiscountCode(Discount discount){
        this.discount = discount;
        LocalDateTime today = LocalDateTime.now();
        this.expiringDate = today.plusDays(this.discount.getLimitDays());

        StringBuilder generatedCode = new StringBuilder();
        int random;
        for(int i=0; i<lenght; i++){
            random = (int) ((Math.random()*100 %26));
            System.out.println("******* RANDOM NUMBER ******* = "+random);
            if(i<=2){

                generatedCode.append(characters.charAt(random));
            }else{
                generatedCode.append(random % 10);
            }
        } // generazione codice random
        this.code = generatedCode.toString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(LocalDateTime expiringDate) {
        this.expiringDate = expiringDate;
    }

    public Discount getDiscount() {
        return discount;
    }


}
