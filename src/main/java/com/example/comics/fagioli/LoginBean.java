package com.example.comics.fagioli;

import com.example.comics.model.LoginController;

public class LoginBean {
    private String email;
    private String password;

    public LoginBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validate() {
        // controllo sintattico
        if (email == null || email.equals("") || password == null || password.equals("")) {
            return false;
        }

        return LoginController.getInstance().login(email, password);
    }

}
