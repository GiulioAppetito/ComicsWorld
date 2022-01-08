package com.example.comics.model.dao;

import com.example.comics.model.Author;
import com.example.comics.model.Series;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            List<Series> publishedSeries = seriesDAO.retrievePublishedSeries(rs.getString("username"));

            author = new Author(publishedSeries);
            author.setFirstName(rs.getString("firstname"));
            author.setUsername(rs.getString("username"));
            //author.setLastName(rs.getString(rs.getString("lastname")));
            author.setPassword(rs.getString("password"));

            Blob bl = rs.getBlob("proPic");
            InputStream inputStream = bl.getBinaryStream();
            Image image = new Image(inputStream);
            author.setProPic(image);
            System.out.println("[AuthorDAO] Inited author with pic : "+author.getProPic());

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

    public Author retrieveAuthorWithoutPassword(String username) {

        Statement stmt;
        Connection conn = null;

        Author author = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveAuthor(stmt, username);

            if (!rs.first()) {
                return null;
            }
            rs.first();

            SeriesDAO seriesDAO = new SeriesDAO();
            List<Series> publishedSeries = seriesDAO.retrievePublishedSeries(rs.getString("username"));

            author = new Author(publishedSeries);
            author.setFirstName(rs.getString("firstname"));
            author.setUsername(rs.getString("username"));
            author.setLastName(rs.getString("lastname"));


            Blob bl = rs.getBlob("proPic");
            InputStream inputStream = bl.getBinaryStream();

            Image image = new Image(inputStream);
            author.setProPic(image);
            System.out.println("[AuthorDAO] Inited author with pic : "+author.getProPic());

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

    public void savePublishedSeries(Series series) {

            // STEP 1: dichiarazioni
            Statement stmt = null;
            Connection conn = null;

            try {

                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                // STEP 4.1: creazione ed esecuzione della query
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);

                // In pratica i risultati delle query possono essere visti come un Array Associativo o un Map
               // Queries.saveSeries(stmt,series);
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    assert conn != null;
                    conn.close();
                } catch (SQLException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
    }


    public List<Author> retreiveFollowedAuthorsByReader(String username) {
        Statement stmt = null;
        Connection conn = null;

        List<Author> followedAuthors = new ArrayList<>();
        Author author;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = Queries.retreiveFollowedAuthors(stmt,username);
            if(!rs.first()){
                return followedAuthors;
            }
            rs.first();
            do{
               author = new Author();
               author = retrieveAuthorWithoutPassword(rs.getString("author"));
                followedAuthors.add(author);
            }while(rs.next());



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return followedAuthors;

    }
}