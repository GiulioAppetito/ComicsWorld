package com.example.comics.model.dao;

import com.example.comics.model.*;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeriesDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";


    public List<Series> retrieveFavouriteSeries(String user) {
        Statement stmt = null;
        Connection conn = null;

        List<Series> seriesList = new ArrayList<>();

        String title;

        Series series;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveFavouriteSeries(stmt, user);

            if (!rs.first()) {
                throw new Exception("No series Found ");
            }
            rs.first();

            do {
                title = rs.getString("series");
                series = retrieveSeries(title);
                seriesList.add(series);
            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
        return seriesList;
    }

    public List<Series> retrieveToReadSeries(String user) {
        Statement stmt = null;
        Connection conn = null;

        List<Series> seriesList = new ArrayList<>();

        String title;
        Series series;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveToReadSeries(stmt, user);

            if (!rs.first()) {
                throw new Exception("No series Found ");
            }
            rs.first();

            do {
                title = rs.getString("series");
                series = retrieveSeries(title);

                seriesList.add(series);
            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
        return seriesList;
    }

    public List<Series> retrievePublishedSeries(String user) {
        Statement stmt = null;
        Connection conn = null;

        List<Series> seriesList = new ArrayList<>();

        String title;
        Series series;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrivePublishedSeries(stmt, user);

            if (!rs.first()) {
                return seriesList;
            }
            rs.first();

            do {
                title = rs.getString("title");
                series = retrieveSeries(title);
                seriesList.add(series);
            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
        return seriesList;
    }

    public List<Series> retrieveReadingSeries(String user) {
        Statement stmt = null;
        Connection conn = null;

        List<Series> seriesList = new ArrayList<>();

        String title;
        Series series;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveReadingSeries(stmt, user);

            if (!rs.first()) {
                throw new Exception("No series Found ");
            }
            rs.first();

            do {
                title = rs.getString("series");
                series = retrieveSeries(title);
                seriesList.add(series);
            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
        return seriesList;
    }

    public Series retrieveSeries(String title) {
        Statement stmt = null;
        Connection conn = null;

        String author;
        Series series = null;
        Genres genre1;
        Genres genre2;
        Genres genre3;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveSeries(stmt, title);

            if (!rs.first()) {
                throw new Exception("No series Found ");
            }
            rs.first();

            do {
                //genre1 = Genres.valueOf(rs.getString("genre1"));
                //genre2 = Genres.valueOf(rs.getString("genre2"));
                //genre3 = Genres.valueOf(rs.getString("genre3"));

                title = rs.getString("title");
                author = rs.getString("author");
                series = new Series(title);
                Blob bl = rs.getBlob("cover");
                InputStream inputStream = bl.getBinaryStream();
                Image image = new Image(inputStream);
                series.setCover(image);
                series.setAuthor(author);
                //series.setGenre1(genre1);
               // series.setGenre2(genre2);
                //series.setGenre3(genre3);
            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
        return series;
    }

    public List<Series> retriveLatestSeries() {

        Statement stmt = null;
        Connection conn = null;

        List<Series> seriesList = new ArrayList<>();

        String author;
        Series series;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveLatestSeries(stmt);

            if (!rs.first()) {
                throw new Exception("No series Found ");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
        return seriesList;
    }

    public List<Chapter> retriveChapters(String seriesTitle) {
        ChapterDAO chapterDAO = new ChapterDAO();
        return chapterDAO.retriveChapters(seriesTitle);
    }

    public void addReviewToChapter(Review review, String seriesTitle) {
        ChapterDAO chapterDAO = new ChapterDAO();
        chapterDAO.addReview(review, seriesTitle);
    }

    public List<Objective> retrieveObjectives(Series series) {
        ObjectiveDAO objectiveDAO = new ObjectiveDAO();
        return objectiveDAO.retrieveSeriesObjectives(series);
    }

    public List<Review> retrieveReviewsByReader(String username) {

        Statement stmt = null;
        Connection conn = null;

        List<Review> reviews = new ArrayList<>();
        Review review;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveReviewsByReader(stmt,username);

            if (!rs.first()) {
                throw new Exception("No reviews Found ");
            }
            rs.first();

            do {
                review = new Review(rs.getString("comment"), rs.getString("user"));
                review.setChapter(rs.getString("chapter_id"));
                reviews.add(review);

            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reviews;

    }


}