package com.example.comics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tools.FxmlLoader;

import java.io.IOException;
import java.net.URL;

public class FeedControllerG {

    @FXML
    public Pane latestPane;
    @FXML
    private Label lblHome;
    @FXML
    private Button btnTest;

    public void initialize(){
        btnTest.setOnAction(event ->

                {
                    try {
                        openCharacter();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        );
    }

    //non va rip

    //non lho provato dinamico perchè non vedo sto bottone dannazione
    public void newBtn(){
        Button btn = new Button();
        btn.setPrefHeight(100);
        btn.setPrefWidth(100);
        btn.setStyle("-fx-background-color: #ff0000; ");

        btn.setVisible(true);
        btn.setOnAction(event ->

                {
                    try {
                        openCharacter();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        );
        latestPane.getChildren().add(btn);
    }

    private void openCharacter() throws IOException {
        CharacterControllerG characterControllerG = new CharacterControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("character.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(characterControllerG);

        System.out.println("Clicked character");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("profile");

        HomeControllerG homeControllerG = HomeControllerG.getInstance();
        homeControllerG.changeCenter(view);
    }



}
