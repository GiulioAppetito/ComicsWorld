package com.example.comics.model;

import com.example.comics.model.dao.AccountDAO;
import com.example.comics.model.dao.SeriesDAO;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Account{

    private ArrayList<Series> favourites;
    private ArrayList<Series> toRead;
    private ArrayList<Series> reading;

    public Reader(ArrayList<Series> favourites, ArrayList<Series> toRead, ArrayList<Series> reading){

        this.favourites = favourites;
        this.toRead = toRead;
        this.reading = reading;

    }

    private static final String ROLE = "reader";

    @Override
    public String getRole(){
        return ROLE;
    }

    public ArrayList<Series> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Series> favourites) {
        this.favourites = favourites;
    }

    public ArrayList<Series> getToRead() {
        return toRead;
    }

    public void setToRead(ArrayList<Series> toRead) {
        this.toRead = toRead;
    }

    public ArrayList<Series> getReading() {
        return reading;
    }

    public void setReading(ArrayList<Series> reading) {
        this.reading = reading;
    }

}
