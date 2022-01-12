package com.example.comics.controller;

import com.example.comics.controller.externalBoundaries.BoundarySendEmail;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.ReviewBean;
import com.example.comics.model.*;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.SeriesBean;

public class PostReviewController{

    public void post(ReviewBean reviewBean, ChapterBean chapterBean, SeriesBean seriesBean) {

        //salvataggio sul DB
        SeriesDAO seriesDAO = new SeriesDAO();
        Series series = seriesDAO.retrieveSeries(seriesBean.getTitle());
        series.addReview(chapterBean.getTitle(), reviewBean.getComment(), reviewBean.getRating());

        //controllare obiettivi
        checkObjectives(series);

    }

    private void checkObjectives(Series series) {
        //numero di review del lettore
        int numOfReviews = series.getNumberOfReviews(UserLogin.getInstance().getReader());

        //controllo degli obiettivi
        for(Objective objective : series.getObjectives()){

            if(objective.getType().equals("reviewsObjective")){
                if(objective.achieveObjective(numOfReviews)){
                    //controllo che il lettore non abbia già raggiunto questo obiettivo confrontando i badge
                    if(!UserLogin.getInstance().getReader().hasAchievedThisBadge(objective.getBadge())){

                        //aggiungo badge alla lista e salvo sul DB + assegno badge
                        UserLogin.getInstance().getReader().addAchievedBadge(objective.getBadge());

                        //genero discount code
                        DiscountCode discountCode = new DiscountCode(objective.getDiscount());
                        UserLogin.getInstance().getReader().addDiscountCode(discountCode);

                        //discount code : arriva la mail
                        //genero discount code

                        //invio mail
                        BoundarySendEmail boundarySendEmail = new BoundarySendEmail();
                        boundarySendEmail.sendEmail(UserLogin.getInstance().getAccount(), discountCode);

                    }
                }
            }

        }
        //azione a seguito del controllo

    }

}
