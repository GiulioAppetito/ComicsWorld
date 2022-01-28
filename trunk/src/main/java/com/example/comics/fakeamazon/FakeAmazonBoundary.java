package com.example.comics.fakeamazon;

import com.example.comics.controller.BuyComicController;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.SeriesBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FakeAmazonBoundary {

    @FXML
    private ImageView ivComicCover;

    @FXML
    private Label lblChapter;

    @FXML
    private Label lblSeries;

    @FXML
    private TextField tfDiscountCode;

    @FXML
    private Button btnBuy;

    public void init(ChapterBean chapterBean, SeriesBean seriesBean) {
        if(chapterBean.getCover()!=null){
            ivComicCover.setImage(chapterBean.getCover());
        }
        lblChapter.setText(chapterBean.getTitle());
        lblSeries.setText(seriesBean.getTitle());
        btnBuy.setOnAction(event -> buyComics(tfDiscountCode.getText()));

    }

    public void buyComics(String discountCode){
        FakeAmazon fakeAmazon = new FakeAmazon();
        boolean isCodeValid = fakeAmazon.isDiscountCodeValid(discountCode);

        if(isCodeValid){
            tfDiscountCode.setStyle("-fx-background-color: #74ff74");
        }else{
            tfDiscountCode.setStyle("-fx-background-color: #ff6969");
        }

        BuyComicController buyComicController = new BuyComicController();

    }
}
