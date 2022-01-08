package com.example.comics.controller;

import com.example.comics.model.*;
import com.example.comics.model.dao.AuthorDAO;
import com.example.comics.model.dao.ReaderDAO;
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

    private List<SeriesBean> getSeriesBeans(List<Series> series) {

        List<SeriesBean> seriesBeans = new ArrayList<>();
        SeriesBean seriesBean;
        for (Series serie : series){
            seriesBean = new SeriesBean();
            seriesBean.setTitle(serie.getTitle());
            seriesBean.setAuthor(serie.getAuthor());
            seriesBean.setChapters(serie.getChapters());
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
        authorBean.setUsername(author.getUsername());
        authorBean.setFirstName(author.getFirstName());
        authorBean.setLastName(author.getLastName());
        authorBean.setProPic(author.getProPic());

        return authorBean;

    }

    public List<AuthorBean> getFollowedAuthors(Reader reader) {
        List<AuthorBean> authorBeans = new ArrayList<>();
        AuthorBean authorBean;

        List<Author> followedAuthors = reader.getFollowedAuthors();
        for(int i=0; i<followedAuthors.size();i++){
            authorBean = new AuthorBean();
            authorBean.setUsername(followedAuthors.get(i).getUsername());
            authorBean.setFirstName(followedAuthors.get(i).getFirstName());
            authorBean.setLastName(followedAuthors.get(i).getLastName());
            authorBean.setProPic(followedAuthors.get(i).getProPic());
            authorBean.setPublishedSeries(followedAuthors.get(i).getPublishedSeries());
            authorBeans.add(authorBean);
        }

        return authorBeans;
    }
}
