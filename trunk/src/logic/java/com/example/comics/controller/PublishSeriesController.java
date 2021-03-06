package com.example.comics.controller;

import com.example.comics.controller.boundaries.PublishSeriesReaderBoundary;
import com.example.comics.model.*;
import com.example.comics.model.dao.AccountDAO;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.exceptions.AlreadyExistingSeriesException;
import com.example.comics.model.fagioli.*;
import com.example.comics.model.fagioli.bundle.AuthorBundle;
import com.example.comics.model.fagioli.bundle.ReaderBundle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PublishSeriesController {

    public void publishSeries(SeriesBean seriesBean, List<ObjectiveBean>objectiveBeans) throws AlreadyExistingSeriesException {

        Series series;
        SeriesDAO seriesDAO = new SeriesDAO();

        HashMap<Objective, InputStream> objectiveBadgeHM = new HashMap<>();

        List<Objective> objectives = new ArrayList<>();

        for(ObjectiveBean objectiveBean : objectiveBeans){
            Badge badge = new Badge();
            badge.setName(objectiveBean.getBadgeBean().getName());
            badge.setIcon(objectiveBean.getBadgeBean().getIcon());

            Discount discount = new Discount(objectiveBean.getDiscountBean().getPercentage());
            discount.setLimitDays(objectiveBean.getDiscountBean().getLimitDays());

            Objective objective;
            try{
                ObjectiveFactory objectiveFactory = new ObjectiveFactory();
                objective = objectiveFactory.createObjective(objectiveBean.getType(),badge,discount,Levels.valueOf(objectiveBean.getLevel()),objectiveBean.getRequirement());
            }catch (Exception e){
                throw new AlreadyExistingSeriesException("Error while creating serie's objectives.",e.getCause());
            }


            objectives.add(objective);
            objectiveBadgeHM.put(objective,objectiveBean.getBadgeIconInputStream());
        }

        List<Genres> genresList = new ArrayList<>(List.of(seriesBean.getGenre1(), seriesBean.getGenre2(), seriesBean.getGenre3()));

        //istanziazione e salvataggio su DB
        series = seriesDAO.createSeries(seriesBean.getTitle(),genresList,seriesBean.getCover(),seriesBean.getCoverInputStream(), objectives,objectiveBadgeHM,seriesBean.getDescription());

        //aggiunta della serie all'autore
        UserLogin.getInstance().getAuthor().addPublishedSeries(series);

        new Thread(()->{
            //invio mail ai lettori che seguono l'autore
            PublishSeriesReaderBoundary boundary = new PublishSeriesReaderBoundary();
            AccountDAO accountDAO = new AccountDAO();
            List<String> followersMails = accountDAO.retreiveAuthorFollowersMails(UserLogin.getInstance().getAuthor().getUsername());
            ReaderBean readerBean;
            AuthorBean authorBean = new AuthorBundle();

            authorBean.setEmail(UserLogin.getInstance().getAccount().getEmail());
            authorBean.setUsername(UserLogin.getInstance().getAccount().getUsername());
            authorBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
            authorBean.setLastName(UserLogin.getInstance().getAccount().getLastName());
            for(String followerMail : followersMails){
                readerBean = new ReaderBundle();
                readerBean.setEmail(followerMail);
                boundary.sendEmailForSeriesPublished(authorBean,seriesBean,readerBean);
            }
        }).start();
    }
}
