package com.example.comics.model.fagioli;

import com.example.comics.model.Series;
import javafx.scene.image.Image;

import java.util.List;

public interface AuthorBean {
    List<Series> getPublishedSeries();
    void setPublishedSeries(List<Series> publishedSeries);
    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    String getUsername();
    void setUsername(String username);
    Image getProPic();
    void setProPic(Image image);
}
