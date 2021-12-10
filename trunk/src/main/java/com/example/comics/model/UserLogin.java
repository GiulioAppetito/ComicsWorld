package com.example.comics.model;

import com.example.comics.model.dao.AccountDAO;

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
            account.setFirstName("Giulio");
            account.setLastName("Appetito");
        }else if(role.equals("reader")){
            account = new Reader();
            account.setFirstName("Anastasia");
            account.setLastName("Brinati");
        }

        return true;

    }

}
