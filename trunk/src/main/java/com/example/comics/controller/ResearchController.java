package com.example.comics.controller;

import com.example.comics.model.*;
import com.example.comics.model.dao.AuthorDAO;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.view1.bean1.AuthorBean1;
import com.example.comics.model.fagioli.BadgeBean;
import com.example.comics.view1.bean1.BadgeBean1;
import com.example.comics.model.fagioli.SeriesBean;
import com.example.comics.view1.bean1.SeriesBean1;

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

    private List<SeriesBean> getSeriesBeans(List<Series> series) {

        List<SeriesBean> seriesBeans = new ArrayList<>();
        SeriesBean seriesBean;
        for (Series serie : series){
            seriesBean = new SeriesBean1();
            seriesBean.setTitle(serie.getTitle());
            seriesBean.setAuthor(serie.getAuthor());
            seriesBean.setChapters(serie.getChapters());
            System.out.println("[RESEARCH CONTR] Primo capitolo : "+seriesBean.getChapters().get(0).getTitle());
            seriesBean.setCover(serie.getCover());
            seriesBeans.add(seriesBean);
        }


        return seriesBeans;
    }


    //boh
    public List<BadgeBean> getUserBadges() {

        ArrayList<BadgeBean> badgeBeans = new ArrayList<>();
        BadgeBean badgeBean;

        List<Badge> badges = UserLogin.getInstance().getReader().getBadges();

        for (Badge badge : badges) {
            badgeBean = new BadgeBean1();
            badgeBean.setIcon(badge.getIcon());
            badgeBean.setName(badge.getName());
            badgeBeans.add(badgeBean);
        }


        return badgeBeans;
    }


    public List<AuthorBean> getFollowedAuthors(Reader reader) {
        List<AuthorBean> authorBean1s = new ArrayList<>();
        AuthorBean authorBean1;

        List<Author> followedAuthors = reader.getFollowedAuthors();
        for(int i=0; i<followedAuthors.size();i++){
            authorBean1 = new AuthorBean1();
            authorBean1.setUsername(followedAuthors.get(i).getUsername());
            authorBean1.setFirstName(followedAuthors.get(i).getFirstName());
            authorBean1.setLastName(followedAuthors.get(i).getLastName());
            authorBean1.setProPic(followedAuthors.get(i).getProPic());
            authorBean1.setPublishedSeries(followedAuthors.get(i).getPublishedSeries());
            authorBean1s.add(authorBean1);
        }

        return authorBean1s;
    }
}
