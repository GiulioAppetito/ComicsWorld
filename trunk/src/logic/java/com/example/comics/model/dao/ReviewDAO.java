package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Connector;
import com.example.comics.model.dao.utils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public List<Review> retrieveReviews(String chapter)  {
        Statement stmt33 = null;
        Connection conn33;

        List<Review> reviewsList = new ArrayList<>();
        Review reviewItem;

        try {
            conn33 = Connector.getInstance().getConnection();
            stmt33 = conn33.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveReviewsByChapter(stmt33, chapter);

            if(!rs.first()){
                return reviewsList;
            }
            rs.first();
            do{
                AccountDAO accountDAO = new AccountDAO();
                Account account = accountDAO.retriveReviewAuthor(rs.getString("user"));

                reviewItem = new Review(rs.getString("comment"),Integer.parseInt(rs.getString("rating")),account);
                reviewsList.add(reviewItem);

            }while(rs.next());

        } catch (SQLException throwables) {
           return reviewsList;
        }finally{
            assert stmt33!=null;
            try {
                stmt33.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reviewsList;

    }

    public void saveReview(Review review, Chapter chapter, Series series){
        // STEP 1: dichiarazioni
        Statement stmt34 = null;

        try {

            Connection conn34 = Connector.getInstance().getConnection();

            // STEP 4.2: creazione ed esecuzione della query
            stmt34 = conn34.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.saveReview(stmt34,review, chapter, series);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

            try {
                assert stmt34 != null;
                stmt34.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
