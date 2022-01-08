package com.example.comics.model.dao;

import com.example.comics.model.Reader;
import com.example.comics.model.Review;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    public List<Review> retrieveReviews(String chapter) throws ReviewsNotFoundException {
        Statement stmt = null;
        Connection conn = null;

        List<Review> reviewsList = new ArrayList<>();
        Review reviewItem;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveReviewsByChapter(stmt, chapter);

            if(!rs.first()){
                throw new ReviewsNotFoundException();
            }
            rs.first();
            do{
                reviewItem = new Review(rs.getString("comment"),rs.getString("user"));
                reviewItem.setRating(Integer.valueOf(rs.getString("rating")));
                reviewsList.add(reviewItem);

            }while(rs.next());

        } catch (SQLException throwables) {
            throw new ReviewsNotFoundException();
        }finally{
            assert conn!=null;
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reviewsList;

    }

    public List<Review> retrieveReviewsByReaderAndSeries(Series series, Reader reader) throws ReviewsNotFoundException {
        Statement stmt = null;
        Connection conn = null;

        List<Review> reviewsList = new ArrayList<>();
        Review reviewItem;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveReviewsByReaderAndSeries(stmt, series.getTitle(), reader.getUsername());

            if(!rs.first()){
                throw new ReviewsNotFoundException();
            }
            rs.first();
            do{
                reviewItem = new Review(rs.getString("comment"),rs.getString("user"));
                reviewItem.setRating(Integer.valueOf(rs.getString("rating")));
                reviewsList.add(reviewItem);

            }while(rs.next());

        } catch (SQLException throwables) {
            throw new ReviewsNotFoundException();
        }finally{
            assert conn!=null;
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reviewsList;

    }

    public static void saveReview(Review review, String seriesTitle) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;

        try {
            // STEP 2: loading dinamico del driver mysql

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.2: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.saveReview(stmt,review, seriesTitle);

        } finally {
            assert conn!=null;
            conn.close();
        }
    }
}
