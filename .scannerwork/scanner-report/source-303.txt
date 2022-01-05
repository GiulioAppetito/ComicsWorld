package com.example.comics.controller;

import com.example.comics.model.fagioli.ObjectiveBean;
import com.example.comics.model.fagioli.ReviewBean;
import com.example.comics.model.*;
import com.example.comics.model.dao.SeriesDAO;

public class PostReviewController extends ReviewSubject {

    public void post(ReviewBean reviewBean) {

        String reviewer = reviewBean.getUsername();
        String comment = reviewBean.getComment();
        Review review = new Review(comment,reviewer);
        review.setSeries(reviewBean.getSeries());
        review.setChapter(reviewBean.getChapter());

        //salvataggio sul DB
        saveReview(review);

        //aggiorna model
        UserLogin.getInstance().getReader().addPublishedReview(review);

        //controllare obiettivi
        SeriesDAO seriesDAO = new SeriesDAO();
        Series series = seriesDAO.retrieveSeries(reviewBean.getSeries());
        checkObjectives(series);

        notifyObservers(reviewBean);
    }

    private void checkObjectives(Series series) {
        //numero di review del lettore
        int numOfReviews = 0;
        for(Review review : UserLogin.getInstance().getReader().getPublishedReviews()) {
            if(review.getSeries().equals(series.getTitle())){
                numOfReviews++;
            }
        }
        //controllo degli obiettivi
        for(Objective objective : series.getObjectives()){

            if(objective.getType().equals("reviewsObjective")){
                if(objective.achieveObjective(numOfReviews)){
                    //controllo che il lettore non abbia già raggiunto questo obiettivo confrontando i badge
                    if(!UserLogin.getInstance().getReader().hasAchievedThisBadge(objective.getBadge())){

                        //aggiungo badge alla lista e salvo sul DB + assegno badge
                        UserLogin.getInstance().getReader().addAchievedBadge(objective.getBadge());

                        ObjectiveBean objectiveBean = new ObjectiveBean();
                        objectiveBean.setBadge(objective.getBadge());
                        objectiveBean.setLevel(objective.getLevel());
                        objectiveBean.setSeriesTitle((objective.getSeriesTitle()));

                        //genero discount code
                        DiscountCode discountCode = new DiscountCode(objective.getDiscount());
                        UserLogin.getInstance().getReader().addDiscountCode(discountCode);

                        //notifica di badge
                        notifyAchievedReviewObjective(objectiveBean);

                        //discount code : arriva la mail
                    }
                }
            }

        }
        //azione a seguito del controllo

    }

    private void saveReview(Review review) {
        SeriesDAO seriesDAO = new SeriesDAO();
        seriesDAO.addReviewToChapter(review);
    }

}
