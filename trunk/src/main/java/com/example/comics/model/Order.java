package com.example.comics.model;

import java.time.LocalDate;

public class Order {
    private Series series;
    private LocalDate date;
    private Float expense;

    public Order(Series series){
        this.series = series;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getExpense() {
        return expense;
    }

    public void setExpense(Float expense) {
        this.expense = expense;
    }
}
