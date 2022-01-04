package com.example.comics.model;

import com.example.comics.model.dao.BadgeDAO;
import com.example.comics.model.dao.ObjectiveDAO;
import com.example.comics.model.dao.ReaderDAO;

import java.sql.SQLException;
import java.util.List;

public class Reader extends Account{

    private List<Series> favourites;
    private List<Series> toRead;
    private List<Series> reading;
    private List<Review> publishedReviews;
    private List<Badge> badges;

    public Reader(List<Series> favourites, List<Series> toRead, List<Series> reading, List<Review> publishedReviews,String username){

        this.setUsername(username);

        this.favourites = favourites;
        this.toRead = toRead;
        this.reading = reading;
        this.publishedReviews = publishedReviews;

        BadgeDAO badgesDAO = new BadgeDAO();
        this.badges = badgesDAO.retrieveAchievedBadges(username);
    }

    private static final String ROLE = "reader";

    @Override
    public String getRole(){
        return ROLE;
    }

    public List<Series> getFavourites() {
        return favourites;
    }

    public List<Series> getToRead() {
        return toRead;
    }

    public List<Review> getPublishedReviews() {
        return publishedReviews;
    }

    public boolean hasAchievedThisBadge(Badge badge) {

        if(this.badges.isEmpty()){
            return false;
        }

        for(Badge readerBadge : this.badges){

            System.out.println("[READER] Badge già in possesso");

            if(readerBadge.getId() == badge.getId()){
                return true;
            }
        }
        return false;

    }

    public List<Badge> getBadges() {

        if(this.badges.isEmpty()){
            System.out.println("[READER]: lista badge vuota");
        }
        return this.badges;
    }

    public void addPublishedReview(Review review) {
        this.publishedReviews.add(review);
    }

    public void addAchievedBadge(Badge badge) {
        this.badges.add(badge);
        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.saveAchievedBadge(badge, this);
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
