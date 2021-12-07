package com.example.comics.model;

import com.example.comics.AccountSubject;
import javafx.scene.image.ImageView;

import java.util.Date;

public abstract class Account extends AccountSubject {

    //profile personal
    private String firstName;
    private String lastName;
    private Date birthday;

    //profile basics
    private String username;
    private ImageView proPic;

    //profile private
    private String city;
    private String email;
    private String password;


    public abstract String getRole();


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
        //e va a cambiare anche su db
        System.out.println(username);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
