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

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveSeries(stmt, title);

            if (!rs.first()) {
                throw new Exception("No series Found ");
            }
            rs.first();

            do {
                title = rs.getString("title");
                author = rs.getString("author");
                series = new Series(title);
                series.setAuthor(author);

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

    public void addReviewToChapter(Review review) {
        ChapterDAO chapterDAO = new ChapterDAO();
        chapterDAO.addReview(review);
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
                review.setSeries(rs.getString("series_title"));
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

    public boolean isSeriesFavourite(String series_title, String username) {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.isSeriesFavourite(stmt, series_title, username);

            if (!rs.first()) {
                throw new Exception("It is not favourite");
            }
            rs.first();

            do {
                if(rs == null){
                    return false;
                }
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
        return true;
    }

    public void addSeriesToFavourites(Series series, String username) {
        Statement stmt = null;
        Connection conn = null;

        String series_title = series.getTitle();

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

    public void removeSeriesFromFavourites(Series series, String username) {
        Statement stmt = null;
        Connection conn = null;

        String series_title = series.getTitle();

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