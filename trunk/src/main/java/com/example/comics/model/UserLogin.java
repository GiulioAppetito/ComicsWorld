package com.example.comics.model;

public class UserLogin{

    private static Account account;

    public static Account getAccount() {
        return account;
    }

    public static boolean login(String username, String password){

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
