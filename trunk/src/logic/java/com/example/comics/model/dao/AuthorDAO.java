package com.example.comics.model.dao;

import com.example.comics.model.Author;
import com.example.comics.model.Series;
import com.example.comics.model.dao.utils.Connector;
import com.example.comics.model.dao.utils.Queries;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    private static List<Author> all = new ArrayList<>();

    private static final String USERNAME = "username";

    public static List<Author> retriveAllAuthors(){
        Statement stmt8 = null;
        Connection conn8 = null;

        List<Author> authors = new ArrayList<>();
        Author author;

        try {
            conn8 = Connector.getInstance().getConnection();
            stmt8 = conn8.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = Queries.retreiveAllAuthors(stmt8);
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
            try {
                assert stmt8 != null;
                stmt8.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        all = authors;
        return authors;
    }

    public Author retrieveAuthor(String identifier, String password) {

        Statement stmt9 = null;
        Connection conn9 = null;

        Author author = null;

        try {

            conn9 = Connector.getInstance().getConnection();
            stmt9 = conn9.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveUser(stmt9, identifier, password);

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
                assert stmt9 != null;
                stmt9.close();
            } catch (SQLException e) {
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
        Statement stmt10 = null;
        Connection conn10 = null;

        List<String> authors = new ArrayList<>();

        String name;

        try {
            conn10 = Connector.getInstance().getConnection();

            stmt10 = conn10.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveFollowedAuthors(stmt10, username);

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
                if (stmt10 != null)
                    stmt10.close();
            } catch (SQLException se2) {
                //TO-DO
            }

        }
        return authors;
    }


}