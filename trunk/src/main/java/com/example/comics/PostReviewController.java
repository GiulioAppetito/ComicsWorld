package com.example.comics;

import com.example.comics.fagioli.ObjectiveBean;
import com.example.comics.fagioli.ReviewBean;
import com.example.comics.model.*;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.dao.ReviewDAO;
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
        System.out.println("[POST REVIEW CONTROLLER] Hai numReviews = "+numOfReviews);

        //controllo degli obiettivi
        for(Objective objective : series.getObjectives()){
            System.out.println("[POST REVIEW CONTROLLER] Iniziato il ciclo for del titolo : "+series.getTitle());
            if(objective.getType().equals("reviewsObjective")){
                System.out.println("[P REV CONTR] C'è almeno un reviewsObjective!");
                if(objective.achieveObjective(numOfReviews)){
                    //controllo che il lettore non abbia già raggiunto questo obiettivo
                    System.out.println("[P REV CONTR] Achieved objective");
                    if(UserLogin.getInstance().getReader().hasAchievedThisObjective(objective)){
                        System.out.println("[P REV CONTR] Reader  ha questo obiettivo gia.");

                    }else {
                        System.out.println("[P REV CONTR] Reader non ha questo obiettivo gia.");
                        //aggiungo obiettivo alla lista e salvo sul DB + assegno badge
                        UserLogin.getInstance().getReader().addAchievedObjective(objective);

                        ObjectiveBean objectiveBean = new ObjectiveBean();
                        objectiveBean.setBadge(objective.getBadge());
                        objectiveBean.setLevel(objective.getLevel());
                        objectiveBean.setSeriesTitle((objective.getSeries_title()));
                        notifyAchievedReviewObjective(objectiveBean);

                        //genero discount code
                        //discount code : arriva la mail
                    }
                    System.out.println("[ P REV CONTR] Dopo if e else");
                }else{
                    System.out.println("Obiettivo non raggiunto.");
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
