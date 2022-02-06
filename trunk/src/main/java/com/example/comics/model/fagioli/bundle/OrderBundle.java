package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.fagioli.OrderBean;
import com.example.comics.model.fagioli.SeriesBean;

import java.time.LocalDate;

public class OrderBundle implements OrderBean {

    private SeriesBean series;
    private LocalDate date;
    private Float expense;

    @Override
    public SeriesBean getSeries() {
        return series;
    }

    @Override
    public void setSeries(SeriesBean series) {
        this.series = series;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public Float getExpense() {
        return expense;
    }

    @Override
    public void setExpense(Float expense) {
        this.expense = expense;
    }

}
