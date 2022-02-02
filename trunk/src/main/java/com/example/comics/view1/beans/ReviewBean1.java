package com.example.comics.view1.beans;

import com.example.comics.model.Account;
import com.example.comics.model.fagioli.ReviewBean;

public class ReviewBean1 implements ReviewBean {

    private String comment;
    private Account account;
    private int rating;

    public String getComment() {
        return comment;
    }
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
