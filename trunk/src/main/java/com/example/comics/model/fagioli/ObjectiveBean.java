package com.example.comics.model.fagioli;

import com.example.comics.model.Badge;
import com.example.comics.model.Levels;
import javafx.scene.image.Image;

public interface ObjectiveBean {

    public String getLevel();

    public void setLevel(Levels level);

    public Image getBadgeIcon();

    public String getBadgeName();

    public void setBadge(Badge badge);

    public String getSeriesTitle();

    public void setSeriesTitle(String seriesTitle);

}
