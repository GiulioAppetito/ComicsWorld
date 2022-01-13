package com.example.comics.view1;

import com.example.comics.controller.PublishChapterController;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.view1.beans.ChapterBean1;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

public class PublishChapterControllerG {


    @FXML
    private ImageView ivComicCover;


    @FXML
    private ChoiceBox<String> choiceBoxSeries;

    @FXML
    private Button btnChangeCover;

    @FXML
    private TextField tfChapterTitle;

    @FXML
    private Label lblSeriesTitle;

    @FXML
    private TextArea taDescription;

    @FXML
    private Button btnPublishChapter;

    String imageCoverPath;

    public void initialize(){
        btnPublishChapter.setOnAction(event -> publishChapter());
        btnChangeCover.setOnAction(event -> changeCover());

        List<Series> publishedSeries = UserLogin.getInstance().getAuthor().getPublishedSeries();
        List<String> seriesTitles = new ArrayList<>();
        for(Series series : publishedSeries){
            seriesTitles.add(series.getTitle());
        }
        choiceBoxSeries.getItems().setAll(seriesTitles);

    }

    private void changeCover() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.png","*.png"));
        File f = fc.showOpenDialog(null);
        if(f!=null){
            imageCoverPath = f.getAbsolutePath();
            System.out.println("Cover path is : "+imageCoverPath);
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(imageCoverPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image cover = new Image(inputStream);
            ivComicCover.setImage(cover);
        }

    }

    public void publishChapter(){
        System.out.println("Publishing chapter...");
        String chapterTitle = tfChapterTitle.getText();
        Image chapterCover = ivComicCover.getImage();
        String chapterDescription = taDescription.getText();
        String seriesTitle = choiceBoxSeries.getValue();

        ChapterBean chapterBean = new ChapterBean1();
        chapterBean.setTitle(chapterTitle);
        chapterBean.setDescription(chapterDescription);

        chapterBean.setCover(chapterCover);
        try {
            chapterBean.setCoverInputStream(new FileInputStream(imageCoverPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        PublishChapterController publishChapterController = new PublishChapterController();
        publishChapterController.publishChapter(chapterBean,seriesTitle);

    }

}
