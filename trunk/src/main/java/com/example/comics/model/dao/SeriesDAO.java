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
                series = new Series();
                series.setTitle(title);
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

        Statement stmt = null;
        Connection conn = null;

        ArrayList<Chapter> chaptersList = new ArrayList<Chapter>();

        String chapterTitle;
        String author;
        String publishingHouse;
        ImageView cover;
        Integer chapter_id;

        Chapter chapter;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveChapters(stmt, seriesTitle);

            if (!rs.first()) {
                Exception e = new Exception("No chapters Found on series: " + seriesTitle);
                throw e;
            }
            rs.first();

            do {
                chapterTitle = rs.getString("chapter_title");
                chapter_id = rs.getInt("chapter_id");

                //author = rs.getString("author");
                //cover = rs.getImg("cover");
                chapter = new Chapter();
                chapter.setTitle(chapterTitle);
                chapter.setId(chapter_id);
                chapter.setSeries(seriesTitle);

                //chapters.setAuthor(author);
                chaptersList.add(chapter);
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
        return chaptersList;
    }

}
