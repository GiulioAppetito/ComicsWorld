package com.example.comics.model.dao;

import com.example.comics.model.Chapter;
import com.example.comics.model.Series;
import javafx.scene.image.ImageView;

import java.sql.*;
import java.util.ArrayList;

public class SeriesDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";


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
                title = rs.getString("title");
                author = rs.getString("author");
                //cover = rs.getImg("cover");
                series = new Series(title);
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

}