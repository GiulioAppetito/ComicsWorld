package com.example.comics.model.fagioli;

import com.example.comics.model.Series;
import javafx.scene.image.Image;

import java.util.List;

public interface AuthorBean {

    void setPublishedSeries(List<Series> publishedSeries);
    List<Series> getPublishedSeries();

    void setFirstName(String firstName);
    String getFirstName();

    String getLastName();
    void setLastName(String lastName);

    String getUsername();
    void setUsername(String username);

    Image getProPic();
    void setProPic(Image image);

    String getEmail();
    void setEmail(String email);

}
