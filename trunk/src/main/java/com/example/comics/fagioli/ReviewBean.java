package com.example.comics.fagioli;

import javafx.scene.image.ImageView;

public class ReviewBean {

    private String comment;
    private String username;
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
}
