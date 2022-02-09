package com.example.comics.view2.beans;

import com.example.comics.model.fagioli.LoginBean;

public class LoginBean2 implements LoginBean {

    private String email;
    private String password;

    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

}
