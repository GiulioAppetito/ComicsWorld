package com.example.comics.view1;

import com.example.comics.controller.FavouritesController;
import com.example.comics.controller.ToReadController;
import com.example.comics.model.UserLogin;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SeriesControllerG {

    @FXML
    public Button btnBack;

    @FXML
    public Button btnAddToFav;

    @FXML
    private ButtonBar buttonBarAuthor;

    @FXML
    public Button btnAddToRead;

    @FXML
    private ImageView comicCover;

    @FXML
    private VBox vbChapters;

    @FXML
    private Button btnAuthor;

    @FXML
    private TextArea taDescription;

    @FXML
    private Label lblTitle;

    private static final String STYLE1 = ".button2";
    private static final String STYLE2 = "-fx-background-color: #5DADE2; -fx-background-radius: 20";

    public void setData(SeriesBean seriesBean) {

        btnAuthor.setText(seriesBean.getAuthor().getUsername());
        lblTitle.setText(seriesBean.getTitle());
        comicCover.setImage(seriesBean.getCover());
        btnAuthor.setText(seriesBean.getAuthor().getUsername());
        taDescription.setText(seriesBean.getDescription());

        btnAuthor.setOnAction(event -> openAuthor(seriesBean));
        List<ChapterBean> listOfChapters = seriesBean.getChapters();

        for (ChapterBean chapterBean : listOfChapters) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("chapterItem.fxml"));

            try {
                VBox vbChapter = fxmlLoader.load();
                ChapterItemControllerG chapterControllerItem = fxmlLoader.getController();
                chapterControllerItem.setData(chapterBean.getTitle(),seriesBean.getTitle(),chapterBean.getCover());

                vbChapter.setOnMouseClicked(event -> {
                    try {
                        openChapter(chapterBean, seriesBean);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                vbChapters.getChildren().add(vbChapter);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(UserLogin.getInstance().getAccount().getRole().equals("author")) {

            FavouritesController favouritesController = new FavouritesController();
            if (favouritesController.isSeriesFavourite(seriesBean)) {
                btnAddToFav.setStyle(STYLE2);
                btnAddToFav.setOnAction(event -> removeFromFavourites(seriesBean));
                btnAddToFav.setText("Remove from fav");

            } else {
                btnAddToFav.setStyle(STYLE1);
                btnAddToFav.setOnAction(event -> addSeriesToFavourites(seriesBean));
                btnAddToFav.setText("Add to favourites");
            }

            ToReadController toReadController = new ToReadController();
            if (toReadController.isSeriesAddedToRead(seriesBean)) {
                btnAddToRead.setStyle(STYLE2);
                btnAddToRead.setOnAction(event -> removeSeriesFromToRead(seriesBean));
                btnAddToRead.setText("Remove from toRead");
            } else {
                btnAddToRead.setStyle(STYLE1);
                btnAddToRead.setOnAction(event -> addSeriesToToRead(seriesBean));
                btnAddToRead.setText("Add to toRead");
            }

            btnBack.setOnAction(event -> {
                HomeFactory homeFactory = new HomeFactory();
                HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
                homeControllerG.openFeed();
            });
        }else{
            btnAddToRead.setVisible(false);
            btnAddToFav.setVisible(false);
        }

    }

    private void openAuthor(SeriesBean seriesBean) {
        AuthorFromOutsideControllerG authorFromOutsideControllerG = new AuthorFromOutsideControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = AuthorFromOutsideControllerG.class.getResource("authorfromoutside.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(authorFromOutsideControllerG);

        HomeFactory homeFactory = new HomeFactory();

        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        try {
            homeControllerG.changeCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        authorFromOutsideControllerG.init(seriesBean.getAuthor());
    }

    private void removeSeriesFromToRead(SeriesBean seriesBean) {
        ToReadController toReadController = new ToReadController();
        toReadController.removeSeriesFromToRead(seriesBean);
        btnAddToRead.setOnAction(event -> addSeriesToToRead(seriesBean));
        btnAddToRead.setStyle(STYLE1);
        btnAddToRead.setText("Add to toRead");

    }

    private void addSeriesToToRead(SeriesBean seriesBean) {
        ToReadController toReadController = new ToReadController();
        toReadController.addSeriesToToRead(seriesBean);
        btnAddToRead.setOnAction(event -> removeSeriesFromToRead(seriesBean));
        btnAddToRead.setStyle(STYLE2);
        btnAddToRead.setText("Remove from toRead");

    }

    private void addSeriesToFavourites(SeriesBean seriesBean) {
        FavouritesController favouritesController = new FavouritesController();
        favouritesController.addSeriesToFavourites(seriesBean);
        btnAddToFav.setOnAction(event -> removeFromFavourites(seriesBean));
        btnAddToFav.setStyle(STYLE2);
        btnAddToFav.setText("Remove from fav");
    }

    private void removeFromFavourites(SeriesBean seriesBean) {
        FavouritesController favouritesController = new FavouritesController();
        favouritesController.removeSeriesFromFavourites(seriesBean);
        btnAddToFav.setOnAction(event -> addSeriesToFavourites(seriesBean));
        btnAddToFav.setStyle(STYLE1);
        btnAddToFav.setText("Add to favourites");
    }

    private void openChapter(ChapterBean chapterBean, SeriesBean seriesBean) throws IOException {

        ChapterControllerG chapterControllerG = new ChapterControllerG();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = ChapterControllerG.class.getResource("chapter.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(chapterControllerG);

        HomeFactory homeFactory = new HomeFactory();

        HomeControllerG homeControllerG = homeFactory.getHomeControllerG();
        homeControllerG.changeCenter(loader.load());

        chapterControllerG.init(chapterBean, seriesBean);

    }


}