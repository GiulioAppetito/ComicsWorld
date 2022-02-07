package com.example.comics.controller;

import com.example.comics.controller.boundaries.PostReviewAuthorBoundary;
import com.example.comics.controller.boundaries.PostReviewReaderBoundary;
import com.example.comics.model.fagioli.*;
import com.example.comics.model.*;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.bundle.AccountBundle;
import com.example.comics.model.fagioli.bundle.DiscountBundle;
import com.example.comics.model.fagioli.bundle.DiscountCodeBundle;

public class PostReviewController{

    public void post(ReviewBean reviewBean, ChapterBean chapterBean, SeriesBean seriesBean) {

        //salvataggio sul DB
        Author author = new Author();
        author.setFirstName(seriesBean.getAuthor().getFirstName());
        author.setUsername(seriesBean.getAuthor().getUsername());
        author.setProPic(seriesBean.getAuthor().getProPic());
        author.setLastName(seriesBean.getAuthor().getLastName());
        author.setEmail(seriesBean.getAuthor().getEmail());


        Series series = null;

        for(Series favs : UserLogin.getInstance().getReader().getFavourites()){
            if(favs.getTitle().equals(seriesBean.getTitle())){
                favs.addReviewInSilence(chapterBean.getTitle(), reviewBean.getComment(), reviewBean.getRating());
                series = favs;
                break;
            }
        }

        for(Series toread : UserLogin.getInstance().getReader().getToRead()){
            if(toread.getTitle().equals(seriesBean.getTitle())){
                toread.addReviewInSilence(chapterBean.getTitle(), reviewBean.getComment(), reviewBean.getRating());
                series = toread;
                break;
            }
        }

        for(Series reading : UserLogin.getInstance().getReader().getReading()){
            if(reading.getTitle().equals(seriesBean.getTitle())){
                reading.addReviewInSilence(chapterBean.getTitle(), reviewBean.getComment(), reviewBean.getRating());
                series = reading;
                break;
            }
        }

        for(Author author1 : UserLogin.getInstance().getReader().getFollowedAuthors()){
            for(Series series1 : author1.getPublishedSeries()){
                if(series1.getTitle().equals(seriesBean.getTitle())){
                    series1.addReviewInSilence(chapterBean.getTitle(), reviewBean.getComment(), reviewBean.getRating());
                    series = series1;
                    break;
                }
            }
        }

        if(series == null){
            SeriesDAO seriesDAO = new SeriesDAO();
            series = seriesDAO.retrieveSeries(seriesBean.getTitle());
        }

        series.notifyNewReview(chapterBean.getTitle(), reviewBean.getComment(), reviewBean.getRating());


        //invio mail all'autore di una nuova review
        Thread emailThread;
        emailThread = new Thread(()->{
            PostReviewAuthorBoundary postReviewAuthorBoundary = new PostReviewAuthorBoundary();
            postReviewAuthorBoundary.sendEmailForNewReviewPosted(seriesBean);
        });
        emailThread.start();

        //controllo obiettivi
        checkObjectives(series,seriesBean);
    }

    private void checkObjectives(Series series, SeriesBean seriesBean) {
        boolean isReviewsType;
        boolean isBadgeAlreadyAchieved;
        boolean isNewObjectiveAchieved;

        //numero di review del lettore
        int numOfReviews = 0;
        for(Chapter chapter : series.getChapters()){
            for(Review review : chapter.getReviews()){
                if(review.getAccount().getUsername().equals(UserLogin.getInstance().getReader().getUsername())){
                    numOfReviews++;
                }
            }
        }

        //controllo degli obiettivi di tipo review
        for(Objective objective : series.getObjectives()){

            isReviewsType = objective.getType().equals("reviewsObjective");
            if(isReviewsType){
                isBadgeAlreadyAchieved = UserLogin.getInstance().getReader().hasAchievedThisBadge(objective.getBadge());
                isNewObjectiveAchieved = objective.isObjectiveAchieved(numOfReviews);
                if(!isBadgeAlreadyAchieved && isNewObjectiveAchieved){
                    assignBadgeAndDiscountCodeToReader(objective,series,seriesBean);
                }
            }
        }
    }

    private void assignBadgeAndDiscountCodeToReader(Objective objective,Series series,SeriesBean seriesBean) {

        //aggiungo badge alla lista e salvo sul DB + assegno badge
        new Thread(()-> UserLogin.getInstance().getReader().addAchievedBadge(objective.getBadge())).start();

        //genero discount code
        DiscountCode discountCode = new DiscountCode(objective.getDiscount());
        UserLogin.getInstance().getReader().assignDiscountCode(discountCode,series);

        //invio mail al lettore del codice sconto
        new Thread(()->{
            sendEmailToReader(discountCode,seriesBean);
        }).start();
    }

    private void sendEmailToReader(DiscountCode discountCode,SeriesBean seriesBean){
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

        PostReviewReaderBoundary postReviewReaderBoundary = new PostReviewReaderBoundary();
        postReviewReaderBoundary.sendEmailForDiscountCode(accountBean, seriesBean, discountCodeBean);
    }
}
