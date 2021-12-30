package com.example.comics.model.dao;

import com.example.comics.model.Chapter;
import com.example.comics.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    public List<Chapter> retriveChapters(String seriesTitle) {

        Statement stmt = null;
        Connection conn = null;

        List<Chapter> chaptersList = new ArrayList<>();

        String chapterTitle;
        Integer chapterID;
        String chapterSeries;

        Chapter chapter;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveChapters(stmt, seriesTitle);

            if (!rs.first()) {
                throw new Exception("No chapters Found on series: " + seriesTitle);
            }
            rs.first();

            do {
                chapterTitle = rs.getString("chapter_title");
                chapterID = rs.getInt("chapterID");
                chapterSeries = rs.getString("series_title");
                chapter = new Chapter(chapterSeries,chapterTitle);
                chapter.setId(chapterID);

                chaptersList.add(chapter);
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
        return chaptersList;
    }

    public List<Review> retrieveReviews(String series, String chapter) throws ReviewsNotFoundException{
        ReviewDAO reviewDAO = new ReviewDAO();
        return reviewDAO.retrieveReviews(series,chapter);

    }

    public void addReview(Review review) {
        try {
            ReviewDAO.saveReview(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
