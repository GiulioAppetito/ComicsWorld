package com.example.comics.model;

import java.util.List;

public class Author extends Account{

    private List<Series> publishedSeries;

    private final String role = "author";

    @Override
    public String getRole(){
        return role;
    }

    public List<Series> getPublishedSeries() {
        return publishedSeries;
    }

    public void setPublishedSeries(List<Series> publishedSeries) {
        this.publishedSeries = publishedSeries;
    }


}
