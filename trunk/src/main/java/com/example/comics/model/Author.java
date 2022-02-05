package com.example.comics.model;

import java.util.ArrayList;
import java.util.List;

public class Author extends Account{

    private List<Series> publishedSeries = new ArrayList<>();

    public Author(){
        //dummy
    }

    private static final String ROLE = "author";

    @Override
    public String getRole(){
        return ROLE;
    }

    public List<Series> getPublishedSeries() {
        return publishedSeries;
    }

    public void setPublishedSeries(List<Series> publishedSeries) {
        this.publishedSeries = publishedSeries;
    }

    public void addPublishedSeries(Series series){
        this.publishedSeries.add(series);
    }

}
