package com.example.comics.controller;

import com.example.comics.model.fagioli.ObjectiveBean;
import com.example.comics.model.fagioli.ReviewBean;
import com.example.comics.model.*;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.SeriesBean;

public class PostReviewController extends ReviewSubject {

    public void post(ReviewBean reviewBean, SeriesBean seriesBean) {

        String reviewer = reviewBean.getUsername();
        String comment = reviewBean.getComment();
        Review review = new Review(comment,reviewer);
        review.setChapter(reviewBean.getChapter());
        review.setRating(reviewBean.getRating());

        //salvataggio sul DB
        SeriesDAO seriesDAO = new SeriesDAO();
        Series series = seriesDAO.retrieveSeries(seriesBean.getTitle());
        saveReview(review, series);

        //aggiorna user
        UserLogin.getInstance().getReader().addPublishedReview(review);

        //controllare obiettivi
        checkObjectives(series);

        notifyObservers(reviewBean);
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

    private void saveReview(Review review, Series series) {

        //series.addReview(review, review.getChapter());
        SeriesDAO seriesDAO = new SeriesDAO();
        seriesDAO.addReviewToChapter(review, series.getTitle());
    }

}
