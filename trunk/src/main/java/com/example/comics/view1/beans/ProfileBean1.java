package com.example.comics.view1.beans;

import com.example.comics.model.fagioli.ProfileBean;
import javafx.scene.image.Image;

import java.io.InputStream;

public class ProfileBean1 implements ProfileBean {

    private String username;
    private Image proPic;
    private InputStream inputStream;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Image getProPic() {
        return proPic;
    }

    public void setProPic(Image proPic) {
        this.proPic = proPic;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
