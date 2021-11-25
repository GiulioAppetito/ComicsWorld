package com.example.comics.view1;

import com.example.comics.model.Badge;
import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderProfileController {


    @FXML
    public Button btnEdit;
    @FXML
    public Button btnConverter;
    @FXML
    public Label lblName;
    @FXML
    public Label lblUsername;
    @FXML
    public GridPane gpBadges;

    @FXML
    public void initialize() {
        lblName.setText(UserLogin.getAccount().getFirstName());
        lblUsername.setText(UserLogin.getAccount().getLastName());

        List<Badge>listOfBadges = new ArrayList<>(add());
        int rows = listOfBadges.size() / 4;

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < 2; j++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("badge.fxml"));
                try {
                    VBox card = fxmlLoader.load();
                    gpBadges.add(card, j, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Badge> add() {

        List<Badge> lb = new ArrayList<>();

        int numBadges = 17;
        int i;

        for(i=0;i<numBadges;i++){
            Badge badge = new Badge();
            badge.setName("Spiderman");
            lb.add(badge);
        }

        return lb;

    }

    public void edit(){}

}