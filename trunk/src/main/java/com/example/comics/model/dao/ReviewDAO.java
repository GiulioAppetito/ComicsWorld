package com.example.comics.model.dao;

import com.example.comics.model.Chapter;
import com.example.comics.model.Review;

import java.sql.*;
import java.util.ArrayList;

public class ReviewDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public ArrayList<Review> retrieveReviews(String series,String chapter) throws ReviewsNotFoundException {
        Statement stmt = null;
        Connection conn = null;

        ArrayList<Review> reviewsList = new ArrayList<Review>();
        Review reviewItem;
        String reviewer;
        String comment;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveReviewsByChapter(stmt, series,chapter);

            if(!rs.first()){
                throw new ReviewsNotFoundException();
            }
            rs.first();
            do{
                reviewItem = new Review(rs.getString("comment"),rs.getString("user"));
                reviewsList.add(reviewItem);

            }while(rs.next());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throw new ReviewsNotFoundException();
        }

        return reviewsList;

    }
}
