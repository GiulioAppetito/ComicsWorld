package com.example.comics.controller;

import com.example.comics.controller.boundaries.FollowAuthorBoundary;
import com.example.comics.model.Author;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.fagioli.AuthorBean;

public class FollowAuthorController {

    public void followAuthor(AuthorBean authorBean){
        //aggiunta alla lista
        Author author = new Author();
        author.setUsername(authorBean.getUsername());
        author.setFirstName(authorBean.getFirstName());
        author.setLastName(authorBean.getLastName());
        author.setProPic(authorBean.getProPic());
        author.setEmail(authorBean.getEmail());

        UserLogin.getInstance().getReader().addFollowedAuthor(author);

        //persistenza
        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.saveFollowedAuthor(author);

        //comunicazione con la boundary dell'autore
        FollowAuthorBoundary followAuthorBoundary = new FollowAuthorBoundary();
        followAuthorBoundary.sendEmailForNewFollower(author,UserLogin.getInstance().getReader());

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
