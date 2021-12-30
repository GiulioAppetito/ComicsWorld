package com.example.comics.model.dao;

import com.example.comics.model.*;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class ReaderDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";


    public Reader retrieveReader(String identifier, String password) {

        Statement stmt = null;
        Connection conn = null;

        Reader reader = null;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveReader(stmt, identifier, password);

            if (!rs.first()) {
                return null;
            }
            rs.first();

            SeriesDAO seriesDAO = new SeriesDAO();

            String username = rs.getString("username");
            ArrayList<Series> favSeries = seriesDAO.retrieveFavouriteSeries(username);
            ArrayList<Series> toReadSeries = seriesDAO.retrieveToReadSeries(username);
            ArrayList<Series> readingSeries = seriesDAO.retrieveReadingSeries(username);
            ArrayList<Review> reviews = seriesDAO.retrieveReviewsByReader(username);

            reader = new Reader(favSeries, toReadSeries, readingSeries, reviews, username);

            reader.setFirstName(rs.getString("firstname"));
            reader.setLastName(rs.getString("lastname"));
            reader.setPassword(rs.getString("password"));

            Blob bl = rs.getBlob("proPic");
            InputStream inputStream = bl.getBinaryStream();
            Image image = new Image(inputStream);
            reader.setProPic(image);




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reader;
    }

    public void saveAchievedObjective(Objective objective, String username) {
        ObjectiveDAO objectiveDAO = new ObjectiveDAO();
        System.out.println("[ReaderDAO] Calling objectiveDAO con objective,username = "+objective+","+username);
        objectiveDAO.addAchievedObjective(objective, username);
    }

    public void saveObtainedDiscountCode(DiscountCode discountCode, Reader reader) {
        Statement stmt = null;
        Connection conn = null;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.saveReadersDiscountCode(stmt,discountCode,reader);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}