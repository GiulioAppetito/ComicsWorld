package com.example.comics.view1;

import com.example.comics.controller.PublishChapterController;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.exceptions.AlreadyExistingChapterException;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.view1.beans.ChapterBean1;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    private Pane publishPane;

    @FXML
    private Label lblPublishResult;

    @FXML
    private TextField tfChapterTitle;

    @FXML
    private Button btnClosePublishPane;

    @FXML
    private TextField tfPrice;

    @FXML
    private TextArea taDescription;

    @FXML
    private Button btnPublishChapter;

    private String imageCoverPath;

    public void initialize(){
        btnPublishChapter.setOnAction(event -> publishChapter());
        btnChangeCover.setOnAction(event -> changeCover());
        btnClosePublishPane.setOnAction(event -> closePublishPane());

        List<Series> publishedSeries = UserLogin.getInstance().getAuthor().getPublishedSeries();
        List<String> seriesTitles = new ArrayList<>();
        for(Series series : publishedSeries){
            seriesTitles.add(series.getTitle());
        }
        choiceBoxSeries.getItems().setAll(seriesTitles);

        closePublishPane();

    }

    private void closePublishPane() {
        publishPane.setVisible(false);
    }

    private void changeCover() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.jpg,*.png","*.jpg","*.png"));
        File f = fc.showOpenDialog(null);
        if(f!=null){
            imageCoverPath = f.getAbsolutePath();
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
        String chapterTitle = tfChapterTitle.getText();
        Image chapterCover = ivComicCover.getImage();
        String chapterDescription = taDescription.getText();
        String seriesTitle = choiceBoxSeries.getValue();

        ChapterBean chapterBean = new ChapterBean1();
        chapterBean.setTitle(chapterTitle);
        chapterBean.setDescription(chapterDescription);
        chapterBean.setPrice(Float.parseFloat(tfPrice.getText()));
        chapterBean.setCover(chapterCover);
        try {
            chapterBean.setCoverInputStream(new FileInputStream(imageCoverPath));
            PublishChapterController publishChapterController = new PublishChapterController();
            publishChapterController.publishChapter(chapterBean,seriesTitle);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (AlreadyExistingChapterException e){
            publishPane.setVisible(true);
            lblPublishResult.setText(e.getMessage());
            tfChapterTitle.setText("");
        }




    }

}
