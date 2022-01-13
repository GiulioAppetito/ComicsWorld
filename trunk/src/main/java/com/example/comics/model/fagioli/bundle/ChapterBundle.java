package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.Review;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.ReviewBean;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ChapterBundle implements ChapterBean {

    private List<Review> reviews;
    private String title;
    private Image cover;
    private String description;
    private int rating;
    private Boolean isRead;


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
        ArrayList<ReviewBean> reviewBeans = new ArrayList<>();
        ReviewBundle reviewBundle;

        for(Review review : this.reviews){
            reviewBundle = new ReviewBundle();
            reviewBundle.setComment(review.getComment());
            reviewBundle.setRating(review.getRating());
            reviewBundle.setUsername(review.getUsername());
            reviewBeans.add(reviewBundle);
        }

        return reviewBeans;
    }

    @Override
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public void setDescription(String chapterDescription) {
        this.description = chapterDescription;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getAverageRating() {
        return this.rating;
    }

    @Override
    public void setAverageRating(int averageRating) {
        this.rating = rating;
    }

    @Override
    public Boolean getRead() {
        return isRead;
    }

    @Override
    public String getCoverPath() {
        return null;
    }

    @Override
    public void setCoverPath(String path) {

    }

    @Override
    public void setRead(Boolean read) {
        isRead = read;
    }
}
