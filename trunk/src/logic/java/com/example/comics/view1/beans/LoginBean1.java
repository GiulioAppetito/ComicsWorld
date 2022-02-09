package com.example.comics.view1.beans;

import com.example.comics.model.fagioli.LoginBean;

public class LoginBean1 implements LoginBean {
    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
