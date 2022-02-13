package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Connector;
import com.example.comics.model.dao.utils.Queries;
import com.example.comics.model.exceptions.AlreadyExistingChapterException;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterDAO {

    public List<Chapter> retriveChapters(String seriesTitle) {

        Statement stmt14 = null;
        Connection conn14;


        Statement stmt15 = null;


        List<Chapter> chaptersList = new ArrayList<>();

        String chapterTitle;
        Chapter chapter;

        try {
            conn14 = Connector.getInstance().getConnection();
            stmt14 = conn14.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retriveChapters(stmt14, seriesTitle);

            stmt15 = conn14.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
                if (stmt15 != null)
                    stmt15.close();
            } catch (SQLException se2) {
                //TO-DO
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
            conn16 = Connector.getInstance().getConnection();

            // STEP 4.2: creazione ed esecuzione della query
            Queries.insertChapter(conn16,chapter,seriesTitle,coverInputStream);


        } catch (SQLException throwables) {
            throw new AlreadyExistingChapterException("Unavailable title! Try another one.");
        }
    }
}
