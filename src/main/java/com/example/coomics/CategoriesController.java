package com.example.coomics;

import com.example.coomics.model.ComicsCategory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesController {



    @FXML
    private VBox boxA;

    @FXML
    private VBox boxB;

    @FXML
    private VBox boxC;

    @FXML
    private VBox boxD;

    @FXML
    private VBox boxE;

    @FXML
    private VBox boxF;

    @FXML
    private VBox boxG;

    @FXML
    private VBox boxH;

    @FXML
    private VBox boxI;
    @FXML
    private VBox boxJ;

    @FXML
    private VBox boxK;

    @FXML
    private VBox boxL;

    @FXML
    private VBox boxM;

    @FXML
    private VBox boxN;

    @FXML
    private VBox boxO;

    @FXML
    private VBox boxP;

    @FXML
    private VBox boxQ;

    @FXML
    private VBox boxR;

    @FXML
    private VBox boxS;

    @FXML
    private VBox boxT;

    @FXML
    private VBox boxU;

    @FXML
    private VBox boxV;

    @FXML
    private VBox boxW;

    @FXML
    private VBox boxX;

    @FXML
    private VBox boxY;

    @FXML
    private VBox boxZ;


    public void initialize() {

        //System.out.println("Initialize categories");
        List<ComicsCategory> categories = new ArrayList<>(add());

        for (ComicsCategory category : categories) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("category.fxml"));
            try {
                HBox categoryBox = fxmlLoader.load();
                CategoryController categoryController = fxmlLoader.getController();
                categoryController.setData(category);
                switch(category.getInitial()){
                    case "A":
                                boxA.getChildren().add(categoryBox);
                                break;
                    case "B":
                        boxB.getChildren().add(categoryBox);
                        break;
                    case "C":
                        boxC.getChildren().add(categoryBox);
                        break;
                    case "D":
                        boxD.getChildren().add(categoryBox);
                        break;
                    case "E":
                        boxE.getChildren().add(categoryBox);
                        break;
                    case "F":
                        boxF.getChildren().add(categoryBox);
                        break;
                    case "G":
                        boxG.getChildren().add(categoryBox);
                        break;

                    default:
                        break;


                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private List<ComicsCategory> add() {

        List<ComicsCategory> ls = new ArrayList<>();

        ComicsCategory category = new ComicsCategory();
        category.setName("Action");
        category.setIntial("A");
        //category.setImageSrc(null);
        ls.add(category);

        ComicsCategory category2 = new ComicsCategory();
        category2.setName("Adventure");
        category2.setIntial("A");
        //comic2.setImageSrc(null);
        ls.add(category2);

        ComicsCategory category3 = new ComicsCategory();
        category3.setName("Biography");
        category3.setIntial("B");
        //category3.setImageSrc(null);
        ls.add(category3);

        ComicsCategory category9 = new ComicsCategory();
        category9.setName("Comedy");
        category9.setIntial("C");
        //comic2.setImageSrc(null);
        ls.add(category9);

        ComicsCategory categoryX = new ComicsCategory();
        categoryX.setName("Crime");
        categoryX.setIntial("C");
        //comic2.setImageSrc(null);
        ls.add(categoryX);

        ComicsCategory category11 = new ComicsCategory();
        category11.setName("Drama");
        category11.setIntial("D");
        //comic2.setImageSrc(null);
        ls.add(category11);

        ComicsCategory category12 = new ComicsCategory();
        category12.setName("Fantasy");
        category12.setIntial("F");
        //comic2.setImageSrc(null);
        ls.add(category12);

        ComicsCategory category4 = new ComicsCategory();
        category4.setName("Manga");
        category4.setIntial("M");
        //comic2.setImageSrc(null);
        ls.add(category4);

        ComicsCategory category5= new ComicsCategory();
        category5.setName("Martial Arts");
        category5.setIntial("M");
        //category5.setImageSrc(null);
        ls.add(category5);

        ComicsCategory category6 = new ComicsCategory();
        category6.setName("Science Fiction");
        category6.setIntial("S");
        //comic2.setImageSrc(null);
        ls.add(category6);

        ComicsCategory category7 = new ComicsCategory();
        category7.setName("Slice of life");
        category7.setIntial("S");
        //category7.setImageSrc(null);
        ls.add(category7);

        ComicsCategory category8 = new ComicsCategory();
        category8.setName("Superhero");
        category8.setIntial("S");
        //comic2.setImageSrc(null);
        ls.add(category8);

        return ls;

    }
}
