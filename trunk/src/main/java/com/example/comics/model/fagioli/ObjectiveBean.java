package com.example.comics.model.fagioli;

import com.example.comics.model.Levels;
import javafx.scene.image.Image;

import java.io.InputStream;

public interface ObjectiveBean {

    String getLevel();
    void setLevel(Levels level);

    void setBadgeBean(BadgeBean badgeBean);
    BadgeBean getBadgeBean();

    String getType();
    void setType(String type);

    Image getBadgeIcon();
    String getBadgeName();

    DiscountBean getDiscountBean();
    void setDiscountBean(DiscountBean discountBean);

    void setRequirement(Float requirement);
    Float getRequirement();

    void setBadgeIconInputStream(InputStream inputStream);
    InputStream getBadgeIconInputStream();



}
