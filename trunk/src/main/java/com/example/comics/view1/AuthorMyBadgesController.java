package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tools.FxmlLoader;

import java.io.IOException;
import java.net.URL;

public class AuthorMyBadgesController {

    @FXML
    private Button btnCreateBadge;

    @FXML
    private GridPane feedGrid;

    @FXML
    public void initialize() {
    }

    public void openBadgeCreator() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = BadgeCreatorControllerG.class.getResource("BadgeCreator.fxml");
        loader.setLocation(fxmlLocation);
        Pane view = loader.load();

        AuthorHomeControllerG.getInstance().changeCenter(view);
    }

}
