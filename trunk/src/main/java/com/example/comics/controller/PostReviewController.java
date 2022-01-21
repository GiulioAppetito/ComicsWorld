package com.example.comics.controller;

import com.example.comics.controller.mailBoundary.BoundarySendEmail;
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
        System.out.println("RESCON: ecco l'email dell'autore: " + author.getEmail());
        author.setPublishedSeries(seriesBean.getAuthor().getPublishedSeries());

        SeriesDAO seriesDAO = new SeriesDAO();
        Series series = seriesDAO.retreiveSeriesWithAuthor(seriesBean.getTitle(),author);
        series.addReview(chapterBean.getTitle(), reviewBean.getComment(), reviewBean.getRating());

        //controllare obiettivi
        checkObjectives(series);


        //invio mail all'autore di una nuova review
        BoundarySendEmail boundarySendEmail = new BoundarySendEmail();
        boundarySendEmail.sendEmailForNewReviewPosted(series.getAuthor());

    }

    private void checkObjectives(Series series) {
        //numero di review del lettore
        int numOfReviews = series.getNumberOfReviews(UserLogin.getInstance().getReader());

        //controllo degli obiettivi
        for(Objective objective : series.getObjectives()){
            if(objective.getType().equals("reviewsObjective")){
                //controllo che il lettore non abbia già raggiunto questo obiettivo confrontando i badge
                if(!UserLogin.getInstance().getReader().hasAchievedThisBadge(objective.getBadge())){
                    if(objective.achieveObjective(numOfReviews, objective.getBadge())){

                        //aggiungo badge alla lista e salvo sul DB + assegno badge
                        UserLogin.getInstance().getReader().addAchievedBadge(objective.getBadge());

                        //genero discount code
                        DiscountCode discountCode = new DiscountCode(objective.getDiscount());
                        UserLogin.getInstance().getReader().addDiscountCode(discountCode);

                        //discount code : arriva la mail
                        //genero discount code


                        //invio mail al lettore del codice sconto
                        BoundarySendEmail boundarySendEmail = new BoundarySendEmail();
                        boundarySendEmail.sendEmailForDiscountCode(UserLogin.getInstance().getReader(), series, discountCode);


                    }
                }
            }
        }
    }

}
