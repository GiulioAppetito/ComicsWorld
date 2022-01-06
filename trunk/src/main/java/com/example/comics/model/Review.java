package com.example.comics.model;

public class Review {

    private String comment;
    private String username;
    private String chapter;
    private String rating;

    public Review(String comment, String username){
            this.comment = comment;
            this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
