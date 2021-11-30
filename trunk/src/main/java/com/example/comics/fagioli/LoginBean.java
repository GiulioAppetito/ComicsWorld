package com.example.comics.fagioli;

import com.example.comics.LoginController;

import java.lang.invoke.WrongMethodTypeException;

public class LoginBean{
    private String email;
    private String password;

    public LoginBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws WrongCredentialException{
        if(email.contains("@")){
            this.email = email;
        }
        else{
            throw new WrongCredentialException();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
