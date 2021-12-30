package com.example.comics.view1;

import com.example.comics.fagioli.ChapterBean;
import com.example.comics.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SeriesControllerG {

    @FXML
    public Button btnBack;


    @FXML
    private ImageView comicCover;

    @FXML
    private Button btnCheck;

    @FXML
    private VBox vbChapters;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblTitle;


    public void init(SeriesBean seriesBean) {

        lblAuthor.setText(seriesBean.getAuthor());
        lblTitle.setText(seriesBean.getTitle());
        comicCover.setImage(seriesBean.getCover());


        List<ChapterBean> listOfChapters = seriesBean.getChapters();

        for (ChapterBean chapterBean : listOfChapters) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("chapterItem.fxml"));

            try {
                VBox vbChapter = fxmlLoader.load();
                ChapterItemController chapterControllerItem = fxmlLoader.getController();
                chapterControllerItem.setData(chapterBean.getTitle(),chapterBean.getSeries(),chapterBean.getCover());

                vbChapter.setOnMouseClicked(event -> {
                    try {
                        openChapter(chapterBean);
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
                System.out.println("ciao");

        }
        );
    }

    private void openChapter(ChapterBean chapterBean) throws IOException {

        ChapterControllerG chapterControllerG = new ChapterControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = ChapterControllerG.class.getResource("chapter.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(chapterControllerG);

        HomeFactory homeFactory = new HomeFactory();

        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.changeCenter(loader.load());

        chapterControllerG.init(chapterBean);

    }


}
