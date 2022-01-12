package com.example.comics.view1.beans;

import com.example.comics.model.fagioli.AccountBean;
import javafx.scene.image.Image;

public class AccountBean1 implements AccountBean {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private Image proPic;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
