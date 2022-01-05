package com.example.comics.controller;

import com.example.comics.model.Badge;
import com.example.comics.model.Objective;
import com.example.comics.model.fagioli.BadgeBean;
import com.example.comics.model.fagioli.SeriesBean;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.SeriesDAO;

import java.util.ArrayList;
import java.util.List;

public class ResearchController {

    public List<SeriesBean> getFavouriteSeries(){

        List<SeriesBean> seriesBeans = new ArrayList<>();
        SeriesBean seriesBean;

        List<Series> favouriteSeries = UserLogin.getInstance().getReader().getFavourites();

        for(Series series : favouriteSeries){
            seriesBean = new SeriesBean();
            seriesBean.setTitle(series.getTitle());
            seriesBean.setAuthor(series.getAuthor());
            seriesBean.setChapters(series.getChapters());

            seriesBeans.add(seriesBean);
            seriesBean.setCover(series.getCover());
        }


        return seriesBeans;
    }

    public List<SeriesBean> getToReadSeries(){
        List<SeriesBean> seriesBeans = new ArrayList<>();
        SeriesBean seriesBean;

        List<Series> toReadSeries = UserLogin.getInstance().getReader().getToRead();

        for(Series series : toReadSeries){
            seriesBean = new SeriesBean();
            seriesBean.setTitle(series.getTitle());
            seriesBean.setAuthor(series.getAuthor());
            seriesBean.setChapters(series.getChapters());
            seriesBeans.add(seriesBean);
            seriesBean.setCover(series.getCover());
        }


        return seriesBeans;

    }

    public List<SeriesBean> getLatestSeries(){

        ArrayList<SeriesBean> seriesBeans = new ArrayList<>();
        SeriesBean seriesBean;

        SeriesDAO seriesdao = new SeriesDAO();

        List<Series> latestSeries = seriesdao.retriveLatestSeries();

        for(Series series : latestSeries){
            seriesBean = new SeriesBean();
            seriesBean.setTitle(series.getTitle());
            seriesBean.setAuthor(series.getAuthor());
            seriesBean.setChapters(series.getChapters());
            System.out.println("ARRIVATO DOPO GET CHAPTERS");
            seriesBean.setCover(series.getCover());
            seriesBeans.add(seriesBean);
        }


        return seriesBeans;
    }


    public List<BadgeBean> getUserBadges() {

        ArrayList<BadgeBean> badgeBeans = new ArrayList<>();
        BadgeBean badgeBean;

        List<Badge> badges = UserLogin.getInstance().getReader().getBadges();

        for(Badge badge : badges){
            badgeBean = new BadgeBean();
            badgeBean.setIcon(badge.getIcon());
            badgeBean.setName(badge.getName());
            System.out.println("[RESEARCH CONTROLLER] BADGE: " + badge.getName());
            badgeBeans.add(badgeBean);
        }


        return badgeBeans;
    }
}
