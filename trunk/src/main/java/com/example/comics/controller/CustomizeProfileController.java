package com.example.comics.controller;

import com.example.comics.model.exceptions.FailedProfileCustomizationException;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AccountDAO;

public class CustomizeProfileController {

    public void changeUsername(AccountBean accountBean) throws FailedProfileCustomizationException {
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeUsername(accountBean.getUsername(), UserLogin.getInstance().getAccount().getUsername());
        UserLogin.getInstance().getAccount().changeCredential("username", accountBean.getUsername());
    }

    public void changeProPic(AccountBean accountBean){
        UserLogin.getInstance().getAccount().changeProPic(accountBean.getProPic());
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.changeProPic(accountBean.getInputStream(),UserLogin.getInstance().getReader());
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
