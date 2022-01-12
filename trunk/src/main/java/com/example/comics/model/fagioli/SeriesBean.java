package com.example.comics.model.fagioli;

import com.example.comics.model.Author;
import com.example.comics.model.Chapter;
import com.example.comics.model.Genres;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.view1.bean1.AuthorBean1;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public interface SeriesBean {

    String getTitle();
    void setTitle(String title);

    Image getCover();
    void setCover(Image cover);

    AuthorBean1 getAuthor();

    void setAuthor(Author author);

    List<ChapterBean> getChapters();

    void setChapters(List<Chapter> chapters);

    Genres getGenre1();

    void setGenre1(Genres genre1);

    Genres getGenre2();

    void setGenre2(Genres genre2);

    Genres getGenre3();

    void setGenre3(Genres genre3);


}
