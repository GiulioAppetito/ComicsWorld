package com.example.comics.model;

import com.example.comics.model.dao.AccountDAO;
import com.example.comics.model.dao.ObjectiveDAO;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.dao.SeriesDAO;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

public class Reader extends Account{

    private ArrayList<Series> favourites;
    private ArrayList<Series> toRead;
    private ArrayList<Series> reading;
    private ArrayList<Review> publishedReviews;
    private ArrayList<Objective> objectives;




    public Reader(ArrayList<Series> favourites, ArrayList<Series> toRead, ArrayList<Series> reading,ArrayList<Review> publishedReviews,String username){

        this.setUsername(username);

        this.favourites = favourites;
        this.toRead = toRead;
        this.reading = reading;
        this.publishedReviews = publishedReviews;

        ObjectiveDAO objectiveDAO = new ObjectiveDAO();

        objectives = objectiveDAO.retreiveAchievedObjectives(username);
    }

    private static final String ROLE = "reader";

    @Override
    public String getRole(){
        return ROLE;
    }

    public ArrayList<Series> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Series> favourites) {
        this.favourites = favourites;
    }

    public ArrayList<Series> getToRead() {
        return toRead;
    }

    public void setToRead(ArrayList<Series> toRead) {
        this.toRead = toRead;
    }

    public ArrayList<Series> getReading() {
        return reading;
    }

    public void setReading(ArrayList<Series> reading) {
        this.reading = reading;
    }

    public ArrayList<Review> getPublishedReviews() {
        return publishedReviews;
    }

    public void setPublishedReviews(ArrayList<Review> publishedReviews) {
        this.publishedReviews = publishedReviews;
    }

    public boolean hasAchievedThisObjective(Objective objective) {
        if(this.objectives.isEmpty()){
            System.out.println("objectives is empty");
            return false;
        }

        for(Objective readersObjective : this.objectives){

            System.out.println("titolo serie di objective già in possesso" + readersObjective.getSeries_title() + "titolo nuova serie" + objective.getSeries_title());

            if(readersObjective.getSeries_title().equals(objective.getSeries_title())
                    && (readersObjective.getLevel().toString().equals(objective.getLevel().toString())) && (readersObjective.getType().equals(objective.getType()))){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(ArrayList<Objective> objectives) {
        this.objectives = objectives;
    }

    public void addPublishedReview(Review review) {
        this.publishedReviews.add(review);
    }

    public void addAchievedObjective(Objective objective) {


        this.objectives.add(objective);

        ReaderDAO readerDAO = new ReaderDAO();
        System.out.println("[Reader] Calling readerDAO con username " +this.getUsername());
        readerDAO.saveAchievedObjective(objective, this.getUsername());



    }

}
