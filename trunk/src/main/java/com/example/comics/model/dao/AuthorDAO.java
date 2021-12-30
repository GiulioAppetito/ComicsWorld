package com.example.comics.model.dao;

import com.example.comics.model.Author;
import com.example.comics.model.Series;

import java.sql.*;
import java.util.ArrayList;

public class AuthorDAO {


    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";


    public Author retrieveAuthor(String identifier, String password) {

        Statement stmt;
        Connection conn = null;

        Author author = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveReader(stmt, identifier, password);

            if (!rs.first()) {
                return null;
            }
            rs.first();

            SeriesDAO seriesDAO = new SeriesDAO();
            ArrayList<Series> publishedSeries = seriesDAO.retrievePublishedSeries(rs.getString("username"));

            author = new Author(publishedSeries);
            author.setFirstName(rs.getString("firstname"));
            author.setUsername(rs.getString("username"));
            author.setLastName(rs.getString(rs.getString("email")));
            author.setPassword(rs.getString("password"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        return author;
    }

}