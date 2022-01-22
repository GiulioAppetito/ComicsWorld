package com.example.comics.model.fagioli;

import com.example.comics.model.Badge;
import com.example.comics.model.Discount;
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
    void setBadge(Badge badge);

    String getSeriesTitle();
    void setSeriesTitle(String seriesTitle);

    DiscountBean getDiscountBean();
    void setDiscountBean(DiscountBean discountBean);

    void setRequirement(int requirement);
    int getRequirement();

    void setBadgeIconInputStream(InputStream inputStream);
    InputStream getBadgeIconInputStream();



}
