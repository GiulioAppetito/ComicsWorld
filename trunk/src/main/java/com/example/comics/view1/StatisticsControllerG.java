package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tools.FxmlLoader;

public class StatisticsControllerG {

    @FXML
    private Button btnRatings;

    @FXML
    private Button btnReadings;

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox settingsBox;

    private static final String STYLE = ".button2";

    public void initialize(){
        openStatisticsRating();
    }

    @FXML
    public void openStatisticsRating() {
        btnRatings.setStyle("-fx-background-color : #5DADE2");
        btnReadings.setStyle(STYLE);
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("statisticsrating");
        mainPane.setCenter(view);
    }

    @FXML
    public void openStatisticsFollowers() {
        btnReadings.setStyle("-fx-background-color : #5DADE2");
        btnRatings.setStyle(STYLE);
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("statisticsfollowers");
        mainPane.setCenter(view);
    }
}
