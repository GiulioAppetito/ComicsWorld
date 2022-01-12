package com.example.comics.controller;

import com.example.comics.model.Author;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AuthorDAO;
import com.example.comics.model.fagioli.ObjectiveBean;
import com.example.comics.model.fagioli.SeriesBean;

import java.util.List;

public class PublishSeriesController {

    public void publishSeries(SeriesBean seriesBean, List<ObjectiveBean>objectiveBeans){

        Author author = new Author();
        author.setUsername(seriesBean.getAuthor().getUsername());
        author.setPublishedSeries(seriesBean.getAuthor().getPublishedSeries());
        author.setFirstName(seriesBean.getAuthor().getFirstName());
        author.setLastName(seriesBean.getAuthor().getLastName());
        author.setProPic(seriesBean.getAuthor().getProPic());

        System.out.println("PUBSERIESCON");
        Series series = new Series(seriesBean.getTitle(), author);

        series.setGenre1(seriesBean.getGenre1());
        series.setGenre2(seriesBean.getGenre2());
        series.setGenre3(seriesBean.getGenre3());


        //aggiunta della serie all'autore
        UserLogin.getInstance().getAuthor().addPublishedSeries(series);

        //salvataggio sul DB
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.savePublishedSeries(series);

    }
}
