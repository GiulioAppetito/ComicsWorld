package com.example.comics.fagioli;

import com.example.comics.model.Chapter;
import com.example.comics.model.Review;
import com.example.comics.model.dao.ReviewDAO;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChapterBean {

    private String title;
    private String series;
    private Integer id;
    private ImageView cover;
    private List<Review> reviews = new ArrayList<>();

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

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public ImageView getCover() {
        return cover;
    }
    public void setCover(ImageView cover) {
        this.cover = cover;
    }

    public List<ReviewBean> getReviews() throws SQLException {
        ReviewDAO reviewDAO = new ReviewDAO();
        ArrayList<ReviewBean> reviewBeans = new ArrayList<>();
        ReviewBean reviewBean ;

        for(Review review : reviews){
            //non so le info su review
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
}
