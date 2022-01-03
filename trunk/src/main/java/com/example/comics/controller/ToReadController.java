package com.example.comics.controller;

import com.example.comics.model.Reader;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.fagioli.SeriesBean;

import java.sql.SQLException;
import java.util.List;

public class ToReadController {
    public void addSeriesToToRead(SeriesBean seriesBean) {
        //controlla se già è aggiunta da leggere dall'utente
        if(!isSeriesAddedToRead(seriesBean)){
            //aggiungi alla lista da leggere
            Series series = new Series(seriesBean.getTitle());
            series.setAuthor(seriesBean.getAuthor());
            series.setCover(seriesBean.getCover());

            Reader reader = UserLogin.getInstance().getReader();
            reader.getToRead().add(series);
            ReaderDAO readerDAO = new ReaderDAO();
            try {
                readerDAO.addSeriesToToRead(series,reader);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("SERIES ALREADY ADDED TO toRead.");
        }

    }

    public boolean isSeriesAddedToRead(SeriesBean seriesBean){
        List<Series> addedSeries = UserLogin.getInstance().getReader().getToRead();
        for(Series item : addedSeries){
            if(item.getTitle().equals(seriesBean.getTitle())){
                return true;
            }
        }
        return false;
    }

    public void removeSeriesFromToRead(SeriesBean seriesBean) {
        Series seriesToRemove = new Series(seriesBean.getTitle());
        seriesToRemove.setAuthor(seriesBean.getAuthor());
        seriesToRemove.setCover(seriesBean.getCover());

        List<Series> toReadSeries = UserLogin.getInstance().getReader().getToRead();
        int i;
        for( i=0; i<toReadSeries.size();i++){
            if(toReadSeries.get(i).getTitle().equals(seriesToRemove.getTitle())){
                toReadSeries.remove(i);
            }
        }

        ReaderDAO readerDAO = new ReaderDAO();
        Reader reader = UserLogin.getInstance().getReader();
        try {
            ReaderDAO.removeSeriesFromToRead(seriesToRemove,reader);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}







