package com.example.comics.model;

import com.example.comics.model.dao.AccountDAO;
import com.example.comics.model.dao.AuthorDAO;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.exceptions.FailedRegistrationException;

public class UserLogin{

    private static UserLogin instance = null;
    private static Account account;

    private static Reader reader;
    private static Author author;

    private UserLogin(){}

    public static synchronized UserLogin getInstance(){
        if(instance==null){
            instance = new UserLogin();
        }
        return instance;
    }

    public Account getAccount() {
        return account;
    }

    public Reader getReader() {
        return reader;
    }

    public Author getAuthor() {
        return author;
    }

    public static boolean createAccount(String credential, String password, String role){

        UserLogin.account = new Author();
        if(role.equals("author")){
            AuthorDAO authorDAO = new AuthorDAO();
            UserLogin.author = authorDAO.retrieveAuthor(credential, password);
            UserLogin.account = author;

        }else if(role.equals("reader")){
            ReaderDAO readerDAO = new ReaderDAO();
            UserLogin.account.setUsername(credential);
            UserLogin.reader = readerDAO.retrieveReader(credential, password);
            UserLogin.account = reader;

        }else{
            return false;
        }

        return true;

    }


    public void register(String firstName, String lastName, String username, String email, String password, String role) throws FailedRegistrationException {
        AccountDAO dao = new AccountDAO();
        dao.registerNewAccount(firstName,lastName,username,email,password,role);
    }

}
