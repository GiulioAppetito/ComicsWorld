package com.example.comics.controller;

import com.example.comics.controller.boundaries.PostReviewAuthorBoundary;
import com.example.comics.controller.boundaries.PostReviewReaderBoundary;
import com.example.comics.model.fagioli.*;
import com.example.comics.model.*;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.bundle.AccountBundle;
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
        new Thread(()->{
            PostReviewAuthorBoundary postReviewAuthorBoundary = new PostReviewAuthorBoundary();
            postReviewAuthorBoundary.sendEmailForNewReviewPosted(seriesBean);
        }).start();

        //controllo obiettivi
        checkObjectives(series,seriesBean);
    }

    private void checkObjectives(Series series, SeriesBean seriesBean) {


        //numero di review del lettore
        int numOfReviews = series.getNumberOfReviews(UserLogin.getInstance().getReader());

        //controllo degli obiettivi
        for(Objective objective : series.getObjectives()){
            if((objective.getType().equals("reviewsObjective"))&&(!UserLogin.getInstance().getReader().hasAchievedThisBadge(objective.getBadge()))&&(objective.achieveObjective(numOfReviews, objective.getBadge()))){

                //aggiungo badge alla lista e salvo sul DB + assegno badge
                new Thread(()-> UserLogin.getInstance().getReader().addAchievedBadge(objective.getBadge())).start();

                //genero discount code
                DiscountCode discountCode = new DiscountCode(objective.getDiscount());
                UserLogin.getInstance().getReader().addDiscountCode(discountCode);



                //invio mail al lettore del codice sconto
                new Thread(()->{
                    AccountBean accountBean = new AccountBundle();
                    accountBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
                    accountBean.setLastName(UserLogin.getInstance().getAccount().getLastName());
                    accountBean.setUsername(UserLogin.getInstance().getAccount().getUsername());
                    accountBean.setEmail(UserLogin.getInstance().getAccount().getEmail());
                    accountBean.setProPic(UserLogin.getInstance().getAccount().getProPic());

                    DiscountCodeBean discountCodeBean = new DiscountCodeBundle();
                    discountCodeBean.setCode(discountCode.getCode());
                    discountCodeBean.setExpiringDate(discountCode.getExpiringDate());
                    discountCodeBean.setPercentage(discountCode.getDiscount().getPercentage());
                    discountCodeBean.setLimitDays(discountCode.getDiscount().getLimitDays());

                    PostReviewReaderBoundary postReviewReaderBoundary = new PostReviewReaderBoundary();
                    postReviewReaderBoundary.sendEmailForDiscountCode(accountBean, seriesBean, discountCodeBean);
                }).start();
            }
        }
    }

}
