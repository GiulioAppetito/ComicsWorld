package com.example.comics.model.fagioli;

import javafx.scene.image.Image;

import java.io.InputStream;

public interface AccountBean {

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getUsername();

    void setUsername(String username);

    Image getProPic();

    void setProPic(Image proPic);

    void setInputStream(InputStream inputStream);

    InputStream getInputStream();

}
