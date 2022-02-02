package com.example.comics.model.fagioli;

import javafx.scene.image.Image;

import java.io.InputStream;

public interface AccountBean {

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);

    public String getEmail();

    public void setEmail(String email);

    public String getUsername();

    public void setUsername(String username);

    public Image getProPic();

    public void setProPic(Image proPic);

    void setInputStream(InputStream inputStream);

    InputStream getInputStream();

}
