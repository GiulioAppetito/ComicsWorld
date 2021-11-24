package com.example.comics;

import com.example.comics.model.Account;
import com.example.comics.model.UserLogin;

public class LoginController {

    private static LoginController inst;

    private LoginController(){

    }

    public static LoginController getInstance(){

        if(inst ==null){
            inst = new LoginController();
        }

        return inst;
    }

    public boolean login(String email, String password){

        //ricerca utente + validation
        return UserLogin.login(email, password);

    }
}
