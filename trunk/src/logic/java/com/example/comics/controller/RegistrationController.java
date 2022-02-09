package com.example.comics.controller;

import com.example.comics.model.exceptions.FailedRegistrationException;
import com.example.comics.model.exceptions.MalformedEmailException;
import com.example.comics.model.fagioli.RegistrationBean;
import com.example.comics.model.UserLogin;

public class RegistrationController {

    public void registerNewAccount(RegistrationBean registrationBean) throws FailedRegistrationException, MalformedEmailException {
        if(!registrationBean.getEmail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            throw new MalformedEmailException("Insert a valid email address!");
        }
        UserLogin.getInstance().register(registrationBean.getFirstName(),registrationBean.getLastName(), registrationBean.getUsername(),registrationBean.getEmail(),registrationBean.getPassword(),registrationBean.getRole());
    }
}
