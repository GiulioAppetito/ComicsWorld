package com.example.comics.model;

import com.example.comics.model.dao.ObjectiveDAO;
import com.example.comics.model.dao.ReaderDAO;

import java.sql.SQLException;
import java.util.List;

public class Reader extends Account{

    private List<Series> favourites;
    private List<Series> toRead;
    private List<Series> reading;
    private List<Review> publishedReviews;
    private List<Objective> objectives;

    public Reader(List<Series> favourites, List<Series> toRead, List<Series> reading, List<Review> publishedReviews,String username){

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

    public List<Series> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Series> favourites) {
        this.favourites = favourites;
    }

    public List<Series> getToRead() {
        return toRead;
    }

    public void setToRead(List<Series> toRead) {
        this.toRead = toRead;
    }

    public List<Series> getReading() {
        return reading;
    }

    public void setReading(List<Series> reading) {
        this.reading = reading;
    }

    public List<Review> getPublishedReviews() {
        return publishedReviews;
    }

    public void setPublishedReviews(List<Review> publishedReviews) {
        this.publishedReviews = publishedReviews;
    }

    public boolean hasAchievedThisObjective(Objective objective) {

        if(this.objectives.isEmpty()){
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

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
    }

    public void addPublishedReview(Review review) {
        this.publishedReviews.add(review);
    }

    public void addAchievedObjective(Objective objective) {
        this.objectives.add(objective);
        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.saveAchievedObjective(objective, this.getUsername());
    }

    public void addDiscountCode(DiscountCode discountCode) {
        ReaderDAO readerDAO = new ReaderDAO();
        try {
            readerDAO.saveObtainedDiscountCode(discountCode,this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean likesThisSeries(Series series){

        for(Series fav : this.favourites){
            if(fav.getTitle().equals(series.getTitle())){
                return true;
            }
        }
        return false;
    }

    public void addSeriesToFavourites(Series series) {
        this.favourites.add(series);
    }

    public void removeSeriesFromFavourites(Series series) {
        for(int i=0; i<this.favourites.size(); i++){
            if(favourites.get(i).getTitle().equals(series.getTitle())){
                this.favourites.remove(i);
            }
        }
    }

    public boolean wantsToRead(Series series) {
        for(Series toBeRead : this.toRead){
            if(toBeRead.getTitle().equals(series.getTitle())){
                return true;
            }
        }
        return false;
    }

    public void addSeriesToToRead(Series series) {
        this.toRead.add(series);
    }

    public void removeSeriesFromToRead(Series series) {
        for(int i=0; i<this.toRead.size(); i++){
            if(toRead.get(i).getTitle().equals(series.getTitle())){
                this.toRead.remove(i);
            }
        }
    }
}
