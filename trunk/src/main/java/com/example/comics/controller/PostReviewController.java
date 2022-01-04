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
        System.out.println("[POST REVIEW CONTROLLER] User login ha review : "+UserLogin.getInstance().getReader().getPublishedReviews().get(0).getComment());

        //controllare obiettivi
        SeriesDAO seriesDAO = new SeriesDAO();
        Series series = seriesDAO.retrieveSeries(reviewBean.getSeries());
        checkObjectives(series);

        notifyObservers(reviewBean);
        //aggiunta sul chapter//chapter.addReview(reviewBean.getComment(), reviewBean.getUsername());//chapter.notify();
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
                    System.out.println("[POST REVIEW CONTROLLER] Obiettivo raggiunto!");
                    if(UserLogin.getInstance().getReader().hasAchievedThisBadge(objective.getBadge())){
                        System.out.println("[POST REVIEW CONTROLLER] Reader ha già questo obiettivo.");

                    }else {
                        //aggiungo badge alla lista e salvo sul DB + assegno badge
                        UserLogin.getInstance().getReader().addAchievedBadge(objective.getBadge());

                        ObjectiveBean objectiveBean = new ObjectiveBean();
                        objectiveBean.setBadge(objective.getBadge());
                        objectiveBean.setLevel(objective.getLevel());
                        objectiveBean.setSeriesTitle((objective.getSeries_title()));

                        //genero discount code
                        DiscountCode discountCode = new DiscountCode(objective.getDiscount());
                        UserLogin.getInstance().getReader().addDiscountCode(discountCode);

                        //notifica di badge
                        notifyAchievedReviewObjective(objectiveBean);

                        //discount code : arriva la mail
                    }
                }else{
                    System.out.println("Obiettivo non raggiunto per numero di reviews.");
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
