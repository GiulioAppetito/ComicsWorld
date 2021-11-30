package com.example.comics.view1;

import com.example.comics.model.Badge;
import com.example.comics.model.Character;
import com.example.comics.model.UserLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderProfileControllerG {


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
    private GridPane gpCharacter;

    @FXML
    public void initialize() {
        lblName.setText(UserLogin.getAccount().getFirstName());
        lblUsername.setText(UserLogin.getAccount().getLastName());

        loadBadges();
        loadFavouriteCharacter();

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

    public void loadBadges(){
        List<Badge>listOfBadges = new ArrayList<>(add());
        int columns = 3;
        int i=1;
        int dummyNumBadges = listOfBadges.size();

        for (int j = 0; j < dummyNumBadges; j++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("badge.fxml"));
            try {
                VBox card = fxmlLoader.load();
                gpBadges.add(card, j%columns, i);
                if(j%columns == columns -1 ){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadFavouriteCharacter(){
        Character favouriteCharacter = new Character();
        //dummy init
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("favouritecharacteritem.fxml"));
        try {
            VBox card = fxmlLoader.load();
            gpCharacter.add(card, 0, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit(){}

}