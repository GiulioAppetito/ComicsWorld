package com.example.coomics.model;

import com.example.coomics.model.fagioli.LoginBean;

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

        //ricerca utente + validation
        if(Math.random() > 0.5){
            return true;
        }
        return true;
    }
}
