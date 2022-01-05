package com.example.comics.controller;

import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.ProfileBean;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AccountDAO;

public class CustomizeProfileController {

    public void changeUsername(ProfileBean profileBean){
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeCredentials(UserLogin.getInstance().getAccount().getFirstName(), UserLogin.getInstance().getAccount().getLastName(), profileBean.getUsername(), UserLogin.getInstance().getAccount().getUsername());
        UserLogin.getInstance().getAccount().changeCredential("username", profileBean.getUsername());
        UserLogin.getInstance().getAccount().notifyObservers();
    }

    public void changeProPic(ProfileBean profileBean){
        UserLogin.getInstance().getAccount().changeProPic(profileBean.getProPic());
        UserLogin.getInstance().getAccount().notifyObservers();
    }

    public void changeEmail(AccountBean accountBean){
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeEmail(accountBean.getEmail(), accountBean.getPassword());
        UserLogin.getInstance().getAccount().changeCredential("email", accountBean.getEmail());
        UserLogin.getInstance().getAccount().notifyObservers();
    }

    public void changeFirstName(AccountBean accountBean){
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeCredentials(accountBean.getFirstName(), accountBean.getLastName(), accountBean.getEmail(), UserLogin.getInstance().getAccount().getUsername());
        UserLogin.getInstance().getAccount().changeCredential("firstName", accountBean.getFirstName());
        UserLogin.getInstance().getAccount().notifyObservers();
    }

    public void changeLastName(AccountBean accountBean){
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeCredentials(accountBean.getFirstName(), accountBean.getLastName(), accountBean.getEmail(), UserLogin.getInstance().getAccount().getUsername());
        UserLogin.getInstance().getAccount().changeCredential("lastName", accountBean.getLastName());
        UserLogin.getInstance().getAccount().notifyObservers();
    }

}
