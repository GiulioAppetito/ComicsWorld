package com.example.comics.model;

import javafx.scene.image.ImageView;

import java.util.Date;
import java.util.List;

public abstract class User {

    private static User instance;

    public synchronized static User getInstance(char type){
        if(instance == null){
            if(type == 'a'){
                instance = new Author();
            }
            if(type == 'r'){
                instance = new Reader();
            }
        }
        return instance;
    }

    private String firstName;
    private String lastName;
    private String username;
    private String city;
    private Date birthday;
    private ImageView proPic;


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

    public static boolean login(String username, String password){
        if(Math.random() > 0.5){
            return true;
        }
        return true;
    }

}
