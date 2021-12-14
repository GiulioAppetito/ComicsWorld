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
        String oldFirstName = this.firstName;
        System.out.println("2. [ACCOUNT] Parameter old firstName : "+ oldFirstName);
        this.firstName = firstName;
        //e va a cambiare anche su db
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeCredentials(this.firstName, this.lastName, this.username, this.username);
        System.out.println("Called DAO with username : "+ this.username);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        System.out.println("2. [ACCOUNT] Parameter old lastName : "+ oldLastName);
        this.lastName = lastName;
        //e va a cambiare anche su db
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeCredentials(this.firstName, this.lastName, this.username, this.username);
        System.out.println("Called DAO with username : "+ this.username);
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
        accountDAO.changeCredentials(this.firstName, this.lastName, this.username,oldUsername);
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
        String oldEmail = this.email;
        System.out.println("2. [ACCOUNT] Parameter old email : "+ oldEmail);
        this.email = email;
        //e va a cambiare anche su db
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeEmail(this.email, this.username);
        System.out.println("Called DAO with username : "+ this.username);
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
        this.email = credentials.get(3);
    }
}
