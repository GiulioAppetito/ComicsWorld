package com.example.comics.model.fagioli;

import com.example.comics.model.Badge;
import com.example.comics.model.Levels;
import javafx.scene.image.Image;

public class ObjectiveBean {

    private Badge badge;
    private Levels level;
    private String seriesTitle;


    public String getLevel() {
        return level.toString();
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

    public Image getBadgeIcon() {
        return badge.getIcon();
    }

    public String getBadgeName(){
        return badge.getName();
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }
}
