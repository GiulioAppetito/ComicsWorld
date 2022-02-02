package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.fagioli.ReviewBean;

public class ReviewBundle implements ReviewBean {

    private String comment;
    private int rating;

    @Override
    public String getComment() {
        return this.comment;
    }
    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int getRating() {
        return this.rating;
    }
    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }

}
