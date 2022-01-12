package com.example.comics.model.fagioli;

import com.example.comics.model.Badge;
import com.example.comics.model.Levels;
import javafx.scene.image.Image;

public interface ObjectiveBean {


    String getLevel();

    void setLevel(Levels level);


    Image getBadgeIcon();


    String getBadgeName();


    void setBadge(Badge badge);

    String getSeriesTitle();

    void setSeriesTitle(String seriesTitle);
}
