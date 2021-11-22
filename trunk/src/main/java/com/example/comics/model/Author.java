package com.example.comics.model;

import java.util.List;

public class Author extends User {
    private List<Series> publishedSeries;


    public List<Series> getPublishedSeries() {
        return publishedSeries;
    }

    public void setPublishedSeries(List<Series> publishedSeries) {
        this.publishedSeries = publishedSeries;
    }
}
