package com.example.comics.model;

import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DiscountCode {

    private String code;
    private LocalDate expiringDate;
    private final Discount discount;

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LENGHT = 6;


    public DiscountCode(Discount discount, String code, LocalDate expiringDate){
        this.discount = discount;
        this.code = code;
        this.expiringDate = expiringDate;
    }

    public DiscountCode(Discount discount){
        this.discount = discount;

        //set expiring date
        this.expiringDate = LocalDate.now().plusDays(discount.getLimitDays());

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

    public LocalDate getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(LocalDate expiringDate) {
        this.expiringDate = expiringDate;
    }

    public Discount getDiscount() {
        return discount;
    }


}
