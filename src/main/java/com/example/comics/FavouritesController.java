package com.example.comics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tools.FxmlLoader;

public class FavouritesController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox settingsBox;

    @FXML
    private Button btnComics;

    @FXML
    private Button btnAuthors;

    @FXML
    private Button btnCharacters;



    @FXML
    void switchToComics(ActionEvent event) {

        System.out.println("Clicked comics");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favouritecomics");
        mainPane.setCenter(view);
    }

    @FXML
    void switchToAuthors(ActionEvent event) {

        System.out.println("Clicked comics");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favouriteauthors");
        mainPane.setCenter(view);
    }

    @FXML
    void switchToCharacters(ActionEvent event) {

        System.out.println("Clicked comics");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favouritecharacters");
        mainPane.setCenter(view);
    }



}