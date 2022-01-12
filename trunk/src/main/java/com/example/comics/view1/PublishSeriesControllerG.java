package com.example.comics.view1;

import com.example.comics.model.Genres;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class PublishSeriesControllerG {

    @FXML
    private Button btnBack;

    @FXML
    private ImageView ivComicCover;

    @FXML
    private TextField tfTitle;

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

    public void initialize(){
        closeObjectives();
        btnApplyObjectives.setOnAction(actionEvent -> closeObjectives());
        btnCloseObjectives.setOnAction(actionEvent -> closeObjectives());
        btnAddObjectives.setOnAction(actionEvent -> openObjectives());
        choiceBoxGenre1.getItems().setAll(Genres.values());
        choiceBoxGenre2.getItems().setAll(Genres.values());
        choiceBoxGenre3.getItems().setAll(Genres.values());
        choiceBoxTypeB.getItems().setAll("REVIEWS","CHAPTERS");
        choiceBoxTypeI.getItems().setAll("REVIEWS","CHAPTERS");
        choiceBoxTypeE.getItems().setAll("REVIEWS","CHAPTERS");
    }

    private void openObjectives() {
        objectivesSettingsPane.setVisible(true);
    }

    private void closeObjectives(){
        objectivesSettingsPane.setVisible(false);
    }



}
