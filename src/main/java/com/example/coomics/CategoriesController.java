package com.example.coomics;

import com.example.coomics.model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesController {


    private List<Category> categories;

    @FXML
    VBox categoryLayout;

    public void initialize() {

        //System.out.println("Initialize categories");
        categories = new ArrayList<>(add());

        for (Category category : categories) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("category.fxml"));
            try {
                HBox categoryBox = fxmlLoader.load();
                CategoryController categoryController = fxmlLoader.getController();
                categoryController.setData(category);
                categoryLayout.getChildren().add(categoryBox);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private List<Category> add() {

        List<Category> ls = new ArrayList<>();

        Category category = new Category();
        category.setName("Action");
        category.setIntial("A");
        category.setImageSrc(null);
        ls.add(category);

        Category category2 = new Category();
        category2.setName("Adventure");
        category2.setIntial("A");
        //comic2.setImageSrc(null);
        ls.add(category2);

        Category category3 = new Category();
        category3.setName("Biography");
        category3.setIntial("B");
        category3.setImageSrc(null);
        ls.add(category3);

        Category category9 = new Category();
        category9.setName("Comedy");
        category9.setIntial("C");
        //comic2.setImageSrc(null);
        ls.add(category9);

        Category categoryX = new Category();
        categoryX.setName("Crime");
        categoryX.setIntial("C");
        //comic2.setImageSrc(null);
        ls.add(categoryX);

        Category category11 = new Category();
        category11.setName("Drama");
        category11.setIntial("D");
        //comic2.setImageSrc(null);
        ls.add(category11);

        Category category12 = new Category();
        category12.setName("Fantasy");
        category12.setIntial("F");
        //comic2.setImageSrc(null);
        ls.add(category12);

        Category category4 = new Category();
        category4.setName("Manga");
        category4.setIntial("M");
        //comic2.setImageSrc(null);
        ls.add(category4);

        Category category5= new Category();
        category5.setName("Martial Arts");
        category5.setIntial("M");
        category5.setImageSrc(null);
        ls.add(category5);

        Category category6 = new Category();
        category6.setName("Science Fiction");
        category6.setIntial("S");
        //comic2.setImageSrc(null);
        ls.add(category6);

        Category category7 = new Category();
        category7.setName("Slice of life");
        category7.setIntial("S");
        category7.setImageSrc(null);
        ls.add(category7);

        Category category8 = new Category();
        category8.setName("Superhero");
        category8.setIntial("S");
        //comic2.setImageSrc(null);
        ls.add(category8);

        return ls;

    }
}
