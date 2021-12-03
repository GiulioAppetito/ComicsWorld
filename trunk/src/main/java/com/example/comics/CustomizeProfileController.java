package com.example.comics;

import com.example.comics.fagioli.ProfileBean;
import com.example.comics.model.UserLogin;

public class CustomizeProfileController {

    public void changeUsername(ProfileBean profileBean){
        UserLogin.getAccount().setUsername(profileBean.getUsername());
    }
    public void changeProPic(ProfileBean profileBean){
        UserLogin.getAccount().setProPic(profileBean.getProPic());
    }
}
