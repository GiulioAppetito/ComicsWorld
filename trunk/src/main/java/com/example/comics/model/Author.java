package com.example.comics.model;

import java.util.ArrayList;

public class Author extends Account{

    private ArrayList<Series> publishedSeries;
    private ArrayList<Badge> badges;
    private ArrayList<Character> characters;

    public Author(){
        //dummy
    }

    public Author(ArrayList<Series> publishedSeries){
        this.publishedSeries = publishedSeries;
    }

    private static final String ROLE = "author";

    @Override
    public String getRole(){
        return ROLE;
    }

    public ArrayList<Series> getPublishedSeries() {
        return publishedSeries;
    }

    public void setPublishedSeries(ArrayList<Series> publishedSeries) {
        this.publishedSeries = publishedSeries;
    }


}
