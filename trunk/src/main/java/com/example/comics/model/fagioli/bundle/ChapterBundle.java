package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.Review;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.ReviewBean;
import javafx.scene.image.Image;

import java.util.List;

public class ChapterBundle implements ChapterBean {

    private List<ReviewBean> reviews;

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public Image getCover() {
        return null;
    }

    @Override
    public void setCover(Image cover) {

    }

    @Override
    public List<ReviewBean> getReviews() {
        return reviews;
    }

    @Override
    public void setReviews(List<Review> reviews) {
        //prende reviewNormali e le deve trasformare in beans
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
