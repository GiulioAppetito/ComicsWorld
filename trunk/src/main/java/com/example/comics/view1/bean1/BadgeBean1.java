package com.example.comics.view1.bean1;

import com.example.comics.model.fagioli.BadgeBean;
import javafx.scene.image.Image;

public class BadgeBean1 implements BadgeBean {
    private String name;
    private Image icon;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Image getIcon() {
        return icon;
    }
    public void setIcon(Image icon) {
        this.icon = icon;
    }
}
