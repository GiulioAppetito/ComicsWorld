package com.example.comics;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class FeedControllerG {

    @FXML
    public Pane latestPane;
    @FXML
    private Label lblHome;

    public void initialize(){
        newBtn();
    }

    public void newBtn(){
        Button btn = new Button();
        btn.setOnAction(event ->

                System.out.println("Hi there! You clicked me!")

        );
    }



}
