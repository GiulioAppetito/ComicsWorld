package com.example.comics.view1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tools.FxmlLoader;

public class FavouritesControllerG {

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
    public void initialize(){
        switchToComics();
    }

    @FXML
    private void switchToComics() {

        System.out.println("Clicked comics");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favouritecomics");
        mainPane.setCenter(view);
    }

    @FXML
    private void switchToAuthors() {

        System.out.println("Clicked comics");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favouriteauthors");
        mainPane.setCenter(view);
    }

    @FXML
    private void switchToCharacters() {

        System.out.println("Clicked comics");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favouritecharacters");
        mainPane.setCenter(view);
    }

}
