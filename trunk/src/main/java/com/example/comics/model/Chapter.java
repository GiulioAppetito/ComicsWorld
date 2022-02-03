package com.example.comics.model;

import com.example.comics.model.dao.ChapterDAO;
import com.example.comics.model.dao.ReviewDAO;
import com.example.comics.model.fagioli.ReviewBean;
import com.example.comics.model.fagioli.bundle.ReviewBundle;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chapter extends ChapterSubject {

    private String title;
    private Date publishingDate;
    private List<Review> reviews;
    private int averageRating;
    private Image cover;
    private String description;
    private Boolean isRead;
    private Float price;

    public Chapter(String title){
        Thread reviewsThread, ratingThread;

        this.title = title;

        reviewsThread = new Thread(() -> {
            ChapterDAO chapterDAO = new ChapterDAO();
            this.reviews = chapterDAO.retrieveReviews(title);
        });
        reviewsThread.start();

        ratingThread = new Thread(() ->{
            this.averageRating = calculateAverageRating();
        });
        ratingThread.start();

        try {
            reviewsThread.join();
            ratingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }
    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public Image getCover() {
        return cover;
    }
    public void setCover(Image cover) {
        this.cover = cover;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void addReview(Series series, String comment, int rating, Reader reader){
        Review review = new Review(comment, rating, reader);
        reviews.add(review);
        ReviewDAO reviewDAO = new ReviewDAO();
        try {
            reviewDAO.saveReview(review,this, series);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.averageRating = calculateAverageRating();

        ReviewBean reviewBean = new ReviewBundle();
        reviewBean.setRating(rating);
        reviewBean.setComment(comment);
        reviewBean.setAccount(reader);

        notifyObserversNewReview(reviewBean);
    }

    public int calculateAverageRating() {
        averageRating = 0;
        int count = 0;
        if(reviews == null){
            return 0;
        }
        for(Review review : reviews){
            averageRating = averageRating + review.getRating();
            count++;
        }

        if(count==0){
            return count;
        }

        return averageRating/count;
    }

    public int getAverageRating(){
        return averageRating;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
