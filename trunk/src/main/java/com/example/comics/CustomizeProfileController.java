package com.example.comics;

import com.example.comics.fagioli.AccountBean;
import com.example.comics.fagioli.ProfileBean;
import com.example.comics.model.UserLogin;

public class CustomizeProfileController {

    public void changeUsername(ProfileBean profileBean){
        UserLogin.getAccount().setUsername(profileBean.getUsername());
        UserLogin.getAccount().notifyObservers();
    }
    public void changeProPic(ProfileBean profileBean){
        UserLogin.getAccount().setProPic(profileBean.getProPic());
        UserLogin.getAccount().notifyObservers();
    }

    public void changeEmail(AccountBean accountBean){
        UserLogin.getAccount().setEmail(accountBean.getEmail());
        UserLogin.getAccount().notifyObservers();
    }

    public void changeFirstName(AccountBean accountBean){
        UserLogin.getAccount().setFirstName(accountBean.getFirstName());
        UserLogin.getAccount().notifyObservers();
    }

    public void changeLastName(AccountBean accountBean){
        UserLogin.getAccount().setLastName(accountBean.getLastName());
        UserLogin.getAccount().notifyObservers();
    }

}
