package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.fagioli.AccountBean;
import javafx.scene.image.Image;

import java.io.InputStream;

public class AccountBundle implements AccountBean {

    private String firstName;
    private String lastName;
    private String email;
    private Image proPic;
    private String username;
    private InputStream inputStream;

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Image getProPic() {
        return this.proPic;
    }

    @Override
    public void setProPic(Image proPic) {
        this.proPic = proPic;
    }

    @Override
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

}
