package com.example.comics.controller;

import com.example.comics.model.Author;
import com.example.comics.model.Badge;
import com.example.comics.model.dao.AuthorDAO;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.AuthorBean;
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
            badgeBeans.add(badgeBean);
        }


        return badgeBeans;
    }

    public AuthorBean getAuthor(AccountBean accountBean) {
        AuthorBean authorBean = new AuthorBean();

        AuthorDAO authorDAO = new AuthorDAO();
        Author author = authorDAO.retrieveAuthorWithoutPassword(accountBean.getUsername());

        authorBean.setFirstName(author.getFirstName());
        authorBean.setLastName(author.getLastName());
        authorBean.setUsername(author.getUsername());

        return authorBean;

    }

    public List<AuthorBean> getFollowedAuthors() {
        List<AuthorBean> authorBeans = new ArrayList<>();
        AuthorBean authorBean;
        System.out.println("[RESEARCH] Prima del for.");
        for(Author author : UserLogin.getInstance().getReader().getFollowedAuthors()){
            System.out.println("RESEARCH CONTR] Primo author : "+author.getUsername());
            authorBean = new AuthorBean();
            authorBean.setUsername(author.getUsername());
            authorBean.setProPic(author.getProPic());
            authorBean.setFirstName(author.getFirstName());
            authorBean.setLastName(author.getLastName());
            authorBeans.add(authorBean);
        }
        return authorBeans;
    }
}
