package com.example.comics.model.fagioli;

import com.example.comics.model.Review;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class ChapterBean {

    private String title;
    private Image cover;
    private List<Review> reviews;
    private int averageRating;
    private String description;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Image getCover() {
        return cover;
    }
    public void setCover(Image cover) {
        this.cover = cover;
    }

    public List<ReviewBean> getReviews(){
        ArrayList<ReviewBean> reviewBeans = new ArrayList<>();
        ReviewBean reviewBean ;

        if(reviews.isEmpty()){
            return reviewBeans;
        }

        for(Review review : reviews){
            reviewBean = new ReviewBean();
            reviewBean.setComment(review.getComment());
            reviewBean.setUsername(review.getUsername());
            reviewBean.setRating(Integer.valueOf(review.getRating()));
            reviewBeans.add(reviewBean);
        }

        return reviewBeans;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setDescription(String chapterDescription) {
        this.description = chapterDescription;
    }
    public String getDescription(){
        return this.description;
    }


    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }
}
