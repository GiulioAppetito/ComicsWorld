package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.SeriesBean;
import com.example.comics.model.Author;
import com.example.comics.model.Series;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FavouriteAuthorsControllerG {

    @FXML
    private GridPane gpFavAuthors;

    @FXML
    public void initialize(){
        ResearchController researchController = new ResearchController();
        List<AuthorBean> listOfCards = researchController.getFollowedAuthors(UserLogin.getInstance().getReader());
        int size = listOfCards.size();
        int columns = 3;
        int i=1;
        for(int j=0; j<size; j++) {
            System.out.println("[FAV AUTH CONTR G] NEl ciclo for : "+listOfCards.get(j).getUsername());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("authorcard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                int finalJ = j;
                card.setOnMouseClicked(event -> {
                    try {
                        openAuthor(listOfCards.get(finalJ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                FollowedAuthorCardControllerG followedAuthorsControllerG= fxmlLoader.getController();
                followedAuthorsControllerG.setData(listOfCards.get(j).getProPic(),listOfCards.get(j).getUsername());
                gpFavAuthors.add(card,j%columns,i);
                if(j%columns == columns-1){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void openAuthor(AuthorBean authorBean) throws IOException {
        AuthorFromOutsideControllerG authorFromOutsideControllerG = new AuthorFromOutsideControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("authorfromoutside.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(authorFromOutsideControllerG);

        HomeFactory homeFactory = new HomeFactory();
        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.changeCenter(loader.load());

        authorFromOutsideControllerG.init(authorBean);

    }



    public void openSerie(SeriesBean seriesBean) throws IOException {

        SeriesControllerG serieController = new SeriesControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("serie.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(serieController);

        HomeFactory homeFactory = new HomeFactory();
        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.changeCenter(loader.load());

        serieController.setData(seriesBean);

    }

}
