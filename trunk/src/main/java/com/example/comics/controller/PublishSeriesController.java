package com.example.comics.controller;

import com.example.comics.model.Author;
import com.example.comics.model.Objective;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AuthorDAO;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.ObjectiveBean;
import com.example.comics.model.fagioli.SeriesBean;

import java.util.List;

public class PublishSeriesController {

    public void publishSeries(SeriesBean seriesBean, List<ObjectiveBean>objectiveBeans){

        Series series;
        series = new Series(seriesBean.getTitle());
        series.setGenre1(seriesBean.getGenre1());
        series.setGenre2(seriesBean.getGenre2());
        series.setGenre3(seriesBean.getGenre3());
        //series.setCover(seriesBean.getCover());


        AuthorBean authorBean = seriesBean.getAuthor();
        Author author = new Author();
        author.setPublishedSeries(authorBean.getPublishedSeries());
        author.setUsername(authorBean.getUsername());
        author.setLastName(authorBean.getLastName());
        author.setProPic(authorBean.getProPic());
        author.setFirstName(authorBean.getFirstName());

        series.setAuthor(author);



        //aggiunta della serie all'autore
        UserLogin.getInstance().getAuthor().addPublishedSeries(series);

        //salvataggio sul DB
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.savePublishedSeries(series);

    }
}
