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

        SeriesDAO seriesDAO = new SeriesDAO();
        Series series = seriesDAO.retrieveSeries(seriesBean.getTitle());
        series.markChapter(chapterBean.getTitle());

        UserLogin.getInstance().getReader().addSeriesToReading(series);

        Series finalSeries = series;
        new Thread(()-> {
            ReaderDAO readerDAO = new ReaderDAO();
            readerDAO.saveReadChapter(finalSeries, chapterBean.getTitle());
        }).start();
        
        checkObjectives(series);
    }

    private void checkObjectives(Series series) {
        System.out.println("[MARK] CheckObjectives("+series.getTitle()+")");
        Float readersReadings = 0f;
        Float achievement = 0f;

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
            System.out.println("[MARK] readerReadings : "+readersReadings);
            System.out.println("[MARK] seriesChapters size : "+series.getChapters().size());
            achievement =(readersReadings / series.getChapters().size());
            System.out.println("[MARK CH C] Achievement : "+achievement);

        }

        //controllo degli obiettivi
        for(Objective objective : series.getObjectives()){
            if((objective.getType().equals("chapters"))&&(!UserLogin.getInstance().getReader().hasAchievedThisBadge(objective.getBadge()))&&(objective.isObjectiveAchieved(achievement))){

                System.out.println("[MARK] Badge won....");
                //aggiungo badge alla lista e salvo sul DB + assegno badge
                UserLogin.getInstance().getReader().addAchievedBadge(objective.getBadge());

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

        for(Author author1 : UserLogin.getInstance().getReader().getFollowedAuthors()){
            if(author1 == null){
                break;
            }
            for(Series series1 : author1.getPublishedSeries()){
                if(series1.getTitle().equals(seriesBean.getTitle())){
                    series1.unmarkChapter(chapterBean.getTitle());
                    break;
                }
            }
        }

        SeriesDAO seriesDAO = new SeriesDAO();
        Series series = seriesDAO.retrieveSeries(seriesBean.getTitle());
        series.unmarkChapter(chapterBean.getTitle());

        Series finalSeries = series;
        new Thread(()-> {
            ReaderDAO readerDAO = new ReaderDAO();
            readerDAO.removeReadChapter(finalSeries, chapterBean.getTitle());
        }).start();

    }
}
