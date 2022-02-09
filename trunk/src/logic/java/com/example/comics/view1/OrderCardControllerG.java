package com.example.comics.view1;

import com.example.comics.model.dao.utils.DatesConverter;
import com.example.comics.model.fagioli.OrderBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class OrderCardControllerG {

    @FXML
    private ImageView imgCover;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblSeries;

    @FXML
    private Label lblchapter;

    public void setData(OrderBean orderBean){
        lblDate.setText(DatesConverter.toString(orderBean.getDate()));
        lblPrice.setText(orderBean.getExpense().toString());
        lblSeries.setText(orderBean.getSeries().getTitle());
        imgCover.setImage(orderBean.getSeries().getCover());
        lblchapter.setText(orderBean.getChapterTitle());
    }

}
