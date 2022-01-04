package com.example.comics.controller;

import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.SeriesBean;

public class FavouritesController {

    public boolean isSeriesFavourite(SeriesBean seriesBean) {

        Series series = new Series(seriesBean.getTitle());
        series.setCover(seriesBean.getCover());
        series.setAuthor(series.getAuthor());

       if(UserLogin.getInstance().getReader().likesThisSeries(series)){
           return true;
       }
       else{
           return false;
       }

    }

    public void addSeriesToFavourites(SeriesBean seriesBean) {

        Series series = new Series(seriesBean.getTitle());
        series.setAuthor(seriesBean.getAuthor());
        series.setCover(seriesBean.getCover());
        //poi altri dettagli

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.addSeriesToFavourites(series, UserLogin.getInstance().getReader());

        UserLogin.getInstance().getReader().addSeriesToFavourites(series);

    }

    public void removeSeriesFromFavourites(SeriesBean seriesBean) {
        Series series = new Series(seriesBean.getTitle());
        series.setAuthor(seriesBean.getAuthor());
        series.setCover(seriesBean.getCover());
        //poi altri dettagli

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.removeSeriesFromFavourites(series, UserLogin.getInstance().getReader());

        UserLogin.getInstance().getReader().removeSeriesFromFavourites(series);
    }
}
