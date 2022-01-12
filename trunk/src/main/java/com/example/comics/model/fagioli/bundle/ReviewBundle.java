package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.fagioli.ReviewBean;
import javafx.scene.image.ImageView;

public class ReviewBundle implements ReviewBean {

    private String comment;
    private String username;
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
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int getRating() {
        return this.rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }




    @Override
    public String getChapter() {
        return null;
    }
    @Override
    public void setChapter(String chapter) {

    }

    @Override
    public ImageView getPropic() {
        return null;
    }

    @Override
    public void setPropic(ImageView propic) {

    }
}
