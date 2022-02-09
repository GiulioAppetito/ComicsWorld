package com.example.comics.model.dao;

import com.example.comics.model.Author;
import com.example.comics.model.Series;
import com.example.comics.model.dao.utils.Queries;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {


    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    private static List<Author> all = new ArrayList<>();

    private static final String USERNAME = "username";

    public static List<Author> retriveAllAuthors(){
        Statement stmt;
        Connection conn = null;

        List<Author> authors = new ArrayList<>();
        Author author;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = Queries.retreiveAllAuthors(stmt);
            if(!rs.first()){
                return authors;
            }
            rs.first();
            do{
                author = new Author();
                author.setFirstName(rs.getString("firstname"));
                author.setUsername(rs.getString(USERNAME));
                author.setLastName(rs.getString("lastname"));
                author.setEmail(rs.getString("email"));

                Blob bl = rs.getBlob("proPic");
                if(bl != null){
                    InputStream inputStream = bl.getBinaryStream();
                    Image image = new Image(inputStream);
                    author.setProPic(image);
                }

                //senza published series

                authors.add(author);
            }while(rs.next());



        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            assert conn != null;
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        all = authors;
        return authors;
    }

    public Author retrieveAuthor(String identifier, String password) {

        Statement stmt;
        Connection conn = null;

        Author author = null;

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveUser(stmt, identifier, password);

            if (!rs.first()) {
                return null;
            }
            rs.first();

            author = new Author();
            author.setFirstName(rs.getString("firstname"));
            author.setLastName(rs.getString("lastname"));
            author.setUsername(rs.getString(USERNAME));
            author.setEmail(rs.getString("email"));
            List<Series> publishedSeries = new ArrayList<>();
            for(Author a: all){
                if(a.getUsername().equals(rs.getString(USERNAME))){
                    publishedSeries = a.getPublishedSeries();
                }
            }
            author.setPublishedSeries(publishedSeries);

            Blob bl = rs.getBlob("proPic");
            if(bl != null){
                InputStream inputStream = bl.getBinaryStream();
                Image image = new Image(inputStream);
                author.setProPic(image);
            }


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


    public Author retrieveAuthor(String username){
        for(Author a: all){
            if(a.getUsername().equals(username)){
                return a;
            }
        }
        return null;
    }

    public List<String> retrieveFollowedAuthorsNames(String username){
        Statement stmt = null;
        Connection conn = null;

        List<String> authors = new ArrayList<>();

        String name;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveFollowedAuthors(stmt, username);

            if (!rs.first()) {
                return authors;
            }
            rs.first();

            do {
                name = rs.getString("author");
                authors.add(name);
            } while (rs.next());


        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return authors;
    }


}