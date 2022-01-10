package com.example.comics.controller;

import com.example.comics.model.Author;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.SeriesBean;

public class FavouritesController {

    public boolean isSeriesFavourite(SeriesBean seriesBean) {

        Series series = null;
        for(Series series1 : seriesBean.getAuthor().getPublishedSeries()){
            if(series1.getTitle().equals(seriesBean.getTitle())){
                series = series1;
            }
        }

        return UserLogin.getInstance().getReader().likesThisSeries(series);
    }

    public void addSeriesToFavourites(SeriesBean seriesBean) {

        Series series = null;
        for(Series series1 : seriesBean.getAuthor().getPublishedSeries()){
            if(series1.getTitle().equals(seriesBean.getTitle())){
                series = series1;
            }
        }

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.addSeriesToFavourites(series, UserLogin.getInstance().getReader());

        UserLogin.getInstance().getReader().addSeriesToFavourites(series);

    }

    public void removeSeriesFromFavourites(SeriesBean seriesBean) {

        Series series = null;
        for(Series series1 : seriesBean.getAuthor().getPublishedSeries()){
            if(series1.getTitle().equals(seriesBean.getTitle())){
                series = series1;
            }
        }

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.removeSeriesFromFavourites(series, UserLogin.getInstance().getReader());

        UserLogin.getInstance().getReader().removeSeriesFromFavourites(series);
    }
}
