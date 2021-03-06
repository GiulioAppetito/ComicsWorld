package com.example.comics.view1;

import com.example.comics.controller.ResearchController;
import com.example.comics.model.fagioli.OrderBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

public class OrdersControllerG {

    @FXML
    private GridPane gpOrders;

    public void initialize(){

        ResearchController researchController = new ResearchController();
        List<OrderBean> orders = researchController.getUserOrders();

        int size = orders.size();

        int columns = 1;
        int i=1;
        for(int j=0; j<size; j++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ordercard.fxml"));
            try {
                HBox card = fxmlLoader.load();

                OrderCardControllerG orderCardControllerG = fxmlLoader.getController();

                orderCardControllerG.setData(orders.get(j));
                gpOrders.add(card,j%columns,i);
                if(j%columns == columns-1){
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
