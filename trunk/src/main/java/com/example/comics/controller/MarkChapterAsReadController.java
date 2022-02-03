package com.example.comics.controller;

import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;

public class MarkChapterAsReadController {

    public void markChapterAsRead(SeriesBean seriesBean, ChapterBean chapterBean){

        Series series = null;
        SeriesDAO seriesDAO = new SeriesDAO();

        for(Series series1 : seriesBean.getAuthor().getPublishedSeries()){
            if(series1.getTitle().equals(seriesBean.getTitle())){
                series = series1;
            }
        }
        Series finalSeries = series;
        UserLogin.getInstance().getReader().markChapter(finalSeries,chapterBean.getTitle());

        Series finalSeries1 = series;

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.saveReadChapter(finalSeries1, chapterBean.getTitle());
        
        checkObjectives();
    }

    private void checkObjectives() {
    }

    public void unmarkChapterAsRead(SeriesBean seriesBean, ChapterBean chapterBean){
        Series series = null;
        SeriesDAO seriesDAO = new SeriesDAO();

        for(Series series1 : seriesBean.getAuthor().getPublishedSeries()){
            if(series1.getTitle().equals(seriesBean.getTitle())){
                series = series1;
            }
        }

        Series finalSeries = series;
        UserLogin.getInstance().getReader().unmarkChapter(finalSeries,chapterBean.getTitle());

        Series finalSeries1 = series;

        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.removeReadChapter(finalSeries1, chapterBean.getTitle());
    }
}
