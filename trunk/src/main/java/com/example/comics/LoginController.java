package com.example.comics;

import com.example.comics.model.User;

public class LoginController {
    public static LoginController INSTANCE;

    private LoginController(){

    }

    public static LoginController getInstance(){

        if(INSTANCE==null){
            INSTANCE = new LoginController();
        }

        return INSTANCE;
    }

    public boolean login(String email, String password){

        //ricerca utente + validationù
        return User.login(email, password) == true;

    }
}
