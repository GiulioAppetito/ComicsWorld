package com.example.comics.model;

import com.example.comics.AccountSubject;
import com.example.comics.model.dao.AccountDAO;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
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
        String oldUsername = this.username;
        System.out.println("2. [ACCOUNT] Parameter username : "+oldUsername);
        this.username = username;
        //e va a cambiare anche su db
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeUsername(this.firstName, this.lastName, oldUsername,this.username);
        System.out.println("Called DAO with username : "+ this.username);
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

    //init the account credentials through DB
    public void init(String input) {
        AccountDAO accountDAO = new AccountDAO();
        ArrayList<String> credentials = accountDAO.retreiveCredentials(input);
        this.firstName = credentials.get(0);
        this.lastName = credentials.get(1);
        this.username = credentials.get(2);


    }
}
