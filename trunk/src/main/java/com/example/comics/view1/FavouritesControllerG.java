package com.example.comics.view1;

import javafx.fxml.FXML;
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
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favouritecomics");
        mainPane.setCenter(view);


    }

    @FXML
    private void switchToAuthors() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favouriteauthors");
        mainPane.setCenter(view);
        System.out.println("FAV CONTR G] Clicked authors.");
    }

    @FXML
    private void switchToCharacters() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favouritecharacters");
        mainPane.setCenter(view);
    }

}
