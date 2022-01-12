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
    private List<ReviewBean> reviews = new ArrayList<>();

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

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getAverageRating() {
        return 0;
    }

    @Override
    public void setAverageRating(int averageRating) {

    }
}
