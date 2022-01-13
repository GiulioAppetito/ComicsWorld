package com.example.comics.controller;

import com.example.comics.model.exceptions.FailedRegistrationException;
import com.example.comics.model.fagioli.RegistrationBean;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AlreadyUsedUsernameException;

public class RegistrationController {

    public void registerNewAccount(RegistrationBean registrationBean) throws FailedRegistrationException{
        UserLogin.getInstance().register(registrationBean.getFirstName(),registrationBean.getLastName(), registrationBean.getUsername(),registrationBean.getEmail(),registrationBean.getPassword(),registrationBean.getRole());
    }
}
