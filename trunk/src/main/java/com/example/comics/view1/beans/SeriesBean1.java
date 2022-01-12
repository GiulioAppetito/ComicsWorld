package com.example.comics.view1.beans;

import com.example.comics.model.Author;
import com.example.comics.model.Chapter;
import com.example.comics.model.Genres;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.scene.image.Image;

import java.util.List;

public class SeriesBean1 implements SeriesBean {

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {}

    @Override
    public Image getCover() {
        return null;
    }

    @Override
    public void setCover(Image cover){}

    @Override
    public AuthorBean getAuthor() {
        return null;
    }

    @Override
    public void setAuthor(Author author){}

    @Override
    public List<ChapterBean> getChapters() {
        return null;
    }

    @Override
    public void setChapters(List<Chapter> chapters){
    }

    @Override
    public Genres getGenre1() {
        return null;
    }
    @Override
    public void setGenre1(Genres genre1) {}
    @Override
    public Genres getGenre2() {
        return null;
    }
    @Override
    public void setGenre2(Genres genre2){}
    @Override
    public Genres getGenre3() {
        return null;
    }
    @Override
    public void setGenre3(Genres genre3){}


    @Override
    public void setAverageRating(int averageRating) {
    }
    @Override
    public int getAverageRating() {
        return 0;
    }
}
