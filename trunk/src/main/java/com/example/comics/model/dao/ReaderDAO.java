package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Queries;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    private List<Series> favSeries = new ArrayList<>();
    private List<Series> toReadSeries = new ArrayList<>();
    private List<DiscountCode> discountCodes = new ArrayList<>();
    private List<Author> followedAuthors = new ArrayList<>();
    private List<Series> readingSeries = new ArrayList<>();

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

            String username = rs.getString("username");


            Thread t = new Thread(()->{
                favSeries = seriesDAO.retrieveFavouriteSeries(username);
            });
            t.start();

            Thread t1 = new Thread(()->{
                toReadSeries = seriesDAO.retrieveToReadSeries(username);
            });
            t1.start();

            Thread t2 = new Thread(()->{
                readingSeries = seriesDAO.retrieveReadingSeries(username);
            });
            t2.start();

            DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
            Thread t3 = new Thread(()->{
                discountCodes = discountCodeDAO.retreiveDiscountCodesByReader(username);
            });
            t3.start();

            AuthorDAO authorDAO = new AuthorDAO();
            Thread t4 = new Thread(()->{
                followedAuthors = authorDAO.retreiveFollowedAuthorsByReader(username);
            });
            t4.start();

            t.join();
            t1.join();
            t2.join();
            t3.join();
            t4.join();

            reader = new Reader(favSeries, toReadSeries, readingSeries, username, followedAuthors,discountCodes);

            reader.setFirstName(rs.getString("firstname"));
            reader.setLastName(rs.getString("lastname"));
            reader.setPassword(rs.getString("password"));
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
}