package com.example.comics.model;

import com.example.comics.ChapterSubject;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chapter extends ChapterSubject {

    private String title;
    private String series;
    private Integer id;
    private Date publishingDate;
    private ArrayList<Review> reviews;
    private ImageView cover;
    private Text description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public ImageView getCover() {
        return cover;
    }

    public void setCover(ImageView cover) {
        this.cover = cover;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public Text getDescription() {
        return description;
    }

    public void setDescription(Text description) {
        this.description = description;
    }

    public void addReview(String comment, String username){
        Review review = new Review(comment, username);
        reviews.add(review);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
