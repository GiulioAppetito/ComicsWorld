package com.example.comics.controller;

import com.example.comics.model.*;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.fagioli.ObjectiveBean;
import com.example.comics.model.fagioli.SeriesBean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PublishSeriesController {

    public void publishSeries(SeriesBean seriesBean, List<ObjectiveBean>objectiveBeans){

        Series series;
        SeriesDAO seriesDAO = new SeriesDAO();

        HashMap<Objective, InputStream> objectiveBadgeHM = new HashMap<>();

        List<Objective> objectives = new ArrayList<>();
        Objective objective;

        for(ObjectiveBean objectiveBean : objectiveBeans){
            Badge badge = new Badge();
            badge.setName(objectiveBean.getBadgeBean().getName());
            badge.setIcon(objectiveBean.getBadgeBean().getIcon());

            Discount discount = new Discount(objectiveBean.getDiscountBean().getPercentage());
            discount.setLimitDays(objectiveBean.getDiscountBean().getLimitDays());

            if(objectiveBean.getType().equals("reviews")){
                objective = new ReviewsObjective(badge, discount,objectiveBean.getRequirement());
            }else{
                objective = new ChapterObjective(badge,discount,objectiveBean.getRequirement());
            }
            objective.setLevel(Levels.valueOf(objectiveBean.getLevel()));
            objective.setRequirement(objectiveBean.getRequirement());
            objectives.add(objective);
            objectiveBadgeHM.put(objective,objectiveBean.getBadgeIconInputStream());
        }

        //istanziazione e salvataggio su DB
        series = seriesDAO.createSeries(UserLogin.getInstance().getAuthor(),seriesBean.getTitle(),seriesBean.getGenre1(),seriesBean.getGenre2(),seriesBean.getGenre3(),seriesBean.getCover(),seriesBean.getCoverInputStream(), objectives,objectiveBadgeHM);

        //aggiunta della serie all'autore
        UserLogin.getInstance().getAuthor().addPublishedSeries(series);



    }
}
