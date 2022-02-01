package com.example.comics.controller;

import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;

public class MarkChapterAsReadController {
    public void markChapterAsRead(SeriesBean seriesBean, ChapterBean chapterBean){

        Series series = null;
        for(Series series1 : seriesBean.getAuthor().getPublishedSeries()){
            if(series1.getTitle().equals(seriesBean.getTitle())){
                series = series1;
            }
        }

        Series finalSeries = series;
        Thread t1 = new Thread(()->UserLogin.getInstance().getReader().markChapter(finalSeries,chapterBean.getTitle()));
        t1.start();

        Series finalSeries1 = series;
        Thread t2 = new Thread(()-> {
            ReaderDAO readerDAO = new ReaderDAO();
            readerDAO.saveReadChapter(finalSeries1, chapterBean.getTitle());
        });
        t2.start();

    }
}
