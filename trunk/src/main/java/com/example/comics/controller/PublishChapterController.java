package com.example.comics.controller;

import com.example.comics.controller.boundaries.PublishChapterReaderBoundary;
import com.example.comics.controller.boundaries.PublishSeriesReaderBoundary;
import com.example.comics.model.Chapter;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.AccountDAO;
import com.example.comics.model.dao.ChapterDAO;
import com.example.comics.model.exceptions.AlreadyExistingChapterException;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.ReaderBean;
import com.example.comics.model.fagioli.bundle.AuthorBundle;
import com.example.comics.model.fagioli.bundle.ReaderBundle;

import java.util.List;
import java.util.Observer;

public class PublishChapterController {

    public void publishChapter(ChapterBean chapterBean, String seriesTitle)throws AlreadyExistingChapterException {


        Chapter chapter = null;
        //cerca la serie dell'author relativa al capitolo e aggiungi capitolo
        for(Series series : UserLogin.getInstance().getAuthor().getPublishedSeries()){
            if(series.getTitle().equals(seriesTitle)){
                chapter = series.addChapter(chapterBean.getTitle(),chapterBean.getCover(),chapterBean.getDescription(),chapterBean.getPrice());
            }
        }

        ChapterDAO chapterDAO = new ChapterDAO();
        chapterDAO.saveChapter(chapter,seriesTitle,chapterBean.getCoverInputStream());


        new Thread(()->{
            //invio mail ai lettori che seguono l'autore
            PublishChapterReaderBoundary boundary = new PublishChapterReaderBoundary();
            AccountDAO accountDAO = new AccountDAO();
            List<String> followersMails = accountDAO.retreiveAuthorFollowersMails(UserLogin.getInstance().getAuthor());
            ReaderBean readerBean;
            AuthorBean authorBean = new AuthorBundle();
            authorBean.setUsername(UserLogin.getInstance().getAccount().getUsername());
            authorBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
            authorBean.setLastName(UserLogin.getInstance().getAccount().getLastName());
            authorBean.setEmail(UserLogin.getInstance().getAccount().getEmail());
            for(String followerMail : followersMails){
                readerBean = new ReaderBundle();
                readerBean.setEmail(followerMail);
                boundary.sendEmailForChapterPublished(authorBean,chapterBean,seriesTitle,readerBean);
            }
        }).start();

    }

}
