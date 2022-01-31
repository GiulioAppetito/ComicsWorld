package com.example.comics.fakeamazon;

import com.example.comics.controller.BuyComicController;
import com.example.comics.controller.boundaries.BuyComicBoundary;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class FakeAmazonControllerG {

    @FXML
    private Button btnBuy;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnCloseErrorPane;

    @FXML
    private Pane errorPane;

    @FXML
    private ImageView ivComicCover;

    @FXML
    private Label lblChapter;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblSeries;

    @FXML
    private TextField tfDiscountCode;

    @FXML
    private TextField tfCard;

    
    @FXML
    public void initialize(ChapterBean chapterBean, SeriesBean seriesBean){
        //finto stage di amazon
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        URL fxmlLocation = BuyComicBoundary.class.getResource("fakeamazon.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(this);

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("FakeAmazon");
        stage.setScene(scene);
        stage.show();

        if(chapterBean.getCover()!=null){
            ivComicCover.setImage(chapterBean.getCover());
        }
        errorPane.setVisible(false);
        lblChapter.setText(chapterBean.getTitle());
        lblSeries.setText(seriesBean.getTitle());
        btnBuy.setOnAction(event -> buy());
        tfDiscountCode.setOnMouseClicked(event -> clear());
        //dummy
        lblPrice.setText(FakeAmazon.getPrice());

        btnCloseErrorPane.setOnAction(event -> closeErrorPane());
    }

    public void buy() {

        if(tfCard.getText().equals("")){
            //segnala che mancano i dati
            return;
        }else{
            //dummy verify if the card number is ok
            //if ok go on
        }

        FakeAmazon fakeAmazon = new FakeAmazon();
        String discountCode = tfDiscountCode.getText();
        if(discountCode==""){
            //dummy buy
        }

        boolean isCodeValid = fakeAmazon.isDiscountCodeValid(discountCode);

        if (isCodeValid) {
            tfDiscountCode.setStyle("-fx-border-color: #74ff74");
            //dummy method to decide if is 'buyable' or not
            //if yes procede ad respond back to comicsWorld
            BuyComicBoundary buyComicBoundary = new BuyComicBoundary();
            buyComicBoundary.boughtTheComics();
            //otherwise, signal back that it was a fail
            //sorry we couldn't go through with the payment
        } else {
            tfDiscountCode.setStyle("-fx-border-color: #ff6969");
            showErrorMessage();
            return;
        }

    }

    private void showErrorMessage() {
        errorPane.setVisible(true);
    }
    private void closeErrorPane(){
        errorPane.setVisible(false);
    }
    private void clear(){
        tfDiscountCode.setStyle("-fx-border-color: #ffffff");
    }
}
