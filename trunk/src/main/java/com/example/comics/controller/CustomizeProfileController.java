package com.example.comics.controller;

import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.ProfileBean;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AccountDAO;

public class CustomizeProfileController {

    public void changeUsername(ProfileBean profileBean){
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeUsername(profileBean.getUsername(), UserLogin.getInstance().getAccount().getUsername());
        UserLogin.getInstance().getAccount().changeCredential("username", profileBean.getUsername());
    }

    public void changeProPic(ProfileBean profileBean){
        UserLogin.getInstance().getAccount().changeProPic(profileBean.getProPic());
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeProPic(profileBean.getInputStream(),UserLogin.getInstance().getReader());
    }

    public void changeEmail(AccountBean accountBean){
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeCredentials(UserLogin.getInstance().getAccount().getFirstName(), UserLogin.getInstance().getAccount().getLastName(), accountBean.getEmail(), UserLogin.getInstance().getAccount().getUsername());
        UserLogin.getInstance().getAccount().changeCredential("email", accountBean.getEmail());
    }

    public void changeFirstName(AccountBean accountBean){
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeCredentials(accountBean.getFirstName(), UserLogin.getInstance().getAccount().getLastName(), UserLogin.getInstance().getAccount().getEmail(), UserLogin.getInstance().getAccount().getUsername());
        UserLogin.getInstance().getAccount().changeCredential("firstname", accountBean.getFirstName());
    }

    public void changeLastName(AccountBean accountBean){
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeCredentials(UserLogin.getInstance().getAccount().getFirstName(), accountBean.getLastName(), UserLogin.getInstance().getAccount().getEmail(), UserLogin.getInstance().getAccount().getUsername());
        UserLogin.getInstance().getAccount().changeCredential("lastname", accountBean.getLastName());
    }

}
