package com.example.comics.model.dao;

import com.example.comics.model.Reader;
import com.example.comics.model.Series;

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

            reader = new Reader(favSeries, toReadSeries, readingSeries);

            reader.setUsername(rs.getString("username"));
            reader.setFirstName(rs.getString("firstname"));
            reader.setEmail(rs.getString("email"));
            reader.setPassword(rs.getString("password"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reader;
    }
}