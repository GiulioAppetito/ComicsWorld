package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.Series;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.scene.image.Image;

import java.util.List;

public class AuthorBundle implements AuthorBean {

    private List<Series> publishedSeries;
    private String firstName;
    private String lastName;
    private String username;
    private Image proPic;
    private String email;

    public List<Series> getPublishedSeries() {
        return publishedSeries;
    }

    public void setPublishedSeries(List<Series> publishedSeries) {
        this.publishedSeries = publishedSeries;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Image getProPic() {
        return this.proPic;
    }

    public void setProPic(Image image){
        this.proPic=image;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
