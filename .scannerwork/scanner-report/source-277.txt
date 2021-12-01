package com.example.comics.model;

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

    public boolean login(String username, String password){

        //soluzione polimorfa
        if(Math.random() > 0.5){
            account = new Reader();
            account.setFirstName("Anastasia");
            account.setLastName("Brinati");
            return true;
        }
        account = new Author();
        account.setFirstName("Giulio");
        account.setLastName("Appetito");
        return true;

    }

}
