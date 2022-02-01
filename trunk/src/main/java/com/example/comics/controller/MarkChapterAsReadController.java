package com.example.comics.controller;

import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;

public class MarkChapterAsReadController {
    public void markChapterAsRead(SeriesBean seriesBean, ChapterBean chapterBean){

        Series series;
        SeriesDAO seriesDAO = new SeriesDAO();

        series = seriesDAO.retrieveSeries(seriesBean.getTitle());

        Thread t1 = new Thread(()->UserLogin.getInstance().getReader().markChapter(series,chapterBean.getTitle()));

        Thread t2 = new Thread(()-> {
            ReaderDAO readerDAO = new ReaderDAO();
            readerDAO.saveReadChapter(series, chapterBean.getTitle());
        });

        t1.start();
        t2.start();

    }
}
