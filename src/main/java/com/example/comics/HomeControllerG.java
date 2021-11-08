package com.example.comics;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import com.example.comics.model.Comic;
import tools.FxmlLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeControllerG {

    @FXML
    private HBox userBox;
    @FXML
    private HBox settingsBox;
    @FXML
    private BorderPane mainPane;
    @FXML
    private GridPane homeGrid;

    @FXML
    private Button btnCategories;

    @FXML
    private Button btnFav;

    @FXML
    private Button btnReading;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnToRead;

    @FXML
    private Button btnTop;

    private List<Comic> listOfCards;


    public static HomeControllerG instance;


    private HomeControllerG(){

    }

    public static HomeControllerG getInstance(){
        if(instance==null){
            instance = new HomeControllerG();
        }
        return instance;
    }

    public void initialize() {
        openFeed();
    }

    /* ex initizialize:
    listOfCards = new ArrayList<>(add());

        for(int i=0;i<6;i++) {

            for(int j=0; j<listOfCards.size(); j++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card2.fxml"));
                /*
                try {
                    //VBox cardBox = fxmlLoader.load();
                    //Card2Controller card2Controller = fxmlLoader.getController();
                    //card2Controller.setData(listOfCards.get(i));
                    //homeGrid.getChildren().add(cardBox);
                    //homeGrid.add(cardBox, i, j);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
     */

    public void changeCenter(Pane pane){
        mainPane.setCenter(pane);
    }

    private List<Comic> add(){

        List<Comic> ls = new ArrayList<>();

        Comic comic = new Comic();
        comic.setName("Spiderman");
        comic.setAuthor("Stan Lee");
        //comic.setImageSrc(null);
        ls.add(comic);

        Comic comic2 = new Comic();
        comic2.setName("Superman");
        comic2.setAuthor("Stan Lee");
        //comic2.setImageSrc(null);
        ls.add(comic2);

        Comic comic3 = new Comic();
        comic3.setName("Spiderman");
        comic3.setAuthor("Stan Lee");
        //comic3.setImageSrc(null);
        ls.add(comic3);

        Comic comic4 = new Comic();
        comic4.setName("Superman");
        comic4.setAuthor("Stan Lee");
        //comic4.setImageSrc(null);
        ls.add(comic4);

        Comic comic5= new Comic();
        comic5.setName("Spiderman");
        comic5.setAuthor("Stan Lee");
        //comic5.setImageSrc(null);
        ls.add(comic5);

        Comic comic6 = new Comic();
        comic6.setName("Superman");
        comic6.setAuthor("Stan Lee");
        //comic6.setImageSrc(null);
        ls.add(comic6);

        Comic comic7 = new Comic();
        comic7.setName("Spiderman");
        comic7.setAuthor("Stan Lee");
        //comic7.setImageSrc(null);
        ls.add(comic7);

        Comic comic8 = new Comic();
        comic8.setName("Superman");
        comic8.setAuthor("Stan Lee");
        //comic8.setImageSrc(null);
        ls.add(comic8);

        return ls;

    }


    public void openProfile() {
        System.out.println("Clicked profile");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("profile");
        mainPane.setCenter(view);

        btnSettings.setStyle("-fx-background-color: #E2E2E2; ");
        btnCategories.setStyle("-fx-background-color: #E2E2E2; ");
        btnFav.setStyle("-fx-background-color: #E2E2E2; ");
        btnReading.setStyle("-fx-background-color: #E2E2E2; ");
        btnTop.setStyle("-fx-background-color: #E2E2E2; ");
        btnToRead.setStyle("-fx-background-color: #E2E2E2; ");
    }

    public void openCharacter() {
        System.out.println("Clicked character");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("character");
        mainPane.setCenter(view);
    }

    public void openSettings() {
        System.out.println("Clicked settings");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("settings");
        mainPane.setCenter(view);

        btnSettings.setStyle("-fx-background-color: #5DADE2; ");
        btnCategories.setStyle("-fx-background-color: #E2E2E2; ");
        btnFav.setStyle("-fx-background-color: #E2E2E2; ");
        btnReading.setStyle("-fx-background-color: #E2E2E2; ");
        btnTop.setStyle("-fx-background-color: #E2E2E2; ");
        btnToRead.setStyle("-fx-background-color: #E2E2E2; ");

    }

    public void openCategories() {
        System.out.println("Clicked btn1");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("categories");
        mainPane.setCenter(view);

        btnSettings.setStyle("-fx-background-color: #E2E2E2; ");
        btnCategories.setStyle("-fx-background-color: #5DADE2; ");
        btnFav.setStyle("-fx-background-color: #E2E2E2; ");
        btnReading.setStyle("-fx-background-color: #E2E2E2; ");
        btnTop.setStyle("-fx-background-color: #E2E2E2; ");
        btnToRead.setStyle("-fx-background-color: #E2E2E2; ");
    }

    public void openFavourites() {
        System.out.println("Clicked favourites");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("favourites");
        mainPane.setCenter(view);

        btnSettings.setStyle("-fx-background-color: #E2E2E2; ");
        btnCategories.setStyle("-fx-background-color: #E2E2E2; ");
        btnFav.setStyle("-fx-background-color: #5DADE2; ");
        btnReading.setStyle("-fx-background-color: #E2E2E2; ");
        btnTop.setStyle("-fx-background-color: #E2E2E2; ");
        btnToRead.setStyle("-fx-background-color: #E2E2E2; ");
    }

    public void openFeed() {
        System.out.println("Clicked feed");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("feed");
        mainPane.setCenter(view);

        btnSettings.setStyle("-fx-background-color: #E2E2E2; ");
        btnCategories.setStyle("-fx-background-color: #E2E2E2; ");
        btnFav.setStyle("-fx-background-color: #E2E2E2; ");
        btnReading.setStyle("-fx-background-color: #E2E2E2; ");
        btnTop.setStyle("-fx-background-color: #E2E2E2; ");
        btnToRead.setStyle("-fx-background-color: #E2E2E2; ");
    }


}