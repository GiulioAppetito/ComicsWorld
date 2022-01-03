package com.example.comics.view1;

import com.example.comics.controller.FavouritesController;
import com.example.comics.controller.ResearchController;
import com.example.comics.controller.ToReadController;
import com.example.comics.model.Series;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SeriesControllerG {

    @FXML
    public Button btnBack;

    @FXML
    public Button btnAddToFav;

    @FXML
    public Button btnAddToRead;

    @FXML
    private ImageView comicCover;

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

        FavouritesController favouritesController = new FavouritesController();
        if(favouritesController.isSeriesFavourite(seriesBean)){
            //setta cuore pieno
            System.out.println("Serie tra i preferiti: " + seriesBean.getTitle());
            btnAddToFav.setStyle("-fx-background-color: #5DADE2");
            btnAddToFav.setOnAction(event -> removeFromFavourites(seriesBean));
        }else{
            //cuore vuoto
            System.out.println("Serie NON tra i preferiti: " + seriesBean.getTitle());
            btnAddToFav.setStyle("-fx-background-color: #AACC22");
            btnAddToFav.setOnAction(event -> addSeriesToFavourites(seriesBean));
        }

        ToReadController toReadController = new ToReadController();
        if(toReadController.isSeriesAddedToRead(seriesBean)){
            //setta cuore pieno
            System.out.println("Serie tra quelli da leggere : " + seriesBean.getTitle());
            btnAddToRead.setStyle("-fx-background-color: #5DADE2");
            btnAddToRead.setOnAction(event -> removeSeriesFromToRead(seriesBean));
        }else{
            //cuore vuoto
            System.out.println("Serie NON tra quelli da leggere : " + seriesBean.getTitle());
            btnAddToRead.setStyle("-fx-background-color: #AACC22");
            btnAddToRead.setOnAction(event -> addSeriesToToRead(seriesBean));
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

    }

    private void removeSeriesFromToRead(SeriesBean seriesBean) {
        ToReadController toReadController = new ToReadController();
        toReadController.removeSeriesFromToRead(seriesBean);
        btnAddToRead.setOnAction(event -> addSeriesToToRead(seriesBean));
    }

    private void addSeriesToToRead(SeriesBean seriesBean) {
        ToReadController toReadController = new ToReadController();
        toReadController.addSeriesToToRead(seriesBean);
        btnAddToRead.setOnAction(event -> removeSeriesFromToRead(seriesBean));

    }

    private void addSeriesToFavourites(SeriesBean seriesBean) {
        FavouritesController favouritesController = new FavouritesController();
        favouritesController.addSeriesToFavourites(seriesBean);
        btnAddToFav.setOnAction(event -> removeFromFavourites(seriesBean));
        System.out.println("Serie aggiunta tra i preferiti: " + seriesBean.getTitle());
        btnAddToFav.setStyle("-fx-background-color: #5DADE2");
    }

    private void removeFromFavourites(SeriesBean seriesBean) {
        FavouritesController favouritesController = new FavouritesController();
        favouritesController.removeSeriesFromFavourites(seriesBean);
        btnAddToFav.setOnAction(event -> addSeriesToFavourites(seriesBean));
        System.out.println("Serie rimossa dai preferiti: " + seriesBean.getTitle());
        btnAddToFav.setStyle("-fx-background-color: #AACC22");
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
