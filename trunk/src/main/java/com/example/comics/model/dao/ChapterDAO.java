package com.example.comics.model.dao;

import com.example.comics.model.Chapter;
import com.example.comics.model.Reader;
import com.example.comics.model.Review;
import com.example.comics.model.Series;

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
                return chaptersList;
            }
            rs.first();

            do {
                chapterTitle = rs.getString("chapter_title");
                chapterID = rs.getInt("chapter_id");
                chapterSeries = rs.getString("series_title");
                chapter = new Chapter(chapterTitle);
                chapter.setDescription(rs.getString("chapterDescription"));

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

    public List<Review> retrieveReviewsByReader(Series series, Reader reader) throws ReviewsNotFoundException{
        ReviewDAO reviewDAO = new ReviewDAO();
        return reviewDAO.retrieveReviewsByReaderAndSeries(series,reader);

    }

    public List<Review> retrieveReviews(String chapter) throws ReviewsNotFoundException{
        ReviewDAO reviewDAO = new ReviewDAO();
        return reviewDAO.retrieveReviews(chapter);

    }

    public void addReview(Review review, Chapter chapter, Series series) {
        try {
            ReviewDAO.saveReview(review, chapter, series);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveChapter(Chapter chapter,String seriesTitle) {
        Statement stmt = null;
        Connection conn = null;

        try {

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.2: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.insertChapter(stmt,chapter,seriesTitle);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
