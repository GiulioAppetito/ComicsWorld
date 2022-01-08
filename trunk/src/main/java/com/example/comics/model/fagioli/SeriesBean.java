package com.example.comics.model.fagioli;

import com.example.comics.model.Author;
import com.example.comics.model.Chapter;
import com.example.comics.model.Genres;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class SeriesBean {

    private String title;
    private Image cover;
    private Author author;
    private List<Chapter> chapters = new ArrayList<>();
    private Genres genre1;
    private Genres genre2;
    private Genres genre3;


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

    public AuthorBean getAuthor() {
        AuthorBean authorBean = new AuthorBean();
        authorBean.setPublishedSeries(author.getPublishedSeries());
        authorBean.setLastName(author.getLastName());
        authorBean.setFirstName(author.getFirstName());
        authorBean.setProPic(author.getProPic());
        authorBean.setUsername(author.getUsername());

        return authorBean;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<ChapterBean> getChapters(){

        ArrayList<ChapterBean> chapterBeans = new ArrayList<>();
        ChapterBean chapterBean;

        for(Chapter chapter : this.chapters){
            chapterBean = new ChapterBean();
            chapterBean.setTitle(chapter.getTitle());
            chapterBean.setReviews(chapter.getReviews());
            chapterBean.setDescription(chapter.getDescription());
            chapterBean.setAverageRating(chapter.getAverageRating());
            chapterBeans.add(chapterBean);
        }

        return chapterBeans;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
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
}
