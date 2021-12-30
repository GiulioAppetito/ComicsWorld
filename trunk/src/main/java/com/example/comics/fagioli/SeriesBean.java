package com.example.comics.fagioli;

import com.example.comics.model.Chapter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SeriesBean {

    private String title;
    private Image cover;
    private String author;
    private List<Chapter> chapters = new ArrayList<>();


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

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public List<ChapterBean> getChapters(){

        ArrayList<ChapterBean> chapterBeans = new ArrayList<>();
        ChapterBean chapterBean;

        for(Chapter chapter : this.chapters){
            chapterBean = new ChapterBean();
            chapterBean.setTitle(chapter.getTitle());
            chapterBean.setSeries(this.title);
            chapterBean.setId(chapter.getId());
            chapterBean.setReviews(chapter.getReviews());
            //chapterBean.setCover(chapter.getCover());
            chapterBeans.add(chapterBean);
        }

        return chapterBeans;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
