package com.example.comics.controller;

import com.example.comics.controller.boundaries.PostReviewReaderBoundary;
import com.example.comics.model.*;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.*;
import com.example.comics.model.fagioli.bundle.AccountBundle;
import com.example.comics.model.fagioli.bundle.DiscountBundle;
import com.example.comics.model.fagioli.bundle.DiscountCodeBundle;
import com.example.comics.model.fagioli.bundle.SeriesBundle;

public class MarkChapterAsReadController {

    public void markChapterAsRead(SeriesBean seriesBean, ChapterBean chapterBean){

        Series series = null;
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
        
        checkObjectives(series);
    }

    private void checkObjectives(Series series) {
        //numero di capitoli letti del lettore
        int numOfChapters = series.getChapters().size();
        int readersReadings = 0;

        for(Series readersSeries : UserLogin.getInstance().getReader().getReading()){
            if(readersSeries == null){
                return;
            }
            if(readersSeries.getTitle().equals(series.getTitle())){
                for(Chapter chapter : readersSeries.getChapters()){
                    if(chapter.getRead()){
                        readersReadings++;
                    }
                }
            }
        }

        //controllo degli obiettivi
        for(Objective objective : series.getObjectives()){
            if((objective.getType().equals("chapterObjective"))&&(!UserLogin.getInstance().getReader().hasAchievedThisBadge(objective.getBadge()))&&(objective.achieveObjective(readersReadings, objective.getBadge()))){

                //aggiungo badge alla lista e salvo sul DB + assegno badge
                new Thread(()-> UserLogin.getInstance().getReader().addAchievedBadge(objective.getBadge())).start();

                //genero discount code
                DiscountCode discountCode = new DiscountCode(objective.getDiscount());
                UserLogin.getInstance().getReader().assignDiscountCode(discountCode,series);

                //invio mail al lettore del codice sconto
                new Thread(()->{
                    AccountBean accountBean = new AccountBundle();
                    accountBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
                    accountBean.setLastName(UserLogin.getInstance().getAccount().getLastName());
                    accountBean.setUsername(UserLogin.getInstance().getAccount().getUsername());
                    accountBean.setEmail(UserLogin.getInstance().getAccount().getEmail());
                    accountBean.setProPic(UserLogin.getInstance().getAccount().getProPic());


                    DiscountBean discountBean = new DiscountBundle();
                    discountBean.setLimitDays(discountCode.getDiscount().getLimitDays());
                    discountBean.setPercentage(discountCode.getDiscount().getPercentage());

                    DiscountCodeBean discountCodeBean = new DiscountCodeBundle();
                    discountCodeBean.setCode(discountCode.getCode());
                    discountCodeBean.setDiscountBean(discountBean);

                    SeriesBean seriesBean = new SeriesBundle();
                    seriesBean.setTitle(series.getTitle());

                    PostReviewReaderBoundary postReviewReaderBoundary = new PostReviewReaderBoundary();
                    postReviewReaderBoundary.sendEmailForDiscountCode(accountBean, seriesBean, discountCodeBean);
                }).start();
            }
        }
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
