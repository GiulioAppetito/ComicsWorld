package com.example.comics.controller;

import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.ChapterBean;

import java.sql.SQLException;

public class PublishChapterController {

    public void publishChapter(ChapterBean chapterBean, String seriesTitle){
        //cerca la serie dell'author relativa al capitolo e aggiungi capitolo
        for(Series series : UserLogin.getInstance().getAuthor().getPublishedSeries()){
            if(series.getTitle().equals(seriesTitle)){
                try {
                    series.addChapter(chapterBean.getTitle(),chapterBean.getCover(),chapterBean.getDescription(),chapterBean.getCoverInputStream());
                } catch (SQLException e) {
                    //review non trovate
                    e.printStackTrace();
                }
            }
        }

        //persistenza chapter

    }
}
