package com.example.comics.controller;

import com.example.comics.model.Author;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.SeriesBean;

public class FavouritesController {

    public boolean isSeriesFavourite(SeriesBean seriesBean) {

        Author author = new Author();
        author.setUsername(seriesBean.getAuthor().getUsername());
        author.setPublishedSeries(seriesBean.getAuthor().getPublishedSeries());
        author.setFirstName(seriesBean.getAuthor().getFirstName());
        author.setLastName(seriesBean.getAuthor().getLastName());
        author.setProPic(seriesBean.getAuthor().getProPic());

        Series series = new Series(seriesBean.getTitle(), author);
        series.setCover(seriesBean.getCover());
        series.setAuthor(series.getAuthor());

        return UserLogin.getInstance().getReader().likesThisSeries(series);


    }

    public void addSeriesToFavourites(SeriesBean seriesBean) {

        Author author = new Author();
        author.setUsername(seriesBean.getAuthor().getUsername());
        author.setPublishedSeries(seriesBean.getAuthor().getPublishedSeries());
        author.setFirstName(seriesBean.getAuthor().getFirstName());
        author.setLastName(seriesBean.getAuthor().getLastName());
        author.setProPic(seriesBean.getAuthor().getProPic());

        Series series = new Series(seriesBean.getTitle(), author);
        series.setCover(seriesBean.getCover());
        //poi altri dettagli

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.addSeriesToFavourites(series, UserLogin.getInstance().getReader());

        UserLogin.getInstance().getReader().addSeriesToFavourites(series);

    }

    public void removeSeriesFromFavourites(SeriesBean seriesBean) {


        Author author = new Author();
        author.setUsername(seriesBean.getAuthor().getUsername());
        author.setPublishedSeries(seriesBean.getAuthor().getPublishedSeries());
        author.setFirstName(seriesBean.getAuthor().getFirstName());
        author.setLastName(seriesBean.getAuthor().getLastName());
        author.setProPic(seriesBean.getAuthor().getProPic());

        Series series = new Series(seriesBean.getTitle(), author);
        series.setCover(seriesBean.getCover());
        //poi altri dettagli

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.removeSeriesFromFavourites(series, UserLogin.getInstance().getReader());

        UserLogin.getInstance().getReader().removeSeriesFromFavourites(series);
    }
}
