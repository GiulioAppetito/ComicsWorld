package com.example.comics.view1;

import com.example.comics.controller.PublishSeriesController;
import com.example.comics.model.Genres;
import com.example.comics.model.Levels;
import com.example.comics.model.exceptions.AlreadyExistingSeriesException;
import com.example.comics.model.fagioli.*;
import com.example.comics.view1.beans.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
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
    private Label lblPublishResult;

    @FXML
    private Pane publishPane;

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
    private Button btnClosePublishPane;

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

    private static final String CHAPTERS = "CHAPTERS";
    private static final String REVIEWS = "REVIEWS";
    private static final String PNG = "*.png";

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

        btnClosePublishPane.setOnAction(event -> closePublishPane());


        btnPublishSeries.setOnAction(event -> publishSeries());

        choiceBoxGenre1.getItems().setAll(Genres.values());
        choiceBoxGenre2.getItems().setAll(Genres.values());
        choiceBoxGenre3.getItems().setAll(Genres.values());
        choiceBoxTypeB.getItems().setAll(REVIEWS,CHAPTERS);
        choiceBoxTypeI.getItems().setAll(REVIEWS,CHAPTERS);
        choiceBoxTypeE.getItems().setAll(REVIEWS,CHAPTERS);

        closePublishPane();

    }

    private void closePublishPane() {
        publishPane.setVisible(false);
    }

    public void changeIcon(String choice){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(PNG,PNG));
        File f = fc.showOpenDialog(null);
        if(f!=null){
            switch (choice) {
                case "I" -> badgeIconPathI = f.getAbsolutePath();
                case "B" -> badgeIconPathB = f.getAbsolutePath();
                default -> badgeIconPathE = f.getAbsolutePath();
            }
        }
    }

    private void changeBagdeIconI() {
        changeIcon("I");
    }

    private void changeBagdeIconB() {
       changeIcon("B");
    }

    private void changeBagdeIconE() {
        changeIcon("E");
    }

    private void applyObjectives() {
        objectiveBeanList.clear();

        DiscountBean1 discountBean1;
        BadgeBean1 badgeBean1;
        ObjectiveBean1 objectiveBean;

        if(isThisObjectiveCompiled(Levels.BEGINNER)){

            discountBean1 = new DiscountBean1();
            discountBean1.setPercentage(Float.parseFloat(tfDiscountPercB.getText()));
            discountBean1.setLimitDays(Integer.parseInt(tfDiscountDaysB.getText()));

            badgeBean1 = new BadgeBean1();
            badgeBean1.setName(tfBadgeNameB.getText());
            badgeBean1.setIcon(new Image(badgeIconPathB));

            objectiveBean = new ObjectiveBean1();
            objectiveBean.setLevel(Levels.BEGINNER);
            objectiveBean.setType(choiceBoxTypeB.getValue());
            objectiveBean.setRequirement(Float.parseFloat(tfTargetNumberB.getText()));

            objectiveBean.setDiscountBean(discountBean1);
            objectiveBean.setBadgeBean(badgeBean1);
            objectiveBeanList.add(objectiveBean);
            try {
                objectiveBean.setBadgeIconInputStream(new FileInputStream(badgeIconPathB));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(isThisObjectiveCompiled(Levels.INTERMEDIATE)){
            discountBean1 = new DiscountBean1();
            discountBean1.setPercentage(Float.parseFloat(tfDiscountPercI.getText()));
            discountBean1.setLimitDays(Integer.parseInt(tfDiscountDaysI.getText()));

            badgeBean1 = new BadgeBean1();
            badgeBean1.setName(tfBadgeNameI.getText());
            badgeBean1.setIcon(new Image(badgeIconPathI));

            objectiveBean = new ObjectiveBean1();
            objectiveBean.setLevel(Levels.INTERMEDIATE);
            objectiveBean.setType(choiceBoxTypeI.getValue());
            objectiveBean.setRequirement(Float.parseFloat(tfTargetNumberI.getText()));

            objectiveBean.setDiscountBean(discountBean1);
            objectiveBean.setBadgeBean(badgeBean1);
            objectiveBeanList.add(objectiveBean);
            try {
                objectiveBean.setBadgeIconInputStream(new FileInputStream(badgeIconPathI));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        if(isThisObjectiveCompiled(Levels.EXPERT)){
            discountBean1 = new DiscountBean1();
            discountBean1.setPercentage(Float.parseFloat(tfDiscountPercE.getText()));
            discountBean1.setLimitDays(Integer.parseInt(tfDiscountDaysE.getText()));

            badgeBean1 = new BadgeBean1();
            badgeBean1.setName(tfBadgeNameE.getText());
            badgeBean1.setIcon(new Image(badgeIconPathE));

            objectiveBean = new ObjectiveBean1();
            objectiveBean.setLevel(Levels.EXPERT);
            objectiveBean.setType(choiceBoxTypeE.getValue());
            objectiveBean.setRequirement(Float.parseFloat(tfTargetNumberE.getText()));

            objectiveBean.setDiscountBean(discountBean1);
            objectiveBean.setBadgeBean(badgeBean1);
            objectiveBeanList.add(objectiveBean);
            try {
                objectiveBean.setBadgeIconInputStream(new FileInputStream(badgeIconPathE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        closeObjectives();
    }

    private void publishSeries() {
        String seriesTitle = tfTitle.getText();
        Genres genre1 = choiceBoxGenre1.getValue();
        Genres genre2 = choiceBoxGenre2.getValue();
        Genres genre3 = choiceBoxGenre3.getValue();

        //init seriesBean
        SeriesBean1 seriesBean1 = new SeriesBean1();

        try {
            seriesBean1.setCoverInputStream(new FileInputStream(imageCoverPath));
            seriesBean1.setTitle(seriesTitle);
            seriesBean1.setGenre1(genre1);
            seriesBean1.setGenre2(genre2);
            seriesBean1.setGenre3(genre3);
            seriesBean1.setDescription(taDescription.getText());
            PublishSeriesController publishSeriesController = new PublishSeriesController();
            publishSeriesController.publishSeries(seriesBean1,objectiveBeanList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (AlreadyExistingSeriesException e) {
            publishPane.setVisible(true);
            lblPublishResult.setText(e.getMessage());
            tfTitle.setText("");
        }
    }

    private boolean isThisObjectiveCompiled(Levels level){
        switch (level){
            case BEGINNER -> {
                return (choiceBoxTypeB.getValue() != null) && !(tfDiscountPercB.getText().equals("")) && !(tfDiscountDaysB.getText().equals("")) && !(tfBadgeNameB.getText().equals("")) && !(tfTargetNumberB.getText().equals(""));
            }
            case INTERMEDIATE -> {
                return (choiceBoxTypeI.getValue() != null) && !(tfDiscountPercI.getText().equals("")) && !(tfDiscountDaysI.getText().equals("")) && !(tfBadgeNameI.getText().equals("")) && !(tfTargetNumberI.getText().equals(""));
            }
            case EXPERT -> {
                return (choiceBoxTypeE.getValue() != null) && !(tfDiscountPercE.getText().equals("")) && !(tfDiscountDaysE.getText().equals("")) && !(tfBadgeNameE.getText().equals("")) && !(tfTargetNumberE.getText().equals(""));
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
        InputStream inputStream = null;
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(PNG,"*.jpg","*.jpg",PNG));
        File file = fc.showOpenDialog(null);
        if(file!=null){
            imageCoverPath = file.getAbsolutePath();
            try {
                inputStream = new FileInputStream(imageCoverPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Image cover = new Image(inputStream);
        ivComicCover.setImage(cover);

    }



}
