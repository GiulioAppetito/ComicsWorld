package com.example.comics.controller;

import com.example.comics.model.Reader;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.SeriesBean;

import java.sql.SQLException;
import java.util.List;

public class ToReadController {

    public boolean isSeriesAddedToRead(SeriesBean seriesBean){

        Series series = new Series(seriesBean.getTitle());
        series.setCover(seriesBean.getCover());
        series.setAuthor(series.getAuthor());

        if(UserLogin.getInstance().getReader().wantsToRead(series)){
            return true;
        }
        else{
            return false;
        }

    }

    public void addSeriesToToRead(SeriesBean seriesBean) {
        Series series = new Series(seriesBean.getTitle());
        series.setAuthor(seriesBean.getAuthor());
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
        series.setAuthor(seriesBean.getAuthor());
        series.setCover(seriesBean.getCover());
        //poi altri dettagli

        UserLogin.getInstance().getReader().removeSeriesFromToRead(series);

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.removeSeriesFromToRead(series,UserLogin.getInstance().getReader());

    }
}







