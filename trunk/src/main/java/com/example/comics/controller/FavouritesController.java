package com.example.comics.controller;

import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.fagioli.SeriesBean;

public class FavouritesController {

    public boolean isSeriesFavourite(SeriesBean seriesBean) {

        for(Series series : seriesBean.getAuthor().getPublishedSeries()){
            if(series.getTitle().equals(seriesBean.getTitle())){
                return UserLogin.getInstance().getReader().likesThisSeries(series);
            }
        }

        return false;
    }

    public void addSeriesToFavourites(SeriesBean seriesBean) {

        for(Series series : seriesBean.getAuthor().getPublishedSeries()){
            if(series.getTitle().equals(seriesBean.getTitle())){
                new Thread(()->{
                    ReaderDAO readerDAO = new ReaderDAO();
                    readerDAO.addSeriesToFavourites(series, UserLogin.getInstance().getReader());
                }).start();

                new Thread(()->{
                    UserLogin.getInstance().getReader().addSeriesToFavourites(series);
                }).start();
            }
        }

    }

    public void removeSeriesFromFavourites(SeriesBean seriesBean) {

        for(Series series : seriesBean.getAuthor().getPublishedSeries()){
            if(series.getTitle().equals(seriesBean.getTitle())){

                ReaderDAO readerDAO = new ReaderDAO();
                readerDAO.removeSeriesFromFavourites(series, UserLogin.getInstance().getReader());

                UserLogin.getInstance().getReader().removeSeriesFromFavourites(series);
            }
        }

    }
}
