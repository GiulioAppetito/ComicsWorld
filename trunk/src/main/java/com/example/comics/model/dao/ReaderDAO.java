package com.example.comics.model.dao;

import com.example.comics.model.*;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    public Reader retrieveReader(String identifier, String password){

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
            List<Series> favSeries = seriesDAO.retrieveFavouriteSeries(username);
            List<Series> toReadSeries = seriesDAO.retrieveToReadSeries(username);
            List<Series> readingSeries = seriesDAO.retrieveReadingSeries(username);
            List<Review> reviews = seriesDAO.retrieveReviewsByReader(username);

            reader = new Reader(favSeries, toReadSeries, readingSeries, reviews, username);

            reader.setFirstName(rs.getString("firstname"));
            reader.setLastName(rs.getString("lastname"));
            reader.setPassword(rs.getString("password"));

            Blob bl = rs.getBlob("proPic");
            InputStream inputStream = bl.getBinaryStream();
            Image image = new Image(inputStream);
            reader.setProPic(image);




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    finally{
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reader;
    }

    public void saveAchievedObjective(Objective objective, String username) {
        ObjectiveDAO objectiveDAO = new ObjectiveDAO();
        System.out.println("[ReaderDAO] Calling objectiveDAO con objective,username = "+objective+","+username);
        objectiveDAO.addAchievedObjective(objective, username);
    }

    public void saveObtainedDiscountCode(DiscountCode discountCode, Reader reader) throws SQLException {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.saveReadersDiscountCode(stmt,discountCode,reader);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            assert conn != null;
            conn.close();
        }
    }

    public void addSeriesToToRead(Series series,Reader reader) throws SQLException {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.addSeriesToToRead(stmt,series,reader);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            assert conn != null;
            conn.close();
        }
    }

    public void removeSeriesFromToRead(Series series,Reader reader){
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;

        try {
            // STEP 2: loading dinamico del driver mysql

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.removeSeriesFromToRead(stmt,series,reader);

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void addSeriesToFavourites(Series series, Reader reader) {
        Statement stmt = null;
        Connection conn = null;

        String series_title = series.getTitle();
        String username = reader.getUsername();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.addSeriesToFavourites(stmt, series_title, username);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeSeriesFromFavourites(Series series, Reader reader) {
        Statement stmt = null;
        Connection conn = null;

        String series_title = series.getTitle();
        String username = reader.getUsername();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.removeSeriesFromFavourites(stmt, series_title, username);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}