package com.example.comics.view1.beans;

import com.example.comics.model.Levels;
import com.example.comics.model.fagioli.BadgeBean;
import com.example.comics.model.fagioli.DiscountBean;
import com.example.comics.model.fagioli.ObjectiveBean;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.Locale;

public class ObjectiveBean1 implements ObjectiveBean {

    private BadgeBean badgeBean;
    private Levels level;
    private DiscountBean discountBean;
    private String type;
    private Float requirement;
    private InputStream badgeIconInputStream;


    public String getLevel() {
        return level.toString();
    }

    @Override
    public void setLevel(Levels level) {
        this.level = level;
    }


    public void setLevel(String level) {
        this.level = Levels.valueOf(level);
    }

    @Override
    public void setBadgeBean(BadgeBean badgeBean) {
        this.badgeBean = badgeBean;
    }

    @Override
    public BadgeBean getBadgeBean() {
        return this.badgeBean;
    }

    @Override
    public String getType() {
        return this.type.toLowerCase(Locale.ROOT);
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Image getBadgeIcon() {
        return badgeBean.getIcon();
    }

    @Override
    public String getBadgeName() {
        return badgeBean.getName();
    }

    @Override
    public DiscountBean getDiscountBean() {
        return this.discountBean;
    }

    @Override
    public void setDiscountBean(DiscountBean discountBean) {
        this.discountBean = discountBean;
    }

    @Override
    public void setRequirement(Float requirement) {
        this.requirement = requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = Float.valueOf(requirement);
    }

    @Override
    public Float getRequirement() {
        return this.requirement;
    }

    @Override
    public void setBadgeIconInputStream(InputStream inputStream) {
        this.badgeIconInputStream = inputStream;
    }

    @Override
    public InputStream getBadgeIconInputStream() {
        return this.badgeIconInputStream;
    }
}
