package com.example.comics.view1.bean1;

import com.example.comics.model.fagioli.ProfileBean;
import javafx.scene.image.Image;

public class ProfileBean1 implements ProfileBean {
    private String username;
    private Image proPic;


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

}
