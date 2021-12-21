package com.example.comics.view1;

import com.example.comics.ResearchController;
import com.example.comics.fagioli.ChapterBean;
import com.example.comics.model.Chapter;
import com.example.comics.model.Series;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeriesControllerG {

    @FXML
    public Button btnBack;

    @FXML
    private Button btnCheck;

    @FXML
    private VBox vbChapters;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblTitle;


    public void init(String series_title, String author) {

        lblAuthor.setText(author);
        lblTitle.setText(series_title);

        ResearchController researchController = new ResearchController();
        //magari serve altro oltre al title
        ArrayList<ChapterBean> listOfChapters = researchController.getChapters(series_title);

        for (ChapterBean chapter : listOfChapters) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("chapterItem.fxml"));

            String chapterTitle = chapter.getTitle();
            try {
                VBox vbChapter = fxmlLoader.load();
                ChapterItemController chapterControllerItem = fxmlLoader.getController();
                chapterControllerItem.setData(chapter.getTitle(),chapter.getSeries());

                vbChapter.setOnMouseClicked(event -> {
                    try {
                        openChapter(chapterTitle, author);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                vbChapters.getChildren().add(vbChapter);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        btnBack.setOnAction(event -> {
            //ha senso che serie conosca home per tornare sul feed?
            //non so, secondo me basterebbe andare dinuovo su feed facendo tipo un refresh
            //però che ne sa il feed che deve sostituire il centro della home?
            //boh
            HomeFactory homeFactory = new HomeFactory();
            HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
            try {
                homeControllerG.openFeed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnCheck.setOnAction(event -> {
                //openChapter(chapterTitle, author);
                System.out.println("ciao");

        }
        );
    }

    private void openChapter(String chapterTitle, String author) throws IOException {

        ChapterControllerG chapterControllerG = new ChapterControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = ChapterControllerG.class.getResource("chapter.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(chapterControllerG);

        HomeFactory homeFactory = new HomeFactory();

        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.changeCenter(loader.load());

        chapterControllerG.init(chapterTitle, author);

    }


}
