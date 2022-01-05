package com.example.comics.model.fagioli;

import com.example.comics.model.Review;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class ChapterBean {

    private String title;

    private Integer id;
    private Image cover;
    private List<Review> reviews = new ArrayList<>();
    private String description;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

        for(Review review : reviews){
            reviewBean = new ReviewBean();
            reviewBean.setComment(review.getComment());
            reviewBean.setUsername(review.getUsername());
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
}
