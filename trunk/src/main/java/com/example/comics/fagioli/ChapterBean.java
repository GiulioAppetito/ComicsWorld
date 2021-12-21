package com.example.comics.fagioli;

import com.example.comics.model.Review;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.Date;
import java.util.List;

public class ChapterBean {

    private String title;
    private String series;
    private Integer id;
    private ImageView cover;

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
}
