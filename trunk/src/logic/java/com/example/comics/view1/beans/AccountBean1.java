package com.example.comics.view1.beans;

import com.example.comics.model.fagioli.AccountBean;
import javafx.scene.image.Image;

import java.io.InputStream;

public class AccountBean1 implements AccountBean {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Image proPic;
    private InputStream inputStream;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setProPic(Image proPic){
        this.proPic = proPic;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
