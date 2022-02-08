package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Queries;
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
    private static final String NOSERIESFOUND = "No series Found ";


    private static List<Series> all = new ArrayList<>();

    public static Series retrieveSeries(String title) {
        for(Series s : all){
            if(s.getTitle().equals(title)){
                return s;
            }
        }
        return null;
    }

    public List<Series> retrieveSeriesFromCategory(Genres genre){

        List<Series> seriesList = new ArrayList<>();

        for(Series s : all){
           if(s.getGenre1().equals(genre) || s.getGenre2().equals(genre) || s.getGenre3().equals(genre)){
               seriesList.add(s);
           }
        }
        return seriesList;
    }

    public List<Series> retrieveLatestSeries(){
        //facciamo finta
        return all;
    }

    public static void retriveAllSeries() {

        List<Author> allAuthors = AuthorDAO.retriveAllAuthors();

        Statement stmt = null;
        Connection conn = null;

        List<Series> seriesList = new ArrayList<>();


        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveLatestSeries(stmt);

            if (!rs.first()) {
                all = null;
            }
            rs.first();
            do {
                        Author author = null;
                        Series series;
                        for(Author a: allAuthors){
                            if(a.getUsername().equals(rs.getString(AUTHOR))){
                                author = a;
                            }
                        }

                        series = new Series(rs.getString(TITLE), author);
                        series.setDescription(rs.getString("description"));
                        series.setGenre1(Genres.valueOf(rs.getString("genre1")));
                        series.setGenre2(Genres.valueOf(rs.getString("genre2")));
                        series.setGenre3(Genres.valueOf(rs.getString("genre3")));

                        Blob bl = rs.getBlob(COVER);
                        if(bl != null){
                            InputStream inputStream = bl.getBinaryStream();
                            Image image = new Image(inputStream);
                            series.setCover(image);
                        }
                        seriesList.add(series);
                        author.addPublishedSeries(series);


            } while (rs.next());


        } catch (Exception throwables) {
            throwables.printStackTrace();
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
        all = seriesList;
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
                rs.first();
                do{
                    bagdeID = rs.getInt("badgeID");
                }while(rs.next());

                int id = bagdeID;
                Queries.insertObjective(stmt,objective,series,id);

                all.add(series);
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
            try {
                assert stmt!=null;
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Series createSeries(Author author, String title, Genres genre1, Genres genre2, Genres genre3, Image cover, InputStream coverInputStream, List<Objective> objectives, Map<Objective, InputStream> badgeIconHM, String description) {
        Series series;
        series = new Series();
        series.setAuthor(author);
        series.setTitle(title);
        series.setGenre1(genre1);
        series.setGenre2(genre2);
        series.setGenre3(genre3);
        series.setCover(cover);
        series.setObjectives(objectives);
        series.setDescription(description);

        savePublishedSeries(series,coverInputStream,badgeIconHM);

        return series;
    }


    public List<String> retrieveFavouriteSeriesTitles(String user) {
        Statement stmt = null;
        Connection conn = null;

        List<String> seriesList = new ArrayList<>();

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
                seriesList.add(title);
            } while (rs.next());


        } catch (Exception throwables) {
            throwables.printStackTrace();
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

    public List<String> retrieveToReadSeriesTitles(String user) {
        Statement stmt = null;
        Connection conn = null;

        List<String> seriesList = new ArrayList<>();

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
                seriesList.add(title);
            } while (rs.next());


        } catch (Exception throwables) {
            throwables.printStackTrace();
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

    public List<String> retrieveReadingSeriesTitles(String user) {
        Statement stmt = null;
        Connection conn = null;

        List<String> seriesList = new ArrayList<>();

        String title;

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
                seriesList.add(title);
            } while (rs.next());


        } catch (Exception throwables) {
            throwables.printStackTrace();
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




}