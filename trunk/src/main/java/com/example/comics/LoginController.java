package com.example.comics;

import com.example.comics.fagioli.LoginBean;
import com.example.comics.model.UserLogin;

public class LoginController {

    public boolean login(LoginBean loginBean){

        //ricerca utente + validation
        return UserLogin.getInstance().login(loginBean.getEmail(), loginBean.getPassword());

    }
}
