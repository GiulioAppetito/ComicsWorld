package com.example.comics.model.dao.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DatesConverter {

    private DatesConverter(){
        //costruttore
    }

    public static final String PATTERN ="yyyy-MM-dd";

    public static LocalDate toLocalDate(String text) {
        LocalDate date=null;
        if(text!=null && !text.trim().isEmpty()) {
            date=LocalDate.parse(text, DateTimeFormatter.ofPattern(PATTERN));
        }
        return date;
    }

    public static String toString(LocalDate date) {
        String text=null;
        if(date!=null) {
            text=DateTimeFormatter.ofPattern(PATTERN).format(date);
        }
        return text;
    }

    public static long daysDifference(LocalDate date1, LocalDate date2){
        return ChronoUnit.DAYS.between(date1,date2);
    }
}
