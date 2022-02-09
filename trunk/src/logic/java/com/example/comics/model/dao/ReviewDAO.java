package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    public List<Review> retrieveReviews(String chapter)  {
        Statement stmt33;
        Connection conn33 = null;

        List<Review> reviewsList = new ArrayList<>();
        Review reviewItem;

        try {
            conn33 = DriverManager.getConnection(DB_URL, USER, PASS);
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
            assert conn33!=null;
            try {
                assert conn33!=null;
                conn33.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reviewsList;

    }

    public void saveReview(Review review, Chapter chapter, Series series){
        // STEP 1: dichiarazioni
        Statement stmt34;
        Connection conn34 = null;

        try {
            // STEP 2: loading dinamico del driver mysql

            // STEP 3: apertura connessione
            conn34 = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.2: creazione ed esecuzione della query
            stmt34 = conn34.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.saveReview(stmt34,review, chapter, series);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            assert conn34!=null;
            try {
                conn34.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
