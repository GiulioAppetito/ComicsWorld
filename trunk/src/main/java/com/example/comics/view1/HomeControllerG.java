package com.example.comics.view1;

import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import tools.FxmlLoader;

import java.io.IOException;
import java.net.URL;

public abstract class HomeControllerG {

    private static final String FEED = "feed";

    private static URL fxmlHomeLocation = null;

    public abstract void init();

    public abstract void changeCenter(Pane pane);

    public abstract void resetButtons();

    public void openFeed(){
        FeedControllerG feedControllerG = FeedControllerG.getInstance();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = HomeControllerG.class.getResource(FEED + ".fxml");
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

    public void openSeries(SeriesBean seriesBean){
        SeriesControllerG serieControllerG = new SeriesControllerG();

        URL fxmlLocation = SeriesControllerG.class.getResource("serie.fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fxmlLocation);
        loader.setController(serieControllerG);

        HomeControllerG homeControllerG = HomeFactory.getHomeControllerG();
        try {
            homeControllerG.changeCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        serieControllerG.setData(seriesBean);
    }

    public URL getLocation(){
        return fxmlHomeLocation;
    }

    public void setLocation(URL location){
        this.fxmlHomeLocation = location;
    }
}
