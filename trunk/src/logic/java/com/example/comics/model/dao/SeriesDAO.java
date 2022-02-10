package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Queries;
import com.example.comics.model.exceptions.AlreadyExistingSeriesException;
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

    private static List<Series> all = new ArrayList<>();

    public static Series retrieveSeries(String title) {
        if(all.isEmpty()){
            retriveAllSeries();
        }
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

        Statement stmt35 = null;
        Connection conn35 = null;

        List<Series> seriesList = new ArrayList<>();


        try {
            conn35 = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt35 = conn35.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveLatestSeries(stmt35);

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
                        assert author != null;
                        author.addPublishedSeries(series);


            } while (rs.next());



        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt35 != null)
                    stmt35.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn35 != null)
                    conn35.close();
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


    public void savePublishedSeries(Series series, InputStream seriesCoverInputStream, Map<Objective,InputStream> hashMap) throws AlreadyExistingSeriesException {
        Statement stmt36 = null;
        Connection conn36 = null;
        int bagdeID = 0;
        try {
            // STEP 3: apertura connessione
            conn36 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt36 = conn36.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // STEP 4.2: creazione ed esecuzione della query
            Queries.insertSeries(conn36,series,seriesCoverInputStream);
            for(Objective objective : series.getObjectives()){

                Queries.insertBadge(conn36,objective.getBadge(),hashMap.get(objective));

                //retreive last insert id in badge
                ResultSet rs = Queries.getAllBadges(stmt36);
                rs.first();
                do{
                    bagdeID = rs.getInt("badgeID");
                }while(rs.next());

                int id = bagdeID;
                Queries.insertObjective(stmt36,objective,series,id);

            }
            all.add(series);
        } catch (SQLException throwables) {
            throw new AlreadyExistingSeriesException("This title is already used!");
        } finally {
            try {
                assert conn36 != null;
                conn36.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                assert stmt36!=null;
                stmt36.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Series createSeries(String title, List<Genres> genresList, Image cover, InputStream coverInputStream, List<Objective> objectives, Map<Objective, InputStream> badgeIconHM, String description) throws AlreadyExistingSeriesException {
        Series series;
        series = new Series();
        //solo perchè c'era il code smell
        series.setAuthor(UserLogin.getInstance().getAuthor());
        series.setTitle(title);
        series.setGenre1(genresList.get(0));
        series.setGenre2(genresList.get(1));
        series.setGenre3(genresList.get(2));
        series.setCover(cover);
        series.setObjectives(objectives);
        series.setDescription(description);

        savePublishedSeries(series,coverInputStream,badgeIconHM);

        return series;
    }


    public List<String> retrieveFavouriteSeriesTitles(String user) {
        Statement stmt37 = null;
        Connection conn37 = null;

        List<String> list = new ArrayList<>();

        String title;

        try {
            conn37 = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt37 = conn37.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveFavouriteSeries(stmt37, user);

            if (!rs.first()) {
                return list;
            }
            rs.first();

            do {
                title = rs.getString(SERIES);
                list.add(title);
            } while (rs.next());


        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt37 != null)
                    stmt37.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn37 != null)
                    conn37.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return list;
    }

    public List<String> retrieveToReadSeriesTitles(String user) {
        Statement stmt38 = null;
        Connection conn38 = null;

        List<String> list2 = new ArrayList<>();

        String title;

        try {
            conn38 = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt38 = conn38.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveToReadSeries(stmt38, user);

            if (!rs.first()) {
                return list2;
            }
            rs.first();

            do {
                title = rs.getString(SERIES);
                list2.add(title);
            } while (rs.next());


        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt38 != null)
                    stmt38.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn38 != null)
                    conn38.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return list2;
    }

    public List<String> retrieveReadingSeriesTitles(String user) {
        Statement stmt39 = null;
        Connection conn39 = null;

        List<String> seriesList = new ArrayList<>();

        String title;

        try {
            conn39 = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt39 = conn39.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveReadingSeries(stmt39, user);

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
                if (stmt39 != null)
                    stmt39.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn39 != null)
                    conn39.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return seriesList;
    }




}