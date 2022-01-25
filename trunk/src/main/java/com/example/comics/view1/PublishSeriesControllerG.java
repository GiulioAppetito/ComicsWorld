package com.example.comics.view1;

import com.example.comics.controller.PublishChapterController;
import com.example.comics.controller.PublishSeriesController;
import com.example.comics.model.Genres;
import com.example.comics.model.Levels;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.*;
import com.example.comics.view1.beans.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PublishSeriesControllerG {

    @FXML
    private Button btnBack;

    @FXML
    private ImageView ivComicCover;

    @FXML
    private TextField tfTitle;

    @FXML
    private Button btnBadgeIconI;
    @FXML
    private Button btnBadgeIconE;
    @FXML
    private Button btnBadgeIconB;

    @FXML
    private ChoiceBox<Genres> choiceBoxGenre1;

    @FXML
    private ChoiceBox<Genres> choiceBoxGenre2;

    @FXML
    private ChoiceBox<Genres> choiceBoxGenre3;

    @FXML
    private TextArea taDescription;

    @FXML
    private Button btnAddObjectives;

    @FXML
    private Button btnPublishSeries;

    @FXML
    private Pane objectivesSettingsPane;

    @FXML
    private Button btnCloseObjectives;

    @FXML
    private ChoiceBox<String> choiceBoxTypeB;

    @FXML
    private TextField tfTargetNumberB;

    @FXML
    private TextField tfBadgeNameB;

    @FXML
    private TextField tfDiscountPercB;

    @FXML
    private TextField tfDiscountDaysB;

    @FXML
    private ChoiceBox<String> choiceBoxTypeI;

    @FXML
    private TextField tfTargetNumberI;

    @FXML
    private TextField tfBadgeNameI;

    @FXML
    private TextField tfDiscountPercI;

    @FXML
    private TextField tfDiscountDaysI;

    @FXML
    private ChoiceBox<String> choiceBoxTypeE;

    @FXML
    private TextField tfTargetNumberE;

    @FXML
    private TextField tfBadgeNameE;

    @FXML
    private TextField tfDiscountPercE;

    @FXML
    private TextField tfDiscountDaysE;

    @FXML
    private Button btnApplyObjectives;

    private List<ObjectiveBean> objectiveBeanList;

    @FXML
    private Button btnChangeCover;

    String imageCoverPath;
    String badgeIconPathB;
    String badgeIconPathI;
    String badgeIconPathE;

    public void initialize(){
        closeObjectives();
        objectiveBeanList = new ArrayList<>();
        btnApplyObjectives.setOnAction(actionEvent -> applyObjectives());
        btnCloseObjectives.setOnAction(actionEvent -> closeObjectives());
        btnAddObjectives.setOnAction(actionEvent -> openObjectives());
        btnChangeCover.setOnAction(event -> changeCover());

        btnBadgeIconB.setOnAction(event -> changeBagdeIconB());
        btnBadgeIconI.setOnAction(event -> changeBagdeIconI());
        btnBadgeIconE.setOnAction(event -> changeBagdeIconE());


        btnPublishSeries.setOnAction(event -> publishSeries());

        choiceBoxGenre1.getItems().setAll(Genres.values());
        choiceBoxGenre2.getItems().setAll(Genres.values());
        choiceBoxGenre3.getItems().setAll(Genres.values());
        choiceBoxTypeB.getItems().setAll("REVIEWS","CHAPTERS");
        choiceBoxTypeI.getItems().setAll("REVIEWS","CHAPTERS");
        choiceBoxTypeE.getItems().setAll("REVIEWS","CHAPTERS");

    }

    private void changeBagdeIconI() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.png","*.png"));
        File f = fc.showOpenDialog(null);
        if(f!=null){
            badgeIconPathI = f.getAbsolutePath();
            System.out.println("Cover path is : "+badgeIconPathI);
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(badgeIconPathI);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeBagdeIconB() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.png","*.png"));
        File f = fc.showOpenDialog(null);
        if(f!=null){
            badgeIconPathB = f.getAbsolutePath();
            System.out.println("Cover path is : "+badgeIconPathB);
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(badgeIconPathB);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeBagdeIconE() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.png","*.png"));
        File f = fc.showOpenDialog(null);
        if(f!=null){
            badgeIconPathE = f.getAbsolutePath();
            System.out.println("Cover path is : "+badgeIconPathE);
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(badgeIconPathE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void applyObjectives() {
        objectiveBeanList.clear();

        ObjectiveBean objectiveBean;
        DiscountBean discountBean1;
        BadgeBean badgeBean1;

        if(isThisObjectiveCompiled(Levels.BEGINNER)){
            System.out.println("[PUB SER CONTR] Adding objective with level : "+Levels.BEGINNER);
            discountBean1 = new DiscountBean1();
            discountBean1.setPercentage(Float.parseFloat(tfDiscountPercB.getText()));
            discountBean1.setLimitDays(Integer.parseInt(tfDiscountDaysB.getText()));

            badgeBean1 = new BadgeBean1();
            badgeBean1.setName(tfBadgeNameB.getText());
            badgeBean1.setIcon(new Image(badgeIconPathB));

            objectiveBean = new ObjectiveBean1();
            objectiveBean.setLevel(Levels.BEGINNER);
            objectiveBean.setType(choiceBoxTypeB.getValue());
            objectiveBean.setDiscountBean(discountBean1);
            objectiveBean.setBadgeBean(badgeBean1);
            objectiveBean.setRequirement(Integer.parseInt(tfTargetNumberB.getText()));
            try {
                objectiveBean.setBadgeIconInputStream(new FileInputStream(badgeIconPathB));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            objectiveBeanList.add(objectiveBean);
        }
        if(isThisObjectiveCompiled(Levels.INTERMEDIATE)){
            System.out.println("[PUB SER CONTR] Adding objective with level : "+Levels.INTERMEDIATE);
            discountBean1 = new DiscountBean1();
            discountBean1.setPercentage(Float.parseFloat(tfDiscountPercI.getText()));
            discountBean1.setLimitDays(Integer.parseInt(tfDiscountDaysI.getText()));

            badgeBean1 = new BadgeBean1();
            badgeBean1.setName(tfBadgeNameI.getText());
            badgeBean1.setIcon(new Image(badgeIconPathI));

            objectiveBean = new ObjectiveBean1();
            objectiveBean.setLevel(Levels.INTERMEDIATE);
            objectiveBean.setType(choiceBoxTypeI.getValue());
            objectiveBean.setDiscountBean(discountBean1);
            objectiveBean.setBadgeBean(badgeBean1);
            objectiveBean.setRequirement(Integer.parseInt(tfTargetNumberI.getText()));
            try {
                objectiveBean.setBadgeIconInputStream(new FileInputStream(badgeIconPathI));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            objectiveBeanList.add(objectiveBean);
        }
        if(isThisObjectiveCompiled(Levels.EXPERT)){
            System.out.println("[PUB SER CONTR] Adding objective with level : "+Levels.EXPERT);
            discountBean1 = new DiscountBean1();
            discountBean1.setPercentage(Float.parseFloat(tfDiscountPercE.getText()));
            discountBean1.setLimitDays(Integer.parseInt(tfDiscountDaysE.getText()));

            badgeBean1 = new BadgeBean1();
            badgeBean1.setName(tfBadgeNameE.getText());
            badgeBean1.setIcon(new Image(badgeIconPathE));

            objectiveBean = new ObjectiveBean1();
            objectiveBean.setLevel(Levels.EXPERT);
            objectiveBean.setType(choiceBoxTypeE.getValue());
            objectiveBean.setDiscountBean(discountBean1);
            objectiveBean.setBadgeBean(badgeBean1);
            objectiveBean.setRequirement(Integer.parseInt(tfTargetNumberE.getText()));
            try {
                objectiveBean.setBadgeIconInputStream(new FileInputStream(badgeIconPathE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            objectiveBeanList.add(objectiveBean);
        }

        closeObjectives();
    }

    private void publishSeries() {
        System.out.println("Publishing series...");
        String seriesTitle = tfTitle.getText();
        Image chapterCover = ivComicCover.getImage();
        String description = taDescription.getText();
        Genres genre1 = choiceBoxGenre1.getValue();
        System.out.println("[P S Contr] Genre1 : "+genre1);
        Genres genre2 = choiceBoxGenre2.getValue();
        Genres genre3 = choiceBoxGenre3.getValue();

        //init seriesBean
        SeriesBean seriesBean1 = new SeriesBean1();

        try {
            seriesBean1.setCoverInputStream(new FileInputStream(imageCoverPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        seriesBean1.setTitle(seriesTitle);
        seriesBean1.setGenre1(genre1);
        seriesBean1.setGenre2(genre2);
        seriesBean1.setGenre3(genre3);


        PublishSeriesController publishSeriesController = new PublishSeriesController();
        publishSeriesController.publishSeries(seriesBean1,objectiveBeanList);
    }

    private boolean isThisObjectiveCompiled(Levels level){
        switch (level){
            case BEGINNER -> {
                return !(choiceBoxTypeB.getValue() == null) && !(tfDiscountPercB.getText() == null) && !(tfDiscountDaysB.getText() == null) && !(tfBadgeNameB.getText() == null) && !(tfTargetNumberB.getText() == null);
            }
            case INTERMEDIATE -> {
                return !(choiceBoxTypeI.getValue() == null) && !(tfDiscountPercI.getText() == null) && !(tfDiscountDaysI.getText() == null) && !(tfBadgeNameI.getText() == null) && !(tfTargetNumberI.getText() == null);
            }
            case EXPERT -> {
                return !(choiceBoxTypeE.getValue() == null) && !(tfDiscountPercE.getText() == null) && !(tfDiscountDaysE.getText() == null) && !(tfBadgeNameE.getText() == null) && !(tfTargetNumberE.getText() == null);
            }
            default -> {
                return false;
            }
        }
    }

    private void openObjectives() {
        objectivesSettingsPane.setVisible(true);
    }

    private void closeObjectives(){
        objectivesSettingsPane.setVisible(false);
    }

    private void changeCover() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.png,*.jpg","*.jpg","*.png"));
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



}
