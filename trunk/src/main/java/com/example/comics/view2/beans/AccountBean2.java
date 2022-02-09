package com.example.comics.view2.beans;

import com.example.comics.model.fagioli.AccountBean;
import javafx.scene.image.Image;

import java.io.InputStream;

public class AccountBean2 implements AccountBean {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Image proPic;
    private InputStream inputStream;

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Image getProPic() {
        return this.proPic;
    }

    public void setProPic(Image proPic){
        this.proPic = proPic;
    }

}
