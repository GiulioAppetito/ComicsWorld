package com.example.comics.model.dao;

import com.example.comics.model.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;

public class SeriesDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";


    public ArrayList<Series> retrieveFavouriteSeries(String user) {
        Statement stmt = null;
        Connection conn = null;

        ArrayList<Series> seriesList = new ArrayList<Series>();

        String title;
        String author;
        String publishingHouse;
        ImageView cover;

        Series series;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveFavouriteSeries(stmt, user);

            if (!rs.first()) {
                Exception e = new Exception("No series Found ");
                throw e;
            }
            rs.first();

            do {
                title = rs.getString("series");
                series = retrieveSeries(title);
                seriesList.add(series);
            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return seriesList;
    }

    public ArrayList<Series> retrieveToReadSeries(String user) {
        Statement stmt = null;
        Connection conn = null;

        ArrayList<Series> seriesList = new ArrayList<Series>();

        String title;
        String author;
        String publishingHouse;
        ImageView cover;

        Series series;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveToReadSeries(stmt, user);

            if (!rs.first()) {
                Exception e = new Exception("No series Found ");
                throw e;
            }
            rs.first();

            do {
                title = rs.getString("series");
                series = retrieveSeries(title);
                seriesList.add(series);
            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return seriesList;
    }

    public ArrayList<Series> retrievePublishedSeries(String user) {
        Statement stmt = null;
        Connection conn = null;

        ArrayList<Series> seriesList = new ArrayList<Series>();

        String title;
        String author;
        String publishingHouse;
        ImageView cover;

        Series series;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrivePublishedSeries(stmt, user);

            if (!rs.first()) {
                Exception e = new Exception("No series Found ");
                throw e;
            }
            rs.first();

            do {
                title = rs.getString("series");
                series = retrieveSeries(title);
                seriesList.add(series);
            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return seriesList;
    }

    public ArrayList<Series> retrieveReadingSeries(String user) {
        Statement stmt = null;
        Connection conn = null;

        ArrayList<Series> seriesList = new ArrayList<Series>();

        String title;
        String author;
        String publishingHouse;
        ImageView cover;

        Series series;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveReadingSeries(stmt, user);

            if (!rs.first()) {
                Exception e = new Exception("No series Found ");
                throw e;
            }
            rs.first();

            do {
                title = rs.getString("series");
                series = retrieveSeries(title);
                seriesList.add(series);
            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return seriesList;
    }

    public Series retrieveSeries(String title) {
        Statement stmt = null;
        Connection conn = null;

        String author;
        String publishingHouse;
        ImageView cover;

        Series series = null;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveSeries(stmt, title);

            if (!rs.first()) {
                Exception e = new Exception("No series Found ");
                throw e;
            }
            rs.first();

            do {
                title = rs.getString("title");
                author = rs.getString("author");
                //cover = rs.getImg("cover");
                series = new Series(title);
                series.setAuthor(author);

            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return series;
    }

    public ArrayList<Series> retriveLatestSeries() {

        Statement stmt = null;
        Connection conn = null;

        ArrayList<Series> seriesList = new ArrayList<Series>();

        String title;
        String author;
        String publishingHouse;
        ImageView cover;

        Series series;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveLatestSeries(stmt);

            if (!rs.first()) {
                Exception e = new Exception("No series Found ");
                throw e;
            }
            rs.first();
            do {
                series = new Series(rs.getString("title"));

                Blob bl = rs.getBlob("cover");
                InputStream inputStream = bl.getBinaryStream();
                Image image = new Image(inputStream);
                series.setCover(image);

                author = rs.getString("author");


                series.setAuthor(author);
                seriesList.add(series);
            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return seriesList;
    }

    public ArrayList<Chapter> retriveChapters(String seriesTitle) {
        ChapterDAO chapterDAO = new ChapterDAO();
        return chapterDAO.retriveChapters(seriesTitle);
    }

    public void addReviewToChapter(Review review) {
        ChapterDAO chapterDAO = new ChapterDAO();
        chapterDAO.addReview(review);
    }

    public ArrayList<Objective> retrieveObjectives(Series series) {
        ObjectiveDAO objectiveDAO = new ObjectiveDAO();
        return objectiveDAO.retrieveSeriesObjectives(series);
    }

    public ArrayList<Review> retrieveReviewsByReader(String username) {

        Statement stmt = null;
        Connection conn = null;

        ArrayList<Review> reviews = new ArrayList<Review>();
        Review review;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveReviewsByReader(stmt,username);

            if (!rs.first()) {
                Exception e = new Exception("No reviews Found ");
                throw e;
            }
            rs.first();

            do {
                review = new Review(rs.getString("comment"), rs.getString("user"));
                review.setSeries(rs.getString("series_title"));
                review.setChapter(rs.getString("chapter_id"));
                reviews.add(review);

            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reviews;

    }
}