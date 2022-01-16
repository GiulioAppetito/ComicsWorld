package com.example.comics.model.fagioli;

import javafx.scene.image.Image;

import java.io.InputStream;

public interface ProfileBean {

    String getUsername();

    void setUsername(String username);

    Image getProPic();

    void setProPic(Image proPic);

    void setInputStream(InputStream inputStream);

    InputStream getInputStream();

}
