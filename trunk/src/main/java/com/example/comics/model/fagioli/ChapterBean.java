package com.example.comics.model.fagioli;

import com.example.comics.model.Review;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public interface ChapterBean {

    public String getTitle();
    public void setTitle(String title) ;

    public Image getCover();
    public void setCover(Image cover);

    public List<ReviewBean> getReviews();
    public void setReviews(List<Review> reviews);

    public void setDescription(String chapterDescription);
    public String getDescription();


    public int getAverageRating();
    public void setAverageRating(int averageRating);
}
