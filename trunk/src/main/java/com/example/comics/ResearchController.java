package com.example.comics;

import com.example.comics.fagioli.ChapterBean;
import com.example.comics.fagioli.SeriesBean;
import com.example.comics.model.Chapter;
import com.example.comics.model.Series;
import com.example.comics.model.dao.SeriesDAO;

import java.util.ArrayList;

public class ResearchController {

    public ArrayList<SeriesBean> getLatestSeries(){

        ArrayList<SeriesBean> seriesBeans = new ArrayList<>();
        SeriesBean seriesBean;

        SeriesDAO seriesdao = new SeriesDAO();
        ArrayList<Series> latestSeries = seriesdao.retriveLatestSeries();
        //return Series.getSeries();
        for(Series series : latestSeries){
            seriesBean = new SeriesBean();
            seriesBean.setTitle(series.getTitle());
            seriesBean.setAuthor(series.getAuthor());

            seriesBeans.add(seriesBean);
            //seriesBean.setCover();
        }


        return seriesBeans;
    }

    //qui io chiedo sempre alla serie perchè
    //Series è composta di chapters, che seguono il suo corso di vita,
    //quindi non posso comunicare direttamente con istanze di chapter per conto mio
    //ma attraverso il composto
    public ArrayList<ChapterBean> getChapters(String seriesTitle){

        ArrayList<ChapterBean> chapterBeans = new ArrayList<>();
        ChapterBean chapterBean;

        SeriesDAO seriesDAO = new SeriesDAO();
        ArrayList<Chapter> chapters = seriesDAO.retriveChapters(seriesTitle);

        for(Chapter chapter : chapters){
            chapterBean = new ChapterBean();
            chapterBean.setTitle(chapter.getTitle());
            chapterBean.setSeries(chapter.getSeries());
            chapterBean.setId(chapter.getId());

            chapterBeans.add(chapterBean);
        }
        return chapterBeans;

    }

}
