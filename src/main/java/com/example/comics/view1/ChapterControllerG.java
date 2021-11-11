package com.example.comics.view1;

import com.example.comics.model.Advertisement;
import com.example.comics.model.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChapterControllerG {

    @FXML
    private VBox vbReviews;


    @FXML
    private Button btnAddReview;

    @FXML
    private Pane paneInsertReview;

    public void init(){

        paneInsertReview.setVisible(false);

        btnCloseEditor.setOnAction(event ->
                closeEditor());

        btnAddReview.setOnAction(event ->
                openEditor());



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
        List<String> nameList = Arrays.asList("Anastasia","Giulio","Gregor","Peter");
        List<String> commentList = Arrays.asList("Very interesting chapter!","I didn't like this chapter.","Not my favourite chapter...","Best chapter ever!");



        for(i=0; i<nameList.size(); i++){
            Review review = new Review();
            review.setComment(commentList.get(i));
            review.setUsername(nameList.get(i));
            //comic.setImageSrc(null);
            lr.add(review);
        }


        return lr;

    }

    public void openEditor(){
        paneInsertReview.setVisible(true);
    }

    @FXML
    private Button btnCloseEditor;

    @FXML
    void closeEditor() {
        paneInsertReview.setVisible(false);

    }

}
