package com.example.comics.model;

import com.example.comics.model.dao.BadgeDAO;
import com.example.comics.model.dao.OrderDAO;
import com.example.comics.model.dao.ReaderDAO;
import com.example.comics.model.fagioli.bundle.BadgeBundle;

import java.util.List;
import java.util.Map;

public class Reader extends Account{

    private final List<Series> favourites;
    private final List<Series> toRead;
    private final List<Series> reading;
    private final List<Badge> badges;
    private final List<Author> followedAuthors;
    private final Map<DiscountCode,Series> discountCodes;
    private final List<Order> ordersHistory;

    private static Badge latestBadge = null;

    public Reader(List<Series> favourites, List<Series> toRead, List<Series> reading, String username,List<Author> followedAuthors, Map<DiscountCode,Series> discountCodes){

        this.setUsername(username);
        this.favourites = favourites;
        this.toRead = toRead;
        this.reading = reading;
        this.followedAuthors = followedAuthors;
        this.discountCodes = discountCodes;

        OrderDAO orderDAO = new OrderDAO();
        this.ordersHistory = orderDAO.retrieveOrders(username);


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
        latestBadge = badge;

        new Thread(()->{
            ReaderDAO readerDAO = new ReaderDAO();
            readerDAO.saveAchievedBadge(badge, this);
        }).start();

        BadgeBundle badgeBundle = new BadgeBundle();
        badgeBundle.setName(badge.getName());
        badgeBundle.setIcon(badge.getIcon());

        notifyObserversNewBadge(badgeBundle);
    }

    public void assignDiscountCode(DiscountCode discountCode, Series series) {
        UserLogin.getInstance().getReader().discountCodes.put(discountCode,series);
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

        if(this.toRead.size() == 0){
            return;
        }

        for (int i = this.toRead.size() - 1; i > 0; i--) {
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

    public void addSeriesToReading(Series seriesToAdd){
        for(Series series : this.reading){
            if(series == null){
                break;
            }
            if(series.getTitle().equals(seriesToAdd.getTitle())){
                return;
            }
        }
        this.reading.add(seriesToAdd);
    }

    public void removeFromReading(Series series){

        if(this.reading.size() == 0){
            return;
        }

        for (int i = this.reading.size() - 1; i > 0; i--) {
            if (reading.get(i).getTitle().equals(series.getTitle())) {
                this.reading.remove(i);
            }
        }
    }

    public Map<DiscountCode,Series> getDiscountCodes() {
        return discountCodes;
    }

    public void removeDiscountCode(DiscountCode codeToRemove) {
        for(DiscountCode discountCode : discountCodes.keySet()){
            if(discountCode.getCode().equals(codeToRemove.getCode())){
                discountCodes.remove(discountCode);
            }
        }
    }

    public DiscountCode getDiscountCodeByCode(String code) {
        for(DiscountCode discountCode : discountCodes.keySet()){
            if(discountCode.getCode().equals(code)){
                return discountCode;
            }
        }
        return null;
    }

    public List<Order> getOrdersHistory() {
        return ordersHistory;
    }

    public void addNewOrder(Order order) {

        System.out.println("adding new order");
        this.ordersHistory.add(order);
        notifyObserversNewOrder(true);
    }


    public void notifyFailedOrder() {
        notifyObserversNewOrder(false);
    }
}
