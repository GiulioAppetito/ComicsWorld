package com.example.comics.model;

import com.example.comics.ChapterSubject;
import com.example.comics.model.dao.ChapterDAO;
import com.example.comics.model.dao.ReviewsNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Chapter extends ChapterSubject {

    private String title;
    private String series;
    private Integer id;
    private Date publishingDate;
    private List<Review> reviews;
    private Image cover;
    private Text description;

    public Chapter(String series, String title) throws SQLException {

        this.series = series;
        this.title = title;
        //delego la responsabilità di creator delle review del chapter al chapterDAO che farà il retreive delle review
        ChapterDAO chapterDAO = new ChapterDAO();
        try {
            this.reviews = chapterDAO.retrieveReviews(this.series,this.title);
        } catch (ReviewsNotFoundException e) {
            System.out.println("No reviews found on "+this.series + " - "+this.title);
        }
    }

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
