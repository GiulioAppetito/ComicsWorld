package com.example.comics;

import com.example.comics.fagioli.RegistrationBean;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AlreadyUsedUsernameException;

public class RegistrationController {

    public boolean registerNewAccount(RegistrationBean registrationBean){
         try{
             UserLogin.getInstance().register(registrationBean.getFirstName(),registrationBean.getLastName(), registrationBean.getUsername(),registrationBean.getEmail(),registrationBean.getPassword(),registrationBean.getRole());
             return true;
         } catch (AlreadyUsedUsernameException e) {
             System.out.println("Username already taken!");
             return false;
         }

    }


}
