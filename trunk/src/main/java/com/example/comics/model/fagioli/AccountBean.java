package com.example.comics.model.fagioli;

import javafx.scene.image.Image;

public interface AccountBean {

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getUsername();

    void setUsername(String username);

    Image getProPic();

    void setProPic(Image proPic);
}
