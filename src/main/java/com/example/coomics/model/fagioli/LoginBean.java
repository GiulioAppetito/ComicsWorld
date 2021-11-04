package com.example.coomics.model.fagioli;

import com.example.coomics.model.LoginController;

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
        if (email == null || email == "" || password == null || password == "") {
            return false;
        }

        boolean utenteTrovato = LoginController.getInstance().login(email, password);
        return utenteTrovato;

    }

}
