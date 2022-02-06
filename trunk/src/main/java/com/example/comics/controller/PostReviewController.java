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

        SeriesDAO seriesDAO = new SeriesDAO();
        Series series = seriesDAO.retreiveSeriesWithAuthor(seriesBean.getTitle(),author);
        series.addReview(chapterBean.getTitle(), reviewBean.getComment(), reviewBean.getRating());

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
        int numOfReviews = series.getNumberOfReviews(UserLogin.getInstance().getReader());

        //controllo degli obiettivi
        for(Objective objective : series.getObjectives()){

            isReviewsType = objective.getType().equals("reviewsObjective");
            isBadgeAlreadyAchieved = UserLogin.getInstance().getReader().hasAchievedThisBadge(objective.getBadge());
            isNewObjectiveAchieved = objective.achieveObjective(numOfReviews, objective.getBadge());

            if(isReviewsType && !isBadgeAlreadyAchieved && isNewObjectiveAchieved){
                assignBadgeAndDiscountCodeToReader(objective,series,seriesBean);
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
