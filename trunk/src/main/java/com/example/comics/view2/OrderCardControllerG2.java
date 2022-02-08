package com.example.comics.view2;

import com.example.comics.model.fagioli.OrderBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class OrderCardControllerG2 {

    @FXML
    private Label lblDate;

    @FXML
    private Label lblExpense;

    @FXML
    private ImageView seriesCover;

    @FXML
    private Label seriesTitle;

    @FXML
    private Label chapterTitle;

    public void setData(OrderBean orderBean){
        lblDate.setText(String.valueOf(orderBean.getDate()));
        lblExpense.setText(String.valueOf(orderBean.getExpense()));
        seriesCover.setImage(orderBean.getSeries().getCover());
        seriesTitle.setText(orderBean.getSeries().getTitle());
        chapterTitle.setText(orderBean.getChapterTitle());
    }
}
