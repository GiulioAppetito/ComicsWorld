package com.example.comics.model.fagioli;

import com.example.comics.model.Author;
import com.example.comics.model.Chapter;
import com.example.comics.model.Genres;
import javafx.scene.image.Image;
import java.util.List;

public interface SeriesBean {

    public String getTitle();

    public void setTitle(String title);

    public Image getCover();

    public void setCover(Image cover);

    public AuthorBean getAuthor();

    public void setAuthor(Author author);

    public List<ChapterBean> getChapters();

    public void setChapters(List<Chapter> chapters);

    public Genres getGenre1();

    public void setGenre1(Genres genre1);

    public Genres getGenre2();

    public void setGenre2(Genres genre2);

    public Genres getGenre3();

    public void setGenre3(Genres genre3);

    public void setAverageRating(int averageRating);

    public int getAverageRating();

}