package com.example.comics.view1;

import com.example.comics.model.Chapter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerieController {

    @FXML
    public Button btnBack;

    @FXML
    private Button btnCheck;


    @FXML
    private VBox vbChapters;

    public void init() {

        ArrayList<Chapter> listOfChapters = new ArrayList<>(addChapters());
        int len = listOfChapters.size();

        for (Chapter chapter : listOfChapters) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("chapterItem.fxml"));
            try {
                VBox vbChapter = fxmlLoader.load();
                ChapterItemController chapterControllerItem = fxmlLoader.getController();
                chapterControllerItem.setData(chapter);

                vbChapter.setOnMouseClicked(event -> {
                    try {
                        openChapter();
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
            ReaderHomeControllerG readerHomeControllerG = ReaderHomeControllerG.getInstance();
            try {
                readerHomeControllerG.openFeed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnCheck.setOnAction(event ->
        {
            try {
                openChapter();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        );
    }

    private void openChapter() throws IOException {
        System.out.println("Opening chapter...");

        ChapterControllerG chapterControllerG = new ChapterControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = ChapterControllerG.class.getResource("chapter.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(chapterControllerG);

        ReaderHomeControllerG readerHomeControllerG = ReaderHomeControllerG.getInstance();
        readerHomeControllerG.changeCenter(loader.load());

        chapterControllerG.init();

    }

    private List<Chapter> addChapters(){

        List<Chapter> chapters = new ArrayList<>();
        int i;
        List<String> nameList = Arrays.asList("1.Chapter","2.Chapter","3.Chapter","4.Chapter");
        List<String> seriesList = Arrays.asList("Spiderman","Spiderman","Spiderman","Spiderman");

        for(i=0; i<nameList.size(); i++){
            Chapter chapter = new Chapter();
            chapter.setName(nameList.get(i));
            chapter.setSeries(seriesList.get(i));
            //comic.setImageSrc(null);
            chapters.add(chapter);
        }

        return chapters;

    }
}
