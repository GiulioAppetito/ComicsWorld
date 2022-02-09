package com.example.comics.view2.beans;

import com.example.comics.model.Account;
import com.example.comics.model.fagioli.ReviewBean;

public class ReviewBean2 implements ReviewBean {

    private String comment;
    private Account account;
    private Integer rating;


    @Override
    public String getComment() {
        return comment;
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
        return rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating/100;
    }

    public void setRating(Double rating){
        this.rating = rating.intValue()/20;
    }

}
