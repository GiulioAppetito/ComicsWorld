package com.example.comics.model;

import java.util.List;

public class Author extends Account{

    private List<Series> publishedSeries;
    private List<Badge> badges;
    private List<Character> characters;

    public Author(){
        //dummy
    }

    public Author(List<Series> publishedSeries){
        this.publishedSeries = publishedSeries;
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


}
