package com.example.comics.view2;

import com.example.comics.model.fagioli.BadgeBean;

public class Notification {

    private String message;
    private BadgeBean badgeBean;

    public Notification(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}
