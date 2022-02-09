package com.example.comics.model;

public class Review {

    private String comment;
    private Account account;
    private int rating;

    public Review(String comment, int rating, Account account){
            this.comment = comment;
            this.account = account;
            this.rating = rating;
    }

    public String getComment() {
        return comment;
    }
    public Account getAccount() {
        return account;
    }
    public int getRating() {
        return rating;
    }

}
