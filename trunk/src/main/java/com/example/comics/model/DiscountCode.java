package com.example.comics.model;

import java.time.LocalDateTime;

public class DiscountCode {

    private String code;
    private LocalDateTime expiringDate;
    private final Discount discount;

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LENGHT = 6;


    public DiscountCode(Discount discount){
        this.discount = discount;
        LocalDateTime today = LocalDateTime.now();
        this.expiringDate = today.plusDays(this.discount.getLimitDays());

        StringBuilder generatedCode = new StringBuilder();
        int random;
        for(int i=0; i<LENGHT; i++){
            random = (int) (Math.random()*100 %26);
            if(i<=2){

                generatedCode.append(ALPHABET.charAt(random));
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
