package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Queries;
import com.example.comics.model.exceptions.AlreadyExistingChapterException;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    public List<Chapter> retriveChapters(String seriesTitle) {

        Statement stmt14 = null;
        Connection conn14 = null;

        Connection conn15 = null;
        Statement stmt15 = null;


        List<Chapter> chaptersList = new ArrayList<>();

        String chapterTitle;
        Chapter chapter;

        try {
            conn14 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt14 = conn14.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveChapters(stmt14, seriesTitle);

            conn15 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt15 = conn15.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs2;

            if (!rs.first()) {
                return chaptersList;
            }
            rs.first();

            do {
                chapterTitle = rs.getString("chapter_title");
                chapter = new Chapter(chapterTitle);
                chapter.setPrice(rs.getFloat("chapterPrice"));
                chapter.setDescription(rs.getString("chapterDescription"));
                Blob bl = rs.getBlob("chapterCover");
                if(bl != null){
                    InputStream inputStream = bl.getBinaryStream();
                    Image image = new Image(inputStream);
                    chapter.setCover(image);
                }

                    rs2 = Queries.isChapterRead(stmt15, chapterTitle, UserLogin.getInstance().getAccount().getUsername());
                    //il capitolo non è letto
                chapter.setRead(rs2.first());


                chaptersList.add(chapter);
            } while (rs.next());




        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt14 != null)
                    stmt14.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn14 != null)
                    conn14.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (stmt15 != null)
                    stmt15.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn15 != null)
                    conn15.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return chaptersList;
    }

    public List<Review> retrieveReviews(String chapter) {
        ReviewDAO reviewDAO = new ReviewDAO();
        return reviewDAO.retrieveReviews(chapter);

    }

    public void saveChapter(Chapter chapter,String seriesTitle,InputStream coverInputStream) throws AlreadyExistingChapterException {

        Connection conn16;

        try {
            // STEP 3: apertura connessione
            conn16 = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.2: creazione ed esecuzione della query
            Queries.insertChapter(conn16,chapter,seriesTitle,coverInputStream);


        } catch (SQLException throwables) {
            throw new AlreadyExistingChapterException("Unavailable title! Try another one.");
        }
    }
}
