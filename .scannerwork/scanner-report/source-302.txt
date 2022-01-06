package com.example.comics.controller;

import com.example.comics.model.fagioli.LoginBean;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AccountDAO;

public class LoginController {

    public boolean login(LoginBean loginBean) throws Exception {

        AccountDAO dao = new AccountDAO();
        String role;
        role = dao.verifyCredentials(loginBean.getEmail(), loginBean.getPassword());
        if(role.equals("reader") || role.equals("author")){
            UserLogin.createAccount(loginBean.getEmail(), loginBean.getPassword(), role);
            return true;
        }
        return false;
    }
}
