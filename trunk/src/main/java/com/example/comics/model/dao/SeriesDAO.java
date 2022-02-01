package com.example.comics.model.dao;

import com.example.comics.model.*;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeriesDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    private static final String SERIES = "series";
    private static final String TITLE = "title";
    private static final String COVER = "cover";
    private static final String AUTHOR = "author";

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
                return seriesList;
            }
            rs.first();

            do {
                title = rs.getString(SERIES);
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
                return seriesList;
            }
            rs.first();

            do {
                title = rs.getString(SERIES);
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

    public List<Series> retrievePublishedSeries(Author author) {
        Statement stmt = null;
        Connection conn = null;

        List<Series> seriesList = new ArrayList<>();

        Series series;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrivePublishedSeries(stmt, author.getUsername());

            if (!rs.first()) {
                return seriesList;
            }
            rs.first();

            do {
                series = new Series(rs.getString(TITLE), author);

                Blob bl = rs.getBlob(COVER);
                if(bl!=null){
                    InputStream inputStream = bl.getBinaryStream();
                    Image image = new Image(inputStream);
                    series.setCover(image);

                }

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
                return seriesList;
            }
            rs.first();

            do {
                title = rs.getString(SERIES);
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

        Series series = null;
        Author author;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveSeries(stmt, title);

            if (!rs.first()) {
                throw new Exception("No series Found ");
            }
            rs.first();

            do {

                title = rs.getString(TITLE);

                AuthorDAO authorDAO = new AuthorDAO();
                author = authorDAO.retrieveAuthorWithoutPassword(rs.getString(AUTHOR));
                series = new Series(title, author);
                Blob bl = rs.getBlob(COVER);
                InputStream inputStream = bl.getBinaryStream();
                Image image = new Image(inputStream);
                series.setCover(image);
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

    public List<Series> retrieveSeriesFromCategory(Genres genre){

        Statement stmt = null;
        Connection conn = null;

        List<Series> seriesList = new ArrayList<>();

        Author author;
        Series series;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveCategorySeries(stmt, genre);

            if (!rs.first()) {
                throw new Exception("No series Found ");
            }
            rs.first();
            do {

                AuthorDAO authorDAO = new AuthorDAO();
                author = authorDAO.retrieveAuthorWithoutPassword(rs.getString(AUTHOR));
                series = new Series(rs.getString(TITLE), author);

                Blob bl = rs.getBlob(COVER);
                InputStream inputStream = bl.getBinaryStream();
                Image image = new Image(inputStream);
                series.setCover(image);

                seriesList.add(series);

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
        return seriesList;
    }

    public Series retreiveSeriesWithAuthor(String title,Author author){
        Statement stmt = null;
        Connection conn = null;

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
                title = rs.getString(TITLE);

                series = new Series(title, author);
                Blob bl = rs.getBlob(COVER);
                InputStream inputStream = bl.getBinaryStream();
                Image image = new Image(inputStream);
                series.setCover(image);
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


        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveLatestSeries(stmt);

            if (!rs.first()) {
                return seriesList;
            }
            rs.first();
            do {
                    try{
                        Author author;
                        Series series;
                        AuthorDAO authorDAO = new AuthorDAO();
                        author = authorDAO.retrieveAuthorWithoutPassword(rs.getString(AUTHOR));
                        series = new Series(rs.getString(TITLE), author);

                        Blob bl = rs.getBlob(COVER);
                        if(bl != null){
                            InputStream inputStream = bl.getBinaryStream();
                            Image image = new Image(inputStream);
                            series.setCover(image);
                        }
                        seriesList.add(series);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

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

    public List<Objective> retrieveObjectives(String series) {
        ObjectiveDAO objectiveDAO = new ObjectiveDAO();
        return objectiveDAO.retrieveSeriesObjectives(series);
    }

    public void savePublishedSeries(Series series, InputStream seriesCoverInputStream, Map<Objective,InputStream> hashMap) {
        Statement stmt = null;
        Connection conn = null;
        int bagdeID = 0;
        try {

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // STEP 4.2: creazione ed esecuzione della query
            Queries.insertSeries(conn,series,seriesCoverInputStream);
            for(Objective objective : series.getObjectives()){

                Queries.insertBadge(conn,objective.getBadge(),hashMap.get(objective));

                //retreive last insert id in badge
                ResultSet rs = Queries.getAllBadges(stmt);
                if(!rs.first()){
                    bagdeID = 0;
                }
                rs.first();
                bagdeID = 0;
                do{
                    bagdeID = rs.getInt("badgeID");
                }while(rs.next());

                int id = bagdeID;
                Queries.insertObjective(stmt,objective,series,id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Series createSeries(Author author, String title, Genres genre1, Genres genre2, Genres genre3, Image cover, InputStream coverInputStream, List<Objective> objectives, Map<Objective,InputStream> badgeIconHM) {
        Series series;
        series = new Series();
        series.setAuthor(author);
        series.setTitle(title);
        series.setGenre1(genre1);
        series.setGenre2(genre2);
        series.setGenre3(genre3);
        series.setCover(cover);
        series.setObjectives(objectives);

        savePublishedSeries(series,coverInputStream,badgeIconHM);

        return series;

    }
}