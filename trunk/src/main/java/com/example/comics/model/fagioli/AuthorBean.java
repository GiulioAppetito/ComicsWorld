package com.example.comics.model.fagioli;

import com.example.comics.model.Series;
import javafx.scene.image.Image;

import java.util.List;

public interface AuthorBean {

    public void setPublishedSeries(List<Series> publishedSeries);
    public List<Series> getPublishedSeries();

    public void setFirstName(String firstName);
    public String getFirstName();

    public String getLastName();
    public void setLastName(String lastName);

    public String getUsername();
    public void setUsername(String username);

    public Image getProPic();
    public void setProPic(Image image);

}
