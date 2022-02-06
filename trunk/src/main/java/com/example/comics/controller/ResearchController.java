package com.example.comics.controller;

import com.example.comics.model.*;
import com.example.comics.model.fagioli.*;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.bundle.*;

import java.util.ArrayList;
import java.util.List;

public class ResearchController {

    public List<SeriesBean> getFavouriteSeries() {
        List<Series> favouriteSeries = UserLogin.getInstance().getReader().getFavourites();
        System.out.println("[RESEARCH CONTROLLER] reading list lenght : "+UserLogin.getInstance().getReader().getReading().size());
        return getSeriesBeans(favouriteSeries);
    }

    public List<SeriesBean> getToReadSeries() {
        List<Series> toReadSeries = UserLogin.getInstance().getReader().getToRead();
        return getSeriesBeans(toReadSeries);
    }

    public List<SeriesBean> getReadingSeries() {
        List<Series> toReadSeries = UserLogin.getInstance().getReader().getReading();
        return getSeriesBeans(toReadSeries);
    }

    public List<SeriesBean> getLatestSeries() {
        SeriesDAO seriesdao = new SeriesDAO();
        List<Series> latestSeries = seriesdao.retriveLatestSeries();
        return getSeriesBeans(latestSeries);
    }

    public List<SeriesBean> getPublishedSeries(){
        List<Series> publishedSeries = UserLogin.getInstance().getAuthor().getPublishedSeries();
        return getSeriesBeans(publishedSeries);
    }

    public List<SeriesBean> getPublishedSeries(AuthorBean authorBean) {
        List<Series> publishedSeries = authorBean.getPublishedSeries();
        return getSeriesBeans(publishedSeries);
    }

    public List<SeriesBean> getCategorySeries(Genres genres){
        SeriesDAO seriesDAO = new SeriesDAO();
        List<Series> series = seriesDAO.retrieveSeriesFromCategory(genres);
        return getSeriesBeans(series);
    }

    private List<SeriesBean> getSeriesBeans(List<Series> seriesList) {

        List<SeriesBean> seriesBeans = new ArrayList<>();

        SeriesBundle seriesBundle;
        for(Series series : seriesList){
            seriesBundle = new SeriesBundle();

            AuthorBundle authorBundle = new AuthorBundle();
            authorBundle.setFirstName(series.getAuthor().getFirstName());
            authorBundle.setLastName(series.getAuthor().getLastName());
            authorBundle.setPublishedSeries(series.getAuthor().getPublishedSeries());
            authorBundle.setUsername(series.getAuthor().getUsername());
            authorBundle.setProPic(series.getAuthor().getProPic());
            authorBundle.setEmail(series.getAuthor().getEmail());
            seriesBundle.setAuthor(authorBundle);

            seriesBundle.setCover(series.getCover());
            seriesBundle.setTitle(series.getTitle());
            seriesBundle.setDescription(series.getDescription());

            List<ChapterBean> chapterBeans = new ArrayList<>();
            ChapterBundle chapterBundle;
            for(Chapter chapter : series.getChapters()){
                chapterBundle = new ChapterBundle();
                chapterBundle.setTitle(chapter.getTitle());
                chapterBundle.setAverageRating(chapter.getAverageRating());
                chapterBundle.setDescription(chapter.getDescription());
                chapterBundle.setReviews(chapter.getReviews());
                chapterBundle.setPrice(chapter.getPrice());
                chapterBundle.setCover(chapter.getCover());
                chapterBundle.setRead(chapter.getRead());
                chapterBeans.add(chapterBundle);
            }
            seriesBundle.setChapters(chapterBeans);
            seriesBundle.setAverageRating(series.getAverageRating());
            seriesBeans.add(seriesBundle);
        }

        return seriesBeans;
    }

    public List<BadgeBean> getUserBadges() {

        ArrayList<BadgeBean> badgeBeans = new ArrayList<>();
        BadgeBundle badgeBundle;

        List<Badge> badges = UserLogin.getInstance().getReader().getBadges();

        for (Badge badge : badges) {
            badgeBundle = new BadgeBundle();
            badgeBundle.setIcon(badge.getIcon());
            badgeBundle.setName(badge.getName());
            badgeBeans.add(badgeBundle);
        }


        return badgeBeans;
    }

    public List<AuthorBean> getFollowedAuthors() {

        List<AuthorBean> authorBeans = new ArrayList<>();
        AuthorBundle authorBundle;

        List<Author> followedAuthors = UserLogin.getInstance().getReader().getFollowedAuthors();
        for(int i=0; i<followedAuthors.size();i++){
            authorBundle = new AuthorBundle();
            authorBundle.setUsername(followedAuthors.get(i).getUsername());
            authorBundle.setFirstName(followedAuthors.get(i).getFirstName());
            authorBundle.setLastName(followedAuthors.get(i).getLastName());
            authorBundle.setEmail(followedAuthors.get(i).getEmail());
            authorBundle.setProPic(followedAuthors.get(i).getProPic());
            authorBundle.setPublishedSeries(followedAuthors.get(i).getPublishedSeries());
            authorBeans.add(authorBundle);
        }

        return authorBeans;
    }

    public BadgeBean getLatestBadge(){

        int size = UserLogin.getInstance().getReader().getBadges().size();
        Badge badge = UserLogin.getInstance().getReader().getBadges().get(size);
        if(badge == null){
            return null;
        }
        BadgeBundle badgeBundle = new BadgeBundle();
        badgeBundle.setIcon(badge.getIcon());
        badgeBundle.setName(badge.getName());
        return badgeBundle;
    }

}