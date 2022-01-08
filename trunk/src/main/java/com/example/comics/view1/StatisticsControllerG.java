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

    @FXML
    public void openStatisticsRating() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("statisticsrating");
        mainPane.setCenter(view);
    }

    @FXML
    public void openStatisticsFollowers() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("statisticsfollowers");
        mainPane.setCenter(view);
    }
}
