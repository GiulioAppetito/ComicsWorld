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

        Series series = new Series(seriesBean.getTitle());
        series.setCover(seriesBean.getCover());
        series.setAuthor(series.getAuthor());

        return UserLogin.getInstance().getReader().wantsToRead(series);

    }

    public void addSeriesToToRead(SeriesBean seriesBean) {
        Series series = new Series(seriesBean.getTitle());
        AuthorBean authorBean = seriesBean.getAuthor();
        Author author = new Author();
        author.setPublishedSeries(authorBean.getPublishedSeries());
        author.setUsername(authorBean.getUsername());
        author.setLastName(authorBean.getLastName());
        author.setProPic(authorBean.getProPic());
        author.setFirstName(authorBean.getFirstName());

        series.setAuthor(author);
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

        Series series = new Series(seriesBean.getTitle());
        AuthorBean authorBean = seriesBean.getAuthor();
        Author author = new Author();
        author.setPublishedSeries(authorBean.getPublishedSeries());
        author.setUsername(authorBean.getUsername());
        author.setLastName(authorBean.getLastName());
        author.setProPic(authorBean.getProPic());
        author.setFirstName(authorBean.getFirstName());

        series.setAuthor(author);
        series.setCover(seriesBean.getCover());
        //poi altri dettagli

        UserLogin.getInstance().getReader().removeSeriesFromToRead(series);

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.removeSeriesFromToRead(series,UserLogin.getInstance().getReader());

    }
}







