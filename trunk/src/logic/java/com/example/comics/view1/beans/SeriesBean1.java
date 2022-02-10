package com.example.comics.view1.beans;

import com.example.comics.model.Genres;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.List;

public class SeriesBean1 implements SeriesBean {

    private String title;
    private Image cover;
    private AuthorBean authorBean;
    private List<ChapterBean> chapterBeans;
    private int rating;
    private InputStream coverInputStream;
    private Genres genre1;
    private Genres genre2;
    private Genres genre3;
    private String description;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Image getCover() {
        return cover;
    }

    @Override
    public void setCover(Image cover){
        this.cover = cover;
    }

    @Override
    public InputStream getCoverInputStream() {
        return this.coverInputStream;
    }

    @Override
    public void setCoverInputStream(InputStream inputStream) {
        this.coverInputStream = inputStream;
    }

    @Override
    public AuthorBean getAuthor() {
        return authorBean;
    }

    @Override
    public void setAuthor(AuthorBean authorBean){
        this.authorBean = authorBean;
    }

    @Override
    public List<ChapterBean> getChapters() {
        return chapterBeans;
    }

    @Override
    public void setChapters(List<ChapterBean> chapterBeans){
        this.chapterBeans = chapterBeans;
    }

    @Override
    public Genres getGenre1() {
        return this.genre1;
    }
    @Override
    public void setGenre1(Genres genre1) {this.genre1 = genre1;}
    @Override
    public Genres getGenre2() {
        return this.genre2;
    }
    @Override
    public void setGenre2(Genres genre2){ this.genre2 = genre2;}
    @Override
    public Genres getGenre3() {
        return this.genre3;
    }
    @Override
    public void setGenre3(Genres genre3){this.genre3 = genre3;}


    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
