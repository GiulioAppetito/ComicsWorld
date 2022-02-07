package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Queries;
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
                chapter.setPrice(rs.getFloat("chapterPrice"));
                chapter.setDescription(rs.getString("chapterDescription"));
                Blob bl = rs.getBlob("chapterCover");
                if(bl != null){
                    InputStream inputStream = bl.getBinaryStream();
                    Image image = new Image(inputStream);
                    chapter.setCover(image);
                }

                if(UserLogin.getInstance().getAccount().getRole().equals("reader")) {
                    System.out.println("### [ChapterDAO] setting read or not to chapter.");
                    rs2 = Queries.isChapterRead(stmt2, chapterTitle, UserLogin.getInstance().getAccount().getUsername());
                    //il capitolo non è letto
                    if(!rs2.first()){
                        chapter.setRead(false);
                        System.out.println("### [ChapterDAO] setted FALSE.");
                    }else{
                        chapter.setRead(true);
                        System.out.println("### [ChapterDAO] setted TRUE.");

                    }

                }
                chaptersList.add(chapter);
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
            try {
                if (stmt2 != null)
                    stmt2.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn2 != null)
                    conn2.close();
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

    public void saveChapter(Chapter chapter,String seriesTitle,InputStream coverInputStream) {

        Connection conn;

        try {
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.2: creazione ed esecuzione della query
            Queries.insertChapter(conn,chapter,seriesTitle,coverInputStream);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
