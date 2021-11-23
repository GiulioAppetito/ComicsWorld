package com.example.comics.model;

import java.util.List;

public class Reader {
    private Account account;
    private List<Series> favourites;
    private List<Series> toRead;
    private List<Series> reading;
    private int coins;

    public List<Series> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Series> favourites) {
        this.favourites = favourites;
    }

    public List<Series> getToRead() {
        return toRead;
    }

    public void setToRead(List<Series> toRead) {
        this.toRead = toRead;
    }

    public List<Series> getReading() {
        return reading;
    }

    public void setReading(List<Series> reading) {
        this.reading = reading;
    }

    public int getCoins() {
        return coins;
    }

    public void incrementCoins(int increment) {
        this.coins = this.coins + increment;
    }
}
