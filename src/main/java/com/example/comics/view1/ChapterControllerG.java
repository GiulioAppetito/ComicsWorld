package com.example.comics.view1;

import com.example.comics.model.Advertisement;
import com.example.comics.model.Review;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChapterControllerG {

    @FXML
    private VBox vbReviews;

    public void init(){
        ArrayList<Review> listOfReviews = new ArrayList<>(addReviews());
        int len = listOfReviews.size();

        for (Review review : listOfReviews) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("review.fxml"));
            try {
                VBox vbRev = fxmlLoader.load();
                ReviewController reviewController = fxmlLoader.getController();
                reviewController.setData(review);

                vbRev.setOnMouseClicked(event -> System.out.println("Clicked ad"));

                vbReviews.getChildren().add(vbRev);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Review> addReviews(){

        List<Review> lr = new ArrayList<>();
        int i;
        int n = 8;


        for(i=0; i<n; i++){
            Review review = new Review();
            review.setComment("Comment n°"+ i);
            review.setUsername("Anastasia");
            //comic.setImageSrc(null);
            lr.add(review);
        }


        return lr;

    }
}
