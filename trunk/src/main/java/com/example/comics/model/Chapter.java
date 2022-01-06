package com.example.comics.model;

import com.example.comics.model.dao.ChapterDAO;
import com.example.comics.model.dao.ReviewsNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Chapter extends ChapterSubject {

    private String title;
    private Integer id;
    private Date publishingDate;
    private List<Review> reviews;
    private Image cover;
    private String description;

    public Chapter(String title) throws SQLException {

        this.title = title;
        //delego la responsabilità di creator delle review del chapter al chapterDAO che farà il retrieve delle review
        ChapterDAO chapterDAO = new ChapterDAO();
        try {
            this.reviews = chapterDAO.retrieveReviews(title);
        } catch (ReviewsNotFoundException e) {
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
