package com.example.comics.view1;

import com.example.comics.model.Character;
import com.example.comics.model.Series;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FavouriteCharactersControllerG {

    @FXML
    private GridPane gpFavCharacters;

    public void initialize(){

        List<Character> listOfCards = new ArrayList<>(add());
        int size = listOfCards.size();
        int columns = 3;
        int i=1;
        for(int j=0; j<size; j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("charactercard.fxml"));
            try {
                VBox card = fxmlLoader.load();
                /*VCardController cardController = fxmlLoader.getController();
                cardController.setData(listOfCards.get(j).getName());

                card.setOnMouseClicked(event -> {
                    try {
                        openSerie();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
*/
                gpFavCharacters.add(card,j%columns,i);
                if(j%columns == columns-1){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private List<Character> add(){

        List<Character> lc = new ArrayList<>();

        int dummyNumCharacters = 7;
        int i;

        for(i=0;i<dummyNumCharacters;i++){
            Character character = new Character();
            //dummy init
            lc.add(character);

        }
        return lc;
    }

    public void openSerie() throws IOException {

        SerieController serieController = new SerieController();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = CharacterControllerG.class.getResource("serie.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(serieController);

        HomeControllerG homeControllerG = HomeControllerG.getInstance();
        homeControllerG.changeCenter(loader.load());

        serieController.init();

    }
}
