package com.example.comics.fagioli;

import javafx.scene.image.ImageView;

public class ReviewBean {

    private String comment;
    private String username;
    private String series;
    private String chapter;
    private ImageView propic;

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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
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
}
