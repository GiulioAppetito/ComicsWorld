package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.fagioli.AuthorBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FollowingControllerG {

    @FXML
    private GridPane gpFollowing;

    @FXML
    public void initialize(){
        ResearchController researchController = new ResearchController();
        List<AuthorBean> listOfCards = researchController.getFollowedAuthors();
        int size = listOfCards.size();
        int columns = 3;
        int i=1;
        for(int j=0; j<size; j++) {
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
                followedAuthorsControllerG.setData(listOfCards.get(j));
                gpFollowing.add(card,j%columns,i);
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

        URL fxmlLocation = FollowingControllerG.class.getResource("authorfromoutside.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(authorFromOutsideControllerG);

        HomeControllerG homeControllerG = HomeFactory.getHomeControllerG();
        homeControllerG.changeCenter(loader.load());

        authorFromOutsideControllerG.init(authorBean);

    }

}