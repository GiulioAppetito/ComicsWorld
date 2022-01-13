package com.example.comics.view1.beans;

import com.example.comics.model.Review;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.ReviewBean;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ChapterBean1 implements ChapterBean {

    private String title;
    private Image cover;
    private final List<ReviewBean> reviews = new ArrayList<>();
    private int rating;
    private String description;
    private Boolean isRead;
    private String coverPath;

    @Override
    public String getTitle() {
        return this.title;
    }
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Image getCover() {
        return this.cover;
    }
    @Override
    public void setCover(Image cover) {
        this.cover = cover;
    }

    @Override
    public List<ReviewBean> getReviews() {
        return this.reviews;
    }

    @Override
    public void setReviews(List<Review> reviews) {

    }

    @Override
    public void setDescription(String chapterDescription) {
        this.description = chapterDescription;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getAverageRating() {
        return this.rating;
    }

    @Override
    public void setAverageRating(int averageRating) {
        this.rating = averageRating;
    }

    @Override
    public void setRead(Boolean read) {
        this.isRead = read;
    }

    @Override
    public Boolean getRead() {
        return this.isRead;
    }

    @Override
    public String getCoverPath() {
        return this.coverPath;
    }

    @Override
    public void setCoverPath(String path) {
        this.coverPath = path;
    }
}
