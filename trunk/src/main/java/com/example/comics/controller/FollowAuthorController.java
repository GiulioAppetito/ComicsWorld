package com.example.comics.controller;

import com.example.comics.model.Author;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AuthorDAO;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.ReaderBean;

public class FollowAuthorController {

    public void followAuthor(AuthorBean authorBean){
        //aggiunta alla lista
        Author author = new Author();
        author.setUsername(authorBean.getUsername());
        author.setFirstName(authorBean.getFirstName());
        author.setLastName(authorBean.getLastName());
        author.setProPic(authorBean.getProPic());

        UserLogin.getInstance().getReader().addFollowedAuthor(author);
        System.out.println("[FOLLOW AUTHOR CONTROLLER] Author followed : "+author.getUsername());

        //persistenza
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.saveFollowedAuthor(author);
        System.out.println("[FOLLOW AUTHOR CONTROLLER] Saved author followed : "+author.getUsername());

        //eventuale logica addizionale


    }
}
