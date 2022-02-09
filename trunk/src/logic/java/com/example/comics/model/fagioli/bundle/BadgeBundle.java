package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.fagioli.BadgeBean;
import javafx.scene.image.Image;

public class BadgeBundle implements BadgeBean {

    private String name;
    private Image icon;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Image getIcon() {
        return this.icon;
    }

    @Override
    public void setIcon(Image icon) {
        this.icon = icon;
    }
}
