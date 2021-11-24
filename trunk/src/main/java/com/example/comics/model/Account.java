package com.example.comics.model;

import javafx.scene.image.ImageView;

import java.util.Date;

public abstract class Account {

    private String firstName;
    private String lastName;
    private String username;
    private String city;
    private Date birthday;
    private ImageView proPic;

    private String Role;

    abstract public String getRole();


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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public ImageView getProPic() {
        return proPic;
    }

    public void setProPic(ImageView proPic) {
        this.proPic = proPic;
    }

}
