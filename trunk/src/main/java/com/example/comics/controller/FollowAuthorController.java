package com.example.comics.controller;

import com.example.comics.model.Author;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.view1.bean1.AuthorBean1;

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
        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.saveFollowedAuthor(author);
        System.out.println("[FOLLOW AUTHOR CONTROLLER] Saved author followed : "+author.getUsername());

        //eventuale logica addizionale


    }

    public boolean isAuthorFollowed(AuthorBean authorBean){
        for(Author author : UserLogin.getInstance().getReader().getFollowedAuthors()){
            if(author.getUsername().equals(authorBean.getUsername())){
                return true;
            }
        }
        return false;
    }

    public void unfollowAuthor(AuthorBean authorBean) {
        Author author = new Author();
        author.setUsername(authorBean.getUsername());
        UserLogin.getInstance().getReader().unfollowAuthor(author);

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.removeFollowedAuthor(UserLogin.getInstance().getReader(), author);

    }
}
