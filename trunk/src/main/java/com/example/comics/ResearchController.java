package com.example.comics;

import com.example.comics.fagioli.ChapterBean;
import com.example.comics.fagioli.SeriesBean;
import com.example.comics.model.Chapter;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.SeriesDAO;

import java.util.ArrayList;

public class ResearchController {

    public ArrayList<SeriesBean> getFavouriteSeries(){

        ArrayList<SeriesBean> seriesBeans = new ArrayList<>();
        SeriesBean seriesBean;

        SeriesDAO seriesdao = new SeriesDAO();

        ArrayList<Series> favouriteSeries = UserLogin.getInstance().getReader().getFavourites();

        for(Series series : favouriteSeries){
            seriesBean = new SeriesBean();
            seriesBean.setTitle(series.getTitle());
            seriesBean.setAuthor(series.getAuthor());
            seriesBean.setChapters(series.getChapters());

            seriesBeans.add(seriesBean);
            //seriesBean.setCover();
        }


        return seriesBeans;
    }

    public ArrayList<SeriesBean> getLatestSeries(){

        ArrayList<SeriesBean> seriesBeans = new ArrayList<>();
        SeriesBean seriesBean;

        SeriesDAO seriesdao = new SeriesDAO();

        ArrayList<Series> latestSeries = seriesdao.retriveLatestSeries();
        //return Series.getSeries();
        for(Series series : latestSeries){
            seriesBean = new SeriesBean();
            seriesBean.setTitle(series.getTitle());
            seriesBean.setAuthor(series.getAuthor());
            seriesBean.setChapters(series.getChapters());
            seriesBeans.add(seriesBean);
            //seriesBean.setCover();
        }


        return seriesBeans;
    }


}
