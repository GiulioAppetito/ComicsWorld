package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Connector;
import com.example.comics.model.dao.utils.Queries;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReaderDAO {


    public Reader retrieveReader(String identifier, String password){

        Statement stmt24 = null;
        Connection conn24;

        Reader reader = null;

        List<Series> favSeries = new ArrayList<>();
        List<Series> toReadSeries = new ArrayList<>();
        Map<DiscountCode,Series> discountCodes;
        List<Author> followedAuthors = new ArrayList<>();
        List<Series> readingSeries = new ArrayList<>();

        List<String> favTitles;
        List<String> toReadTitles;
        List<String> readingTitles;
        List<String> followedAuthorsNames;

        try {
            conn24 = Connector.getInstance().getConnection();
            stmt24 = conn24.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveUser(stmt24, identifier, password);

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
                Series series = SeriesDAO.retrieveSeries(title);
                favSeries.add(series);
            }

            for(String title : toReadTitles){
                Series series = SeriesDAO.retrieveSeries(title);
                toReadSeries.add(series);
            }

            for(String title : readingTitles){
                Series series = SeriesDAO.retrieveSeries(title);
                readingSeries.add(series);
            }

            for(String name : followedAuthorsNames){
                Author author = authorDAO.retrieveAuthor(name);
                followedAuthors.add(author);
            }



            DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
            discountCodes = discountCodeDAO.retreiveDiscountCodesByReader(username);

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

        } finally{
            try {
                assert stmt24 != null;
                stmt24.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reader;
    }


    public void addSeriesToToRead(Series series,Reader reader) throws SQLException {
        Statement stmt25 = null;
        Connection conn25;

        try {
            conn25 = Connector.getInstance().getConnection();
            stmt25 = conn25.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.addSeriesToToRead(stmt25,series,reader);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            assert stmt25 != null;
            stmt25.close();
        }
    }

    public void removeSeriesFromToRead(Series series,Reader reader){
        // STEP 1: dichiarazioni
        Statement stmt26 = null;
        Connection conn26;

        try {
            // STEP 3: apertura connessione
            conn26 = Connector.getInstance().getConnection();
            // STEP 4.1: creazione ed esecuzione della query
            stmt26 = conn26.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.removeSeriesFromToRead(stmt26,series,reader);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert stmt26 != null;
                stmt26.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void addSeriesToFavourites(Series series, Reader reader) {
        Statement stmt27 = null;
        Connection conn27;

        String seriesTitle = series.getTitle();
        String username = reader.getUsername();

        try {
            conn27 = Connector.getInstance().getConnection();
            stmt27 = conn27.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.addSeriesToFavourites(stmt27, seriesTitle, username);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert stmt27 != null;
                stmt27.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeSeriesFromFavourites(Series series, Reader reader) {
        Statement stmt28 = null;
        Connection conn28;

        String seriesTitle = series.getTitle();
        String username = reader.getUsername();

        try {
            conn28 = Connector.getInstance().getConnection();
            stmt28 = conn28.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.removeSeriesFromFavourites(stmt28, seriesTitle, username);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert stmt28 != null;
                stmt28.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFollowedAuthor(Author author) {
        Statement stmt29 = null;
        Connection conn29;

        try {
            conn29 = Connector.getInstance().getConnection();
            stmt29 = conn29.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.addFollowedAuthor(stmt29,author);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            assert stmt29 != null;
            try {
                stmt29.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeFollowedAuthor(Reader reader, Author author) {
        Statement stmt30 = null;
        Connection conn30;

        try {
            conn30 = Connector.getInstance().getConnection();
            stmt30 = conn30.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.removeFollowedAuthor(stmt30,reader,author);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert stmt30 != null;
                stmt30.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveReadChapter(Series series, String chapterTitle) {
        Statement stmt31 = null;
        Connection conn31;

        try {
            conn31 = Connector.getInstance().getConnection();
            stmt31 = conn31.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.addReadChapter(stmt31,UserLogin.getInstance().getReader(),series, chapterTitle);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                assert stmt31 != null;
                stmt31.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeReadChapter(Series series, String chapterTitle) {
        // STEP 1: dichiarazioni
        Statement stmt32 = null;
        Connection conn32;

        try {

            conn32 = Connector.getInstance().getConnection();
            stmt32 = conn32.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.removeChapterFromRead(stmt32,series,chapterTitle,UserLogin.getInstance().getReader());

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {

            try {
                assert stmt32 != null;
                stmt32.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}