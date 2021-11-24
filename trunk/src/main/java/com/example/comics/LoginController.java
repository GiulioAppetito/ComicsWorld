package com.example.comics;

import com.example.comics.model.UserLogin;

public class LoginController {

    private static LoginController instance;

    private LoginController(){

    }

    public static LoginController getInstance(){

        if(instance ==null){
            instance = new LoginController();
        }

        return instance;
    }

    public boolean login(String email, String password){

        //ricerca utente + validation
        return UserLogin.getInstance().login(email, password);

    }
}
