package com.example.comics.model;

public class Review {

    private String comment;
    private String username;
    private int rating;

    public Review(String comment, int rating, String username){
            this.comment = comment;
            this.username = username;
            this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

}
