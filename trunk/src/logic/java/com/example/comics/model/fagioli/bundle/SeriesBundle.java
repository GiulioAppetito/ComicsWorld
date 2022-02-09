package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.Genres;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SeriesBundle implements SeriesBean {

    private String title;
    private Image cover;
    private AuthorBean authorBean;
    private int averageRating;
    private List<ChapterBean> chapterBeans = new ArrayList<>();
    private Genres genre1;
    private Genres genre2;
    private Genres genre3;
    private InputStream coverInputStream;
    private String description;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Image getCover() {
        return cover;
    }
    public void setCover(Image cover) {
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

    public AuthorBundle getAuthor() {
        AuthorBundle authorBundle = new AuthorBundle();
        authorBundle.setPublishedSeries(authorBean.getPublishedSeries());
        authorBundle.setLastName(authorBean.getLastName());
        authorBundle.setFirstName(authorBean.getFirstName());
        authorBundle.setProPic(authorBean.getProPic());
        authorBundle.setUsername(authorBean.getUsername());
        authorBundle.setEmail(authorBean.getEmail());

        return authorBundle;
    }

    @Override
    public void setAuthor(AuthorBean authorBean) {
        this.authorBean = authorBean;
    }

    public List<ChapterBean> getChapters(){
        return chapterBeans;
    }

    public void setChapters(List<ChapterBean> chaptersBean) {
        this.chapterBeans = chaptersBean;
    }

    public Genres getGenre1() {
        return genre1;
    }

    public void setGenre1(Genres genre1) {
        this.genre1 = genre1;
    }

    public Genres getGenre2() {
        return genre2;
    }

    public void setGenre2(Genres genre2) {
        this.genre2 = genre2;
    }

    public Genres getGenre3() {
        return genre3;
    }

    public void setGenre3(Genres genre3) {
        this.genre3 = genre3;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}