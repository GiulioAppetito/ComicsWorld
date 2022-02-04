package com.example.comics.model;

import com.example.comics.model.dao.BadgeDAO;
import com.example.comics.model.dao.DiscountCodeDAO;
import com.example.comics.model.dao.ReaderDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reader extends Account{

    private List<Series> favourites;
    private List<Series> toRead;
    private List<Series> reading;
    private List<Badge> badges;
    private List<Author> followedAuthors;
    private List<DiscountCode> discountCodes;

    public Reader(List<Series> favourites, List<Series> toRead, List<Series> reading, String username,List<Author> followedAuthors, List<DiscountCode> discountCodes){

        this.setUsername(username);
        this.favourites = favourites;
        this.toRead = toRead;
        this.reading = reading;
        this.followedAuthors = followedAuthors;
        this.discountCodes = discountCodes;

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

    public List<Series> getReading(){ return reading; }

    public boolean hasAchievedThisBadge(Badge badge) {

        if(this.badges.isEmpty()){
            return false;
        }

        for(Badge readerBadge : this.badges){

            if(readerBadge.getId() == badge.getId()){
                return true;
            }
        }
        return false;

    }

    public List<Badge> getBadges() {
        return this.badges;
    }

    public void addAchievedBadge(Badge badge) {
        this.badges.add(badge);
        ReaderDAO readerDAO = new ReaderDAO();
        readerDAO.saveAchievedBadge(badge, this);
    }

    public void addDiscountCode(DiscountCode discountCode) {
        DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
        try {
            System.out.println("[READER] Saving discount code.");
            discountCodeDAO.saveObtainedDiscountCode(discountCode,this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean likesThisSeries(Series series){
        if(favourites.isEmpty()){
            System.out.println("Reader: No favs");
            return false;
        }
        for(Series fav : this.favourites){
            if(fav==null){
                return false;
            }
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
        for (int i = this.favourites.size() - 1; i >= 0; i--) {
            if(favourites.get(i).getTitle().equals(series.getTitle())){
                this.favourites.remove(i);
            }
        }

        for (int i = this.favourites.size() - 1; i >= 0; i--) {
            System.out.println(favourites.get(i).getTitle());
        }
    }

    public boolean wantsToRead(Series series) {
        if(toRead.isEmpty()){
            return false;
        }
        for(Series toBeRead : this.toRead){
            if(toBeRead==null){
                return false;
            }
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
        for (int i = this.toRead.size() - 1; i >= 0; i--) {
            if (toRead.get(i).getTitle().equals(series.getTitle())) {
                this.toRead.remove(i);
            }
        }
    }

    public List<Author> getFollowedAuthors() {
        return followedAuthors;
    }

    public void addFollowedAuthor(Author author) {
        this.followedAuthors.add(author);
    }

    public void unfollowAuthor(Author author) {
        for (int i = this.followedAuthors.size() - 1; i >= 0; i--) {
            if(author.getUsername().equals(followedAuthors.get(i).getUsername())){
                this.followedAuthors.remove(i);
            }
        }
    }

    public void markChapter(Series series,String chapterTitle){
        addSeriesToReading(series);
        removeSeriesFromToRead(series);
        for(Chapter chapter : series.getChapters()){
            if(chapter.getTitle().equals(chapterTitle)){
                chapter.setRead(true);
            }
        }
    }

    public void unmarkChapter(Series series, String chapterTitle) {

        boolean isToBeRemoved = true;
        Series seriesToRemove = null;

        for(Series readerSeries : reading){
            if(readerSeries.getTitle().equals(series.getTitle())){
                for(Chapter chapter : readerSeries.getChapters()){
                    if(chapter.getTitle().equals(chapterTitle)){
                        chapter.setRead(false);
                        seriesToRemove = readerSeries;
                    }
                }

                for(Chapter chapter : readerSeries.getChapters()){
                    if(chapter.getRead()){
                        isToBeRemoved = false;
                    }
                }

            }
        }
        if(isToBeRemoved){
            reading.remove(seriesToRemove);
        }

    }

    private void addSeriesToReading(Series seriesToAdd){
        for(Series series : this.reading){
            if(series.getTitle().equals(seriesToAdd.getTitle())){
                return;
            }
        }
        this.reading.add(seriesToAdd);
    }

    public List<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

    public void setDiscountCodes(List<DiscountCode> discountCodes) {
        this.discountCodes = discountCodes;
    }

    public void removeDiscountCode(DiscountCode codeToRemove) {
        for(DiscountCode discountCode : discountCodes){
            if(discountCode.getCode().equals(codeToRemove.getCode())){
                discountCodes.remove(discountCode);
            }
        }
    }

    public DiscountCode getDiscountCodeByCode(String code) {
        for(DiscountCode discountCode : discountCodes){
            if(discountCode.getCode().equals(code)){
                return discountCode;
            }
        }
        return null;
    }


}
