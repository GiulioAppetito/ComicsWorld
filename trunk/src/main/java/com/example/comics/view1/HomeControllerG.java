package com.example.comics.view1;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import tools.FxmlLoader;

import java.io.IOException;
import java.net.URL;

public abstract class HomeControllerG {

    public abstract void changeCenter(Pane pane);

    public abstract void resetButtons();

    public void openFeed(){
        FeedControllerG feedControllerG = FeedControllerG.getInstance();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = HomeControllerG.class.getResource("feed.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(feedControllerG);

        Pane view = null;
        try {
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        feedControllerG.init();

        changeCenter(view);

        resetButtons();
    }

    public void openSettings() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("settings");

        changeCenter(view);
        resetButtons();
    }

    public void openCategories() throws IOException {
        CategoriesControllerG categoriesControllerG = new CategoriesControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = HomeControllerG.class.getResource("categories.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(categoriesControllerG);

        Pane view = loader.load();
        categoriesControllerG.init();

        changeCenter(view);

        resetButtons();
    }

}
