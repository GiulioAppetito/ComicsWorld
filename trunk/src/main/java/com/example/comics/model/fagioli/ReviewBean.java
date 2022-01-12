package com.example.comics.model.fagioli;

import javafx.scene.image.ImageView;

public interface ReviewBean {

    public String getComment();

    public void setComment(String comment);

    public String getUsername();

    public void setUsername(String username);

    public String getChapter();

    public void setChapter(String chapter);

    public ImageView getPropic();

    public void setPropic(ImageView propic);

    public int getRating();

    public void setRating(int rating);


}
