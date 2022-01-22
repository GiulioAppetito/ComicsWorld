package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.Reader;
import java.io.*;
import java.sql.*;

public class Queries {

    private Queries(){}

    public static void updateCredentials(Statement stmt, String newName, String newSurname, String newEmail, String oldUsername) throws SQLException {
        String updateStatement = String.format("UPDATE users set firstname='%s', lastname='%s', email='%s' WHERE username = '%s' ", newName,newSurname,newEmail,oldUsername);
        stmt.executeUpdate(updateStatement);
    }

    public static void updateUsername(Statement stmt, String newUsername, String oldUsername) throws SQLException {
        String updateStatement = String.format("UPDATE users SET username = '%s' WHERE username = '%s' ", newUsername, oldUsername);
        stmt.executeUpdate(updateStatement);
    }

    public static ResultSet retriveLatestSeries(Statement stmt) throws SQLException {
        String selectStatement = "SELECT * FROM series";
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retrieveSeries(Statement stmt, String title) throws SQLException {
        String selectStatement = String.format("SELECT * FROM series where title = '%s'", title);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveFavouriteSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userFavouriteSeries where user = '%s' ", user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retrivePublishedSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from series where author = '%s' ", user);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveToReadSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userToReadSeries where user = '%s' ", user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveReadingSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userReadChapters where reader = '%s' ", user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }



    public static ResultSet retriveChapters(Statement stmt, String seriesTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM chapters WHERE series_title = '%s' ", seriesTitle);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet checkSignedUserByEmail(Statement stmt, String credential) throws SQLException {
        String selectStatement = String.format("SELECT * FROM users WHERE (email = '%s' OR username = '%s')", credential, credential);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static void addProfile(Statement stmt, String firstName, String lastName, String username, String email, String password, String role) throws SQLException {

        String insertStatement = String.format("INSERT INTO `users`(firstname,lastname,username,email,password,role) VALUES ('%s','%s','%s','%s','%s','%s')", firstName,lastName,username,email,password,role);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);

    }

    public static ResultSet retrieveReviewsByReaderAndSeries(Statement stmt, String seriesTitle, String user) throws SQLException {
        String selectStatement = String.format("SELECT * FROM review WHERE series_title = '%s' AND user = '%s' ", seriesTitle, user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);

    }

    public static ResultSet retreiveReviewsByChapter(Statement stmt, String chapterTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM review WHERE chapter_title = '%s'",chapterTitle);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);

    }

    public static ResultSet retrieveUser(Statement stmt, String identifier, String password) throws SQLException {
        String selectStatement = String.format("SELECT * FROM users WHERE (username = '%s' OR email = '%s')  AND password = '%s'",identifier,identifier,password);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static int saveReview(Statement stmt, Review review, Chapter chapter, Series series) throws SQLException {
        String insertStatement = String.format("INSERT INTO review (user, comment, series_title, chapter_title, rating) VALUES ('%s','%s','%s','%s','%s')", review.getUsername(), review.getComment(), series.getTitle(), chapter.getTitle(), review.getRating());
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
        return 0;
    }

    public static ResultSet retreiveObjectivesBySeries(Statement stmt,String seriesTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM objectives WHERE seriesTitle = '%s'",seriesTitle);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retreiveBadgeByID(Statement stmt, int badgeID) throws SQLException {
        String selectStatement = String.format("SELECT * FROM badges WHERE badgeID = '%d'",badgeID);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }


    public static void saveReadersDiscountCode(Statement stmt, DiscountCode discountCode, Reader reader) throws SQLException {
        String insertStatement = String.format("INSERT INTO discountCodes (username, code, expiringDate, percentage) values ('%s', '%s', '%s', '%s')", reader.getUsername(), discountCode.getCode(), discountCode.getExpiringDate(), discountCode.getDiscount().getPercentage());
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void addSeriesToFavourites(Statement stmt, String seriesTitle, String username) throws SQLException {
        String insertStatement = String.format("INSERT INTO userFavouriteSeries(user, series) values ('%s', '%s')", username, seriesTitle);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void removeSeriesFromFavourites(Statement stmt, String seriesTitle, String username) throws SQLException {
        String insertStatement = String.format("DELETE FROM userFavouriteSeries WHERE user = '%s' AND series = '%s'", username, seriesTitle);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void addSeriesToToRead(Statement stmt, Series series, Reader reader) throws SQLException {
        String insertStatement = String.format("INSERT INTO userToReadSeries (user, series) values ('%s', '%s')", reader.getUsername(),series.getTitle());
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void removeSeriesFromToRead(Statement stmt, Series series, Reader reader) throws SQLException {
        String deleteStatement = String.format("DELETE FROM userToReadSeries WHERE user = '%s' AND series = '%s'", reader.getUsername(), series.getTitle());
        System.out.println(deleteStatement);
        stmt.executeUpdate(deleteStatement);
    }

    public static ResultSet retrieveBadgesByReader(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT * FROM achievedBadges join badges on achievedbadges_id = badgeID WHERE user = '%s'", username);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static void addAchievedBadge(Statement stmt, String username, int achievedbadgeID) throws SQLException {
        String insertStatement = String.format("INSERT INTO achievedBadges (user, achievedbadges_id) values ('%s', '%d')", username, achievedbadgeID);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertChapter(Connection connection, Chapter chapter,String seriesTitle,InputStream coverInputStream) throws SQLException{


        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO chapters (series_title,chapter_title,chapterDescription,chapterCover) values (?, ?,?,?)");
        try{
            pstmt.setString(1, seriesTitle);
            pstmt.setString(2,chapter.getTitle());
            pstmt.setString(3,chapter.getDescription());

            //Inserting Blob type
            pstmt.setBlob(4, coverInputStream);
            System.out.println(pstmt);
            //Executing the statement
            pstmt.execute();
        }
        catch (Exception e){
            //TO-DO
        }
        finally {
            pstmt.close();
        }
    }

    public static ResultSet retreiveAuthor(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT * FROM users WHERE (username = '%s')  ",username);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static void addFollowedAuthor(Statement stmt, Author author) throws SQLException {
        String insertStatement = String.format("INSERT INTO followedAuthors (reader,author) values ('%s', '%s')", UserLogin.getInstance().getAccount().getUsername(), author.getUsername());
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet retreiveFollowedAuthors(Statement stmt, String readerUsername) throws SQLException {
        String selectStatement = String.format("SELECT * FROM followedAuthors WHERE (reader = '%s')  ",readerUsername);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static void removeFollowedAuthor(Statement stmt, Reader reader,Author author) throws SQLException {
        String deleteStatement = String.format("DELETE FROM followedAuthors WHERE reader = '%s' AND author = '%s'", reader.getUsername(), author.getUsername());
        System.out.println(deleteStatement);
        stmt.executeUpdate(deleteStatement);
    }

    public static void addReadChapter(Statement stmt,Reader reader, Series series, String title) throws SQLException {
        String insertStatement = String.format("INSERT INTO userReadChapters (reader,chapter,series) values ('%s', '%s','%s')",reader.getUsername(),title,series.getTitle());
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet isChapterRead(Statement stmt, String chapterTitle, String reader) throws SQLException {
        String selectStatement = String.format("SELECT * FROM userReadChapters WHERE (reader = '%s' AND chapter='%s')",reader,chapterTitle);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static void updateUserProPic(Connection conn, InputStream inputStream, Reader reader) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE username = ?");
        try{
            //Inserting Blob type
            pstmt.setString(1,reader.getUsername());
            //Executing the statement
            pstmt.execute();
            System.out.println(pstmt);
        }
        catch (Exception e){
            //TO-DO
        }
        PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO users (username,firstname,lastname,role,email,password,propic) VALUES (?,?,?,?,?,?,?)");
        try{
            //Inserting Blob type
            pstmt2.setBlob(7, inputStream);
            pstmt2.setString(1,reader.getUsername());
            pstmt2.setString(2,reader.getFirstName());
            pstmt2.setString(3,reader.getLastName());
            pstmt2.setString(4,"reader");
            pstmt2.setString(5,reader.getEmail());
            pstmt2.setString(6,reader.getPassword());
            //Executing the statement
            pstmt2.execute();
            System.out.println(pstmt2);
        }
        catch (Exception e){
            //TO-DO
        }
        finally {
            pstmt.close();
            pstmt2.close();
        }
    }

    public static ResultSet retrieveCategorySeries(Statement stmt, Genres genre) throws SQLException {
        String selectStatement = String.format("SELECT * FROM series WHERE genre1 = '%s' OR genre2 = '%s' OR genre3 = '%s'", genre.name(), genre.name(), genre.name());
        return stmt.executeQuery(selectStatement);
    }

    public static void insertSeries(Connection conn, Series series, InputStream seriesCoverInputStream) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO series (title,author,genre1,genre2,genre3,cover) values (?,?,?,?,?,?)");
        try{
            pstmt.setString(1, series.getTitle());
            pstmt.setString(2,series.getAuthor().getUsername());
            pstmt.setString(3,series.getGenre1().name());
            pstmt.setString(4,series.getGenre2().name());
            pstmt.setString(5,series.getGenre3().name());

            //Inserting Blob type
            pstmt.setBlob(6, seriesCoverInputStream);
            System.out.println(pstmt);
            //Executing the statement
            pstmt.execute();
        }
        catch (Exception e){
            //TO-DO
        }
        finally {
            pstmt.close();
        }
    }

    public static void insertObjective(Statement stmt, Objective objective,Series series,int bagdeID) throws SQLException {
        int limitDays = objective.getDiscount().getLimitDays();
        String seriesTitle = series.getTitle();
        String level = objective.getLevel().toString();
        String type ;
        switch (objective.getType()){
            case "reviewsObjective":
                type =  "reviews";
                break;
            case "chaptersObjective":
                type = "chapters";
                break;
            default:
                type = "";
        }

        Float discountPercentage = objective.getDiscount().getPercentage();

        String insertStatement = String.format("INSERT INTO objectives (limitDays,level,type,seriesTitle,discountPercentage,associatedBadgeID) values ('%s', '%s','%s','%s','%s','%d')",limitDays,level,type,seriesTitle,discountPercentage,bagdeID);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertBadge(Connection conn,Badge badge,InputStream badgeIconStream) throws SQLException {


        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO badges (badgeName,badgeIcon) values (?, ?)");
        try{
            pstmt.setString(1, badge.getName());

            //Inserting Blob type
            pstmt.setBlob(2, badgeIconStream);
            System.out.println(pstmt);

            //Executing the statement
            pstmt.execute();
        }
        catch (Exception e){
            //TO-DO
        }
        finally {
            pstmt.close();
        }
    }

    public static ResultSet getAllBadges(Statement stmt) throws SQLException {
        String selectStatement = String.format("SELECT * FROM badges");
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);

    }
}