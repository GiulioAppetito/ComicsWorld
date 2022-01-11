package com.example.comics.controller;

import com.example.comics.model.*;
import com.example.comics.model.fagioli.*;
import com.example.comics.model.dao.SeriesDAO;
import java.util.ArrayList;
import java.util.List;

public class ResearchController {

    public List<SeriesBean> getFavouriteSeries() {
        List<Series> favouriteSeries = UserLogin.getInstance().getReader().getFavourites();
        return getSeriesBeans(favouriteSeries);
    }

    public List<SeriesBean> getToReadSeries() {
        List<Series> toReadSeries = UserLogin.getInstance().getReader().getToRead();
        return getSeriesBeans(toReadSeries);
    }

    public List<SeriesBean> getLatestSeries() {
        SeriesDAO seriesdao = new SeriesDAO();
        List<Series> latestSeries = seriesdao.retriveLatestSeries();
        return getSeriesBeans(latestSeries);
    }

    public List<SeriesBean> getPublishedSeries() {
        List<Series> publishedSeries = UserLogin.getInstance().getAuthor().getPublishedSeries();
        return getSeriesBeans(publishedSeries);
    }

    //boh

    private List<SeriesBean> getSeriesBeans(List<Series> series) {

        List<SeriesBean> seriesBeans = new ArrayList<>();
        SeriesBean seriesBean;
        for (Series serie : series){
            seriesBean = new SeriesBean();
            seriesBean.setTitle(serie.getTitle());
            seriesBean.setAuthor(serie.getAuthor());
            seriesBean.setAverageRating(serie.getAverageRating());
            seriesBean.setChapters(serie.getChapters());
            seriesBean.setCover(serie.getCover());
            seriesBeans.add(seriesBean);
        }
        return seriesBeans;

    }

}
