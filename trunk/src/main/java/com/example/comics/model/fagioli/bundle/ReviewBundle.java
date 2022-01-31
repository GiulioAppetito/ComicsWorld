package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.Account;
import com.example.comics.model.fagioli.ReviewBean;

public class ReviewBundle implements ReviewBean {

    private String comment;
    private int rating;
    private Account account;

    @Override
    public String getComment() {
        return this.comment;
    }
    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public Account getAccount() {
        return account;
    }
    @Override
    public void setAccount(Account account) {
        this.account = account;
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
