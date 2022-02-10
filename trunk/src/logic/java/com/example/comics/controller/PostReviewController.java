package com.example.comics.controller;

import com.example.comics.controller.boundaries.PostReviewAuthorBoundary;
import com.example.comics.controller.boundaries.PostReviewReaderBoundary;
import com.example.comics.model.dao.DiscountCodeDAO;
import com.example.comics.model.exceptions.IncompleteReviewException;
import com.example.comics.model.fagioli.*;
import com.example.comics.model.*;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.bundle.AccountBundle;
import com.example.comics.model.fagioli.bundle.DiscountBundle;
import com.example.comics.model.fagioli.bundle.DiscountCodeBundle;

public class PostReviewController{

    public void post(ReviewBean reviewBean, ChapterBean chapterBean, SeriesBean seriesBean) throws IncompleteReviewException {
        //verifica completezza della review
        if(reviewBean.getComment().equals("") || reviewBean.getComment() == null){
            throw new IncompleteReviewException("Fill every field to post!");
        }

        Series series;
        series = SeriesDAO.retrieveSeries(seriesBean.getTitle());
        series.addReview(chapterBean.getTitle(),reviewBean.getComment(),reviewBean.getRating(),reviewBean.getAccount());

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
        float numOfReviews = 0f;
        for(Chapter chapter : series.getChapters()){
            for(Review review : chapter.getReviews()){
                if(review.getAccount().getUsername().equals(UserLogin.getInstance().getReader().getUsername())){
                    numOfReviews+=1;
                }
            }
        }

        //controllo degli obiettivi di tipo review
        for(Objective objective : series.getObjectives()){

            isReviewsType = objective.getType().equals("reviews");
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

        //genero discount code
        DiscountCode discountCode = new DiscountCode(objective.getDiscount());
        UserLogin.getInstance().getReader().assignDiscountCode(discountCode,series);
        DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
        discountCodeDAO.saveObtainedDiscountCode(discountCode,UserLogin.getInstance().getReader(), series,objective);


        //aggiungo badge alla lista e salvo sul DB + assegno badge
        UserLogin.getInstance().getReader().addAchievedBadge(objective.getBadge());


        //invio mail al lettore del codice sconto
        new Thread(()-> sendEmailToReader(discountCode,seriesBean)).start();
    }

    private void sendEmailToReader(DiscountCode discountCode,SeriesBean seriesBean){

        AccountBean accountBean = new AccountBundle();
        accountBean.setLastName(UserLogin.getInstance().getAccount().getLastName());
        accountBean.setUsername(UserLogin.getInstance().getAccount().getUsername());
        accountBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
        accountBean.setEmail(UserLogin.getInstance().getAccount().getEmail());

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
