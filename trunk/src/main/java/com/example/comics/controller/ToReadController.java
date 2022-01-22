package com.example.comics.controller;

import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.fagioli.SeriesBean;

import java.sql.SQLException;

public class ToReadController {

    public boolean isSeriesAddedToRead(SeriesBean seriesBean){

        for(Series series : seriesBean.getAuthor().getPublishedSeries()){
            if(series.getTitle().equals(seriesBean.getTitle())){
                return UserLogin.getInstance().getReader().wantsToRead(series);
            }
        }

        return false;

    }

    public void addSeriesToToRead(SeriesBean seriesBean) {

        for(Series series : seriesBean.getAuthor().getPublishedSeries()){
            if(series.getTitle().equals(seriesBean.getTitle())){
                UserLogin.getInstance().getReader().addSeriesToToRead(series);

                ReaderDAO readerDAO = new ReaderDAO();
                try {
                    readerDAO.addSeriesToToRead(series,UserLogin.getInstance().getReader());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeSeriesFromToRead(SeriesBean seriesBean) {

        for(Series series : seriesBean.getAuthor().getPublishedSeries()){
            if(series.getTitle().equals(seriesBean.getTitle())){
                UserLogin.getInstance().getReader().removeSeriesFromToRead(series);

                ReaderDAO readerDAO = new ReaderDAO();
                readerDAO.removeSeriesFromToRead(series,UserLogin.getInstance().getReader());
            }
        }


    }
}







