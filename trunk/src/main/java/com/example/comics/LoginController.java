package com.example.comics;

import com.example.comics.model.Account;

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
        return Account.login(email, password);

    }
}
