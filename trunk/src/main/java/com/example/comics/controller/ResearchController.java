package com.example.comics.controller;

import com.example.comics.model.*;
import com.example.comics.model.fagioli.*;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.bundle.AuthorBundle;
import com.example.comics.model.fagioli.bundle.BadgeBundle;
import com.example.comics.model.fagioli.bundle.ChapterBundle;
import com.example.comics.model.fagioli.bundle.SeriesBundle;

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
        System.out.println("researchcontroller: serie latest" + latestSeries.get(0).getTitle());
        return getSeriesBeans(latestSeries);
    }

    public List<SeriesBean> getPublishedSeries() {
        List<Series> publishedSeries = UserLogin.getInstance().getAuthor().getPublishedSeries();
        return getSeriesBeans(publishedSeries);
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
            seriesBundle.setAuthor(authorBundle);

            seriesBundle.setCover(series.getCover());
            seriesBundle.setTitle(series.getTitle());

            List<ChapterBean> chapterBeans = new ArrayList<>();
            ChapterBundle chapterBundle;
            for(Chapter chapter : series.getChapters()){
                chapterBundle = new ChapterBundle();
                chapterBundle.setTitle(chapter.getTitle());
                chapterBundle.setAverageRating(chapter.getAverageRating());
                chapterBundle.setDescription(chapter.getDescription());
                chapterBundle.setReviews(chapter.getReviews());
                chapterBundle.setCover(chapter.getCover());
                chapterBundle.setRead(chapter.getRead());
                chapterBeans.add(chapterBundle);
            }
            seriesBundle.setChapters(chapterBeans);
            System.out.println("ResCon : rating series: " + series.getTitle() + " " + series.getAverageRating());
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
            authorBundle.setProPic(followedAuthors.get(i).getProPic());
            authorBundle.setPublishedSeries(followedAuthors.get(i).getPublishedSeries());
            authorBeans.add(authorBundle);
        }

        return authorBeans;
    }
}