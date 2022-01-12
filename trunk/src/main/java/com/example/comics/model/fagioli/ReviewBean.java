package com.example.comics.model.fagioli;

import javafx.scene.image.ImageView;

public interface ReviewBean {


    String getComment();


    void setComment(String comment);

    String getUsername();


    void setUsername(String username);


    String getChapter();


    void setChapter(String chapter);


    ImageView getPropic();


    void setPropic(ImageView propic);


    int getRating();


    void setRating(int rating);



}
