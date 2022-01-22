package com.example.comics.model.fagioli;

import com.example.comics.model.Genres;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.List;

public interface SeriesBean {

    String getTitle();

    void setTitle(String title);

    Image getCover();

    void setCover(Image cover);

    InputStream getCoverInputStream();

    void setCoverInputStream(InputStream inputStream);

    AuthorBean getAuthor();

    void setAuthor(AuthorBean authorBean);

    List<ChapterBean> getChapters();

    void setChapters(List<ChapterBean> chaptersBean);

    Genres getGenre1();

    void setGenre1(Genres genre1);

    Genres getGenre2();

    void setGenre2(Genres genre2);

    Genres getGenre3();

    void setGenre3(Genres genre3);

    void setAverageRating(int averageRating);

    int getAverageRating();

}