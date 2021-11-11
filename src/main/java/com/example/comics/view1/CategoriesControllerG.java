package com.example.comics.view1;

import com.example.comics.model.Categories;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CategoriesControllerG {

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


    public static CategoriesControllerG instance;

    private CategoriesControllerG(){
    }

    public static synchronized CategoriesControllerG getInstance(){

        if(instance==null){
            instance = new CategoriesControllerG();
        }
        return instance;

    }


    public void init() {

        for (Categories category : Categories.values()) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("categorycard.fxml"));
            try {
                HBox categoryBox = fxmlLoader.load();
                categoryBox.setOnMouseClicked(event -> openCategory());
                String categoryName = category.name();

                CategoryCardController categoryCardController = fxmlLoader.getController();
                categoryCardController.setData(categoryName);

                switch (categoryName.substring(0,1)) {
                    case "A" -> boxA.getChildren().add(categoryBox);
                    case "B" -> boxB.getChildren().add(categoryBox);
                    case "C" -> boxC.getChildren().add(categoryBox);
                    case "D" -> boxD.getChildren().add(categoryBox);
                    case "E" -> boxE.getChildren().add(categoryBox);
                    case "F" -> boxF.getChildren().add(categoryBox);
                    case "G" -> boxG.getChildren().add(categoryBox);
                    case "H" -> boxH.getChildren().add(categoryBox);
                    case "I" -> boxI.getChildren().add(categoryBox);
                    case "J" -> boxJ.getChildren().add(categoryBox);
                    case "K" -> boxK.getChildren().add(categoryBox);
                    case "L" -> boxL.getChildren().add(categoryBox);
                    case "M" -> boxM.getChildren().add(categoryBox);
                    case "N" -> boxN.getChildren().add(categoryBox);
                    case "O" -> boxO.getChildren().add(categoryBox);
                    case "P" -> boxP.getChildren().add(categoryBox);
                    case "Q" -> boxQ.getChildren().add(categoryBox);
                    case "R" -> boxR.getChildren().add(categoryBox);
                    case "S" -> boxS.getChildren().add(categoryBox);
                    case "T" -> boxT.getChildren().add(categoryBox);
                    case "U" -> boxU.getChildren().add(categoryBox);
                    case "V" -> boxV.getChildren().add(categoryBox);
                    case "W" -> boxW.getChildren().add(categoryBox);
                    case "X" -> boxX.getChildren().add(categoryBox);
                    case "Y" -> boxY.getChildren().add(categoryBox);
                    case "Z" -> boxZ.getChildren().add(categoryBox);
                    default -> {
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void openCategory(){

    }

}
