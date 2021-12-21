package com.example.comics.fagioli;

import com.example.comics.model.Chapter;
import javafx.scene.image.ImageView;

import java.util.List;

public class SeriesBean {

    private String title;
    private ImageView cover;
    private String author;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public ImageView getCover() {
        return cover;
    }
    public void setCover(ImageView cover) {
        this.cover = cover;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}
