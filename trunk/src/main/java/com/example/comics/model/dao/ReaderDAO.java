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

    public static List<Author> retreiveFollowedAuthors(Reader reader) {
        Statement stmt = null;
        Connection conn = null;

        List<Author> followedAuthors = new ArrayList<>();
        Author author;

        AuthorDAO authorDAO = new AuthorDAO();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveFollowedAuthors(stmt, reader.getUsername());

            if (!rs.first()) {
                return null;
            }
            rs.first();

            do{
               author = authorDAO.retrieveAuthorWithoutPassword(rs.getString("author"));
               followedAuthors.add(author);

            }while(rs.next());



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
        return followedAuthors;
    }

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
            List<Series> favSeries = seriesDAO.retrieveFavouriteSeries(username);
            List<Series> toReadSeries = seriesDAO.retrieveToReadSeries(username);
            List<Series> readingSeries = seriesDAO.retrieveReadingSeries(username);

            AuthorDAO authorDAO = new AuthorDAO();
            List<Author> followedAuthors = authorDAO.retreiveFollowedAuthorsByReader(username);

            reader = new Reader(favSeries, toReadSeries, readingSeries, username, followedAuthors);

            reader.setFirstName(rs.getString("firstname"));
            reader.setLastName(rs.getString("lastname"));
            reader.setPassword(rs.getString("password"));
            reader.setEmail(rs.getString("email"));

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

    public void saveAchievedBadge(Badge badge, Reader reader) {
        BadgeDAO badgeDAO = new BadgeDAO();
        badgeDAO.addAchievedBadge(badge, reader);
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

    public void saveReadChapter(Series series, String title) {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.addReadChapter(stmt,UserLogin.getInstance().getReader(),series,title);

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