package com.example.comics.model.fagioli;

import javafx.scene.image.ImageView;

public class ReviewBean {

    private String comment;
    private String username;
    private String chapter;
    private ImageView propic;
    private int rating;

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

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public ImageView getPropic() {
        return propic;
    }

    public void setPropic(ImageView propic) {
        this.propic = propic;
    }

    public String getRating() {
        return String.valueOf(rating);
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
