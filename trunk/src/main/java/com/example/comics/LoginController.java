package com.example.comics;

import com.example.comics.fagioli.LoginBean;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AccountDAO;

public class LoginController {

    public boolean login(LoginBean loginBean) throws Exception {

        AccountDAO dao = new AccountDAO();
        String role;
        role = dao.verifyCredentials(loginBean.getEmail(), loginBean.getPassword());
        System.out.println("Login Controller: role: " + role);
        if(role.equals("reader") || role.equals("author")){
            UserLogin.getInstance().createAccount(loginBean.getEmail(), loginBean.getPassword(), role);
            System.out.println("ROle: " + UserLogin.getInstance().getAccount().getRole());
            return true;
        }
        return false;
    }
}
