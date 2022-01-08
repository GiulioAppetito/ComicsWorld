package com.example.comics.controller;

import com.example.comics.model.Author;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.SeriesBean;
import java.sql.SQLException;

public class ToReadController {

    public boolean isSeriesAddedToRead(SeriesBean seriesBean){

        Author author = new Author();
        author.setUsername(seriesBean.getAuthor().getUsername());
        author.setPublishedSeries(seriesBean.getAuthor().getPublishedSeries());
        author.setFirstName(seriesBean.getAuthor().getFirstName());
        author.setLastName(seriesBean.getAuthor().getLastName());
        author.setProPic(seriesBean.getAuthor().getProPic());

        Series series = new Series(seriesBean.getTitle(), author);
        series.setCover(seriesBean.getCover());

        return UserLogin.getInstance().getReader().wantsToRead(series);

    }

    public void addSeriesToToRead(SeriesBean seriesBean) {

        Author author = new Author();
        author.setUsername(seriesBean.getAuthor().getUsername());
        author.setPublishedSeries(seriesBean.getAuthor().getPublishedSeries());
        author.setFirstName(seriesBean.getAuthor().getFirstName());
        author.setLastName(seriesBean.getAuthor().getLastName());
        author.setProPic(seriesBean.getAuthor().getProPic());

        Series series = new Series(seriesBean.getTitle(), author);
        series.setCover(seriesBean.getCover());
        //poi altri dettagli

        UserLogin.getInstance().getReader().addSeriesToToRead(series);

        ReaderDAO readerDAO = new ReaderDAO();
        try {
            readerDAO.addSeriesToToRead(series,UserLogin.getInstance().getReader());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSeriesFromToRead(SeriesBean seriesBean) {


        Author author = new Author();
        author.setUsername(seriesBean.getAuthor().getUsername());
        author.setPublishedSeries(seriesBean.getAuthor().getPublishedSeries());
        author.setFirstName(seriesBean.getAuthor().getFirstName());
        author.setLastName(seriesBean.getAuthor().getLastName());
        author.setProPic(seriesBean.getAuthor().getProPic());

        Series series = new Series(seriesBean.getTitle(), author);

        series.setCover(seriesBean.getCover());
        //poi altri dettagli

        UserLogin.getInstance().getReader().removeSeriesFromToRead(series);

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.removeSeriesFromToRead(series,UserLogin.getInstance().getReader());

    }
}







