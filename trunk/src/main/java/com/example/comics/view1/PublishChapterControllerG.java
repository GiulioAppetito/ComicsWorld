package com.example.comics.view1;

import com.example.comics.controller.PublishChapterController;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.ChapterBean;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

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

    public void initialize(){
        btnPublishChapter.setOnAction(actionEvent -> publishChapter());

        List<Series> publishedSeries = UserLogin.getInstance().getAuthor().getPublishedSeries();
        List<String> seriesTitles = new ArrayList<>();
        for(Series series : publishedSeries){
            seriesTitles.add(series.getTitle());
        }
        choiceBoxSeries.getItems().setAll(seriesTitles);

    }
    public void publishChapter(){
        System.out.println("Publishing chapter...");
        String chapterTitle = tfChapterTitle.getText();
        Image chapterCover = ivComicCover.getImage();
        String chapterDescription = taDescription.getText();
        String seriesTitle = choiceBoxSeries.getValue();

        ChapterBean chapterBean = new ChapterBean();
        chapterBean.setTitle(chapterTitle);
        chapterBean.setCover(chapterCover);
        chapterBean.setDescription(chapterDescription);

        PublishChapterController publishChapterController = new PublishChapterController();
        publishChapterController.publishChapter(chapterBean,seriesTitle);

    }

}
