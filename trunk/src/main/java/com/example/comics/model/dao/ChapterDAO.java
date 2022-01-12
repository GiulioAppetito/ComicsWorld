package com.example.comics.model.dao;

import com.example.comics.model.*;

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

        Connection conn2 = null;
        Statement stmt2 = null;


        List<Chapter> chaptersList = new ArrayList<>();

        String chapterTitle;
        Chapter chapter;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("ChapterDAO: sto andando a recuperare i capitoli");
            ResultSet rs = Queries.retriveChapters(stmt, seriesTitle);

            conn2 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt2 = conn2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs2;

            if (!rs.first()) {
                return chaptersList;
            }
            rs.first();

            do {
                chapterTitle = rs.getString("chapter_title");
                chapter = new Chapter(chapterTitle);
                chapter.setDescription(rs.getString("chapterDescription"));

                rs2 = Queries.isChapterRead(stmt2,chapterTitle, UserLogin.getInstance().getUsername());
                //il capitolo non è letto
                chapter.setRead(rs2.first());


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

    public List<Review> retrieveReviewsByReader(Series series, Reader reader) {
        ReviewDAO reviewDAO = new ReviewDAO();
        return reviewDAO.retrieveReviewsByReaderAndSeries(series,reader);

    }

    public List<Review> retrieveReviews(String chapter) {
        ReviewDAO reviewDAO = new ReviewDAO();
        return reviewDAO.retrieveReviews(chapter);

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
