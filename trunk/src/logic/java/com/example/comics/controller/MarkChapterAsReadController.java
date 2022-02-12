package com.example.comics.controller;

import com.example.comics.controller.boundaries.MarkChapterAsReadReaderBoundary;
import com.example.comics.controller.boundaries.PostReviewReaderBoundary;
import com.example.comics.model.*;
import com.example.comics.model.dao.DiscountCodeDAO;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.*;
import com.example.comics.model.fagioli.bundle.AccountBundle;
import com.example.comics.model.fagioli.bundle.DiscountBundle;
import com.example.comics.model.fagioli.bundle.DiscountCodeBundle;
import com.example.comics.model.fagioli.bundle.SeriesBundle;

public class MarkChapterAsReadController {

    public void markChapterAsRead(SeriesBean seriesBean, ChapterBean chapterBean){

        Series series = SeriesDAO.retrieveSeries(seriesBean.getTitle());
        assert series != null;
        series.markChapter(chapterBean.getTitle());

        UserLogin.getInstance().getReader().addSeriesToReading(series);

        new Thread(()-> {
            ReaderDAO readerDAO = new ReaderDAO();
            readerDAO.saveReadChapter(series, chapterBean.getTitle());
        }).start();
        
        checkObjectives(series);
    }

    private void checkObjectives(Series series) {
        float readersReadings = 0f;
        float achievement;

        readersReadings = series.countReadersReadChapters();
        achievement =(readersReadings / series.getChapters().size());

        //controllo degli obiettivi
        for(Objective objective : series.getObjectives()){
            if((objective.getType().equals("chapters"))&&(!UserLogin.getInstance().getReader().hasAchievedThisBadge(objective.getBadge()))&&(objective.isObjectiveAchieved(achievement))){

                //aggiungo badge alla lista e salvo sul DB + assegno badge
                UserLogin.getInstance().getReader().addAchievedBadge(objective.getBadge());

                //genero discount code
                DiscountCode discountCode = new DiscountCode(objective.getDiscount());
                UserLogin.getInstance().getReader().assignDiscountCode(discountCode,series);
                DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
                discountCodeDAO.saveObtainedDiscountCode(discountCode,UserLogin.getInstance().getReader(), series,objective);

                DiscountBean discountBean = new DiscountBundle();
                discountBean.setLimitDays(discountCode.getDiscount().getLimitDays());
                discountBean.setPercentage(discountCode.getDiscount().getPercentage());

                DiscountCodeBean discountCodeBean = new DiscountCodeBundle();
                discountCodeBean.setCode(discountCode.getCode());
                discountCodeBean.setDiscountBean(discountBean);

                SeriesBean seriesBean = new SeriesBundle();
                seriesBean.setTitle(series.getTitle());

                AccountBean accountBean = new AccountBundle();
                accountBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
                accountBean.setLastName(UserLogin.getInstance().getAccount().getLastName());
                accountBean.setUsername(UserLogin.getInstance().getAccount().getUsername());
                accountBean.setEmail(UserLogin.getInstance().getAccount().getEmail());

                //invio mail al lettore del codice sconto
                new Thread(()->{
                    MarkChapterAsReadReaderBoundary markChapterAsReadReaderBoundary = new MarkChapterAsReadReaderBoundary();
                    markChapterAsReadReaderBoundary.sendEmailForDiscountCode(accountBean, seriesBean, discountCodeBean);
                }).start();
            }
        }
    }

    public void unmarkChapterAsRead(SeriesBean seriesBean, ChapterBean chapterBean){


        Series series = SeriesDAO.retrieveSeries(seriesBean.getTitle());
        series.unmarkChapter(chapterBean.getTitle());

        Series finalSeries = series;
        new Thread(()-> {
            ReaderDAO readerDAO = new ReaderDAO();
            readerDAO.removeReadChapter(finalSeries, chapterBean.getTitle());
        }).start();

    }
}
