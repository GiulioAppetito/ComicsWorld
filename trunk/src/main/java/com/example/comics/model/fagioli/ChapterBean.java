package com.example.comics.model.fagioli;

import com.example.comics.model.Review;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public interface ChapterBean {

    String getTitle();
    void setTitle(String title) ;

    Image getCover();
    void setCover(Image cover);

    List<ReviewBean> getReviews();
    void setReviews(List<Review> reviews);

    void setDescription(String chapterDescription);
    String getDescription();

    int getAverageRating();
    void setAverageRating(int averageRating);

    void setRead(Boolean read);
    Boolean getRead();

    String getCoverPath();
    void setCoverPath(String path);
}
