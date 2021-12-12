package com.example.comics.model;

import com.example.comics.fagioli.RegistrationBean;
import com.example.comics.model.dao.AccountDAO;
import com.example.comics.model.dao.AlreadyUsedUsernameException;

public class UserLogin{

    private static UserLogin instance;
    private static Account account;

    private UserLogin(){}

    public static synchronized UserLogin getInstance(){
        if(instance==null){
            instance = new UserLogin();
        }
        return instance;
    }

    public static Account getAccount() {
        return account;
    }

    public boolean login(String email, String password) throws Exception {

        AccountDAO dao = new AccountDAO();
        String role;
        role = dao.verifyCredentials(email,password);

        if(role.equals("author")){
            account = new Author();

        }else if(role.equals("reader")){
            account = new Reader();

        }
        account.init(email);

        return true;

    }


    public void register(String firstName, String lastName, String username, String email, String password, String role) throws AlreadyUsedUsernameException {
        AccountDAO dao = new AccountDAO();
        dao.registerNewAccount(firstName,lastName,username,email,password,role);
    }
}
