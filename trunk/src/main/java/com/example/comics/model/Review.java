package com.example.comics.model;

public class Review {

    private String comment;
    private String username;

    public Review(String comment, String username){
            this.comment = comment;
            this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
