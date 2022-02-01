package com.example.comics.controller;

import com.example.comics.controller.boundaries.PostReviewBoundary;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.ReviewBean;
import com.example.comics.model.*;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.SeriesBean;

public class PostReviewController{

    public void post(ReviewBean reviewBean, ChapterBean chapterBean, SeriesBean seriesBean) {

        //salvataggio sul DB
        Author author = new Author();
        author.setFirstName(seriesBean.getAuthor().getFirstName());
        author.setUsername(seriesBean.getAuthor().getUsername());
        author.setProPic(seriesBean.getAuthor().getProPic());
        author.setLastName(seriesBean.getAuthor().getLastName());
        author.setEmail(seriesBean.getAuthor().getEmail());
        author.setPublishedSeries(seriesBean.getAuthor().getPublishedSeries());

        SeriesDAO seriesDAO = new SeriesDAO();
        Series series = seriesDAO.retreiveSeriesWithAuthor(seriesBean.getTitle(),author);
        series.addReview(chapterBean.getTitle(), reviewBean.getComment(), reviewBean.getRating());

        //invio mail all'autore di una nuova review
        new Thread(()->{
            PostReviewBoundary postReviewAuthorBoundary = new PostReviewBoundary();
            postReviewAuthorBoundary.sendEmailForNewReviewPosted(series.getAuthor());
        }).start();

        //controllo obiettivi
        checkObjectives(series);
    }

    private void checkObjectives(Series series) {


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
                    PostReviewBoundary postReviewAuthorBoundary = new PostReviewBoundary();
                    postReviewAuthorBoundary.sendEmailForDiscountCode(UserLogin.getInstance().getReader(), series, discountCode);
                }).start();
            }
        }
    }

}
