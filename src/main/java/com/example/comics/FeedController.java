package com.example.comics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import tools.FxmlLoader;

public class FeedController {

    @FXML
    private Label lblHome;

    public void openSeries(ActionEvent actionEvent) {
        System.out.println("Clicked series");


    }

}
