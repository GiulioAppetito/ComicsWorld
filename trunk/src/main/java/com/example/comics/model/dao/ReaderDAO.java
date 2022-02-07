package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Queries;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReaderDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    private List<Series> favSeries = new ArrayList<>();
    private List<Series> toReadSeries = new ArrayList<>();
    private Map<DiscountCode,Series> discountCodes = new HashMap<>();
    private List<Author> followedAuthors = new ArrayList<>();
    private List<Series> readingSeries = new ArrayList<>();


    private List<Series> all = new ArrayList<>();
    private List<String> favTitles = new ArrayList<>();
    private List<String> toReadTitles = new ArrayList<>();
    private List<String> readingTitles = new ArrayList<>();
    private List<String> followedAuthorsNames = new ArrayList<>();

    public Reader retrieveReader(String identifier, String password){

        Statement stmt = null;
        Connection conn = null;

        Reader reader = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveUser(stmt, identifier, password);

            if (!rs.first()) {
                return null;
            }
            rs.first();

            SeriesDAO seriesDAO = new SeriesDAO();
            AuthorDAO authorDAO = new AuthorDAO();

            String username = rs.getString("username");

            favTitles = seriesDAO.retrieveFavouriteSeriesTitles(username);
            toReadTitles = seriesDAO.retrieveToReadSeriesTitles(username);
            readingTitles = seriesDAO.retrieveReadingSeriesTitles(username);
            followedAuthorsNames = authorDAO.retrieveFollowedAuthorsNames(username);

            for(String title : favTitles){
                Series series = seriesDAO.retrieveSeries(title);
                favSeries.add(series);
            }

            for(String title : toReadTitles){
                Series series = seriesDAO.retrieveSeries(title);
                toReadSeries.add(series);
            }

            for(String title : readingTitles){
                Series series = seriesDAO.retrieveSeries(title);
                readingSeries.add(series);
            }

            for(String name : followedAuthorsNames){
                Author author = authorDAO.retrieveAuthor(name);
                followedAuthors.add(author);
            }



            DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
            Thread t3 = new Thread(()->{
                discountCodes = discountCodeDAO.retreiveDiscountCodesByReader(username);
            });
            t3.start();



            t3.join();
            reader = new Reader(favSeries, toReadSeries, readingSeries, username, followedAuthors, discountCodes);

            reader.setFirstName(rs.getString("firstname"));
            reader.setLastName(rs.getString("lastname"));
            reader.setEmail(rs.getString("email"));

            Blob bl = rs.getBlob("propic");
            if(bl != null){
                InputStream inputStream = bl.getBinaryStream();
                Image image = new Image(inputStream);
                reader.setProPic(image);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reader;
    }

    public void saveAchievedBadge(Badge badge, Reader reader) {
        BadgeDAO badgeDAO = new BadgeDAO();
        badgeDAO.addAchievedBadge(badge, reader);
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

        String seriesTitle = series.getTitle();
        String username = reader.getUsername();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.addSeriesToFavourites(stmt, seriesTitle, username);

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

        String seriesTitle = series.getTitle();
        String username = reader.getUsername();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.removeSeriesFromFavourites(stmt, seriesTitle, username);

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

    public void saveFollowedAuthor(Author author) {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.addFollowedAuthor(stmt,author);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            assert conn != null;
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeFollowedAuthor(Reader reader, Author author) {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.removeFollowedAuthor(stmt,reader,author);

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

    public void saveReadChapter(Series series, String chapterTitle) {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.addReadChapter(stmt,UserLogin.getInstance().getReader(),series, chapterTitle);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            assert conn != null;
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeReadChapter(Series series, String chapterTitle) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.removeChapterFromRead(stmt,series,chapterTitle,UserLogin.getInstance().getReader());

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
}