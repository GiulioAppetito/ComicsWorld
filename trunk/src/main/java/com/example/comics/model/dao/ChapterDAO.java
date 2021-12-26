package com.example.comics.model.dao;

import com.example.comics.model.Chapter;
import com.example.comics.model.Review;
import javafx.scene.image.ImageView;

import java.sql.*;
import java.util.ArrayList;

public class ChapterDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public ArrayList<Chapter> retriveChapters(String seriesTitle) {

        Statement stmt = null;
        Connection conn = null;

        ArrayList<Chapter> chaptersList = new ArrayList<Chapter>();

        String chapterTitle;
        String publishingHouse;
        ImageView cover;
        Integer chapter_id;
        String chapterSeries;

        Chapter chapter;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveChapters(stmt, seriesTitle);

            if (!rs.first()) {
                throw new Exception("No chapters Found on series: " + seriesTitle);
            }
            rs.first();

            do {
                chapterTitle = rs.getString("chapter_title");
                chapter_id = rs.getInt("chapter_id");
                chapterSeries = rs.getString("series_title");

                //author = rs.getString("author");
                //cover = rs.getImg("cover");
                chapter = new Chapter(chapterSeries,chapterTitle);
                chapter.setId(chapter_id);


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

    public ArrayList<Review> retrieveReviews(String series, String chapter) throws ReviewsNotFoundException{
        ReviewDAO reviewDAO = new ReviewDAO();
        return reviewDAO.retrieveReviews(series,chapter);

    }
}
