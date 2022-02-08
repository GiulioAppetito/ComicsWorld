package com.example.comics.model.dao.utils;

import com.example.comics.model.*;
import com.example.comics.model.Reader;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;

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

    public static ResultSet retriveFavouriteSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userFavouriteSeries where user = '%s' ", user);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retrivePublishedSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from series where author = '%s' ", user);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveToReadSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userToReadSeries where user = '%s' ", user);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveReadingSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT DISTINCT series from userReadChapters where reader = '%s' ", user);
        return stmt.executeQuery(selectStatement);
    }



    public static ResultSet retriveChapters(Statement stmt, String seriesTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM chapters WHERE series_title = '%s' ", seriesTitle);
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

    public static ResultSet retreiveReviewsByChapter(Statement stmt, String chapterTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM review WHERE chapter_title = '%s'",chapterTitle);
        return stmt.executeQuery(selectStatement);

    }

    public static ResultSet retrieveUser(Statement stmt, String identifier, String password) throws SQLException {
        String selectStatement = String.format("SELECT * FROM users WHERE (username = '%s' OR email = '%s')  AND password = '%s'",identifier,identifier,password);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static int saveReview(Statement stmt, Review review, Chapter chapter, Series series) throws SQLException {
        String insertStatement = String.format("INSERT INTO review (user, comment, series_title, chapter_title, rating) VALUES ('%s','%s','%s','%s','%s')", review.getAccount().getUsername(), review.getComment(), series.getTitle(), chapter.getTitle(), review.getRating());
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


    public static void saveReadersDiscountCode(Statement stmt, DiscountCode discountCode, Reader reader,Series series,Objective objective) throws SQLException {

        String insertStatement = String.format("INSERT INTO discountCodes (username, code, expiringDate, percentage,startingDate,series,objectiveID) values ('%s', '%s', '%s', '%s','%s','%s','%d')", reader.getUsername(), discountCode.getCode(), DatesConverter.toString(discountCode.getExpiringDate()), discountCode.getDiscount().getPercentage(),DatesConverter.toString(LocalDate.now()),series.getTitle(),objective.getBadge().getId());
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


        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO chapters (series_title,chapter_title,chapterDescription,chapterCover,chapterPrice) values (?, ?,?,?,?)");

            pstmt.setString(1, seriesTitle);
            pstmt.setString(2,chapter.getTitle());
            pstmt.setString(3,chapter.getDescription());
            pstmt.setFloat(5,chapter.getPrice());

            //Inserting Blob type
            pstmt.setBlob(4, coverInputStream);
            System.out.println(pstmt);
            //Executing the statement
            pstmt.execute();

    }

    public static ResultSet retreiveAuthor(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT * FROM users WHERE (username = '%s')  ",username);
        return stmt.executeQuery(selectStatement);
    }

    public static void addFollowedAuthor(Statement stmt, Author author) throws SQLException {
        String insertStatement = String.format("INSERT INTO followedAuthors (reader,author) values ('%s', '%s')", UserLogin.getInstance().getAccount().getUsername(), author.getUsername());

        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet retreiveFollowedAuthors(Statement stmt, String readerUsername) throws SQLException {
        String selectStatement = String.format("SELECT * FROM followedAuthors WHERE (reader = '%s')  ",readerUsername);

        return stmt.executeQuery(selectStatement);
    }

    public static void removeFollowedAuthor(Statement stmt, Reader reader,Author author) throws SQLException {
        String deleteStatement = String.format("DELETE FROM followedAuthors WHERE reader = '%s' AND author = '%s'", reader.getUsername(), author.getUsername());

        stmt.executeUpdate(deleteStatement);
    }

    public static void addReadChapter(Statement stmt,Reader reader, Series series, String title) throws SQLException {
        String insertStatement = String.format("INSERT INTO userReadChapters (reader,chapter,series) values ('%s', '%s','%s')",reader.getUsername(),title,series.getTitle());

        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet isChapterRead(Statement stmt, String chapterTitle, String reader) throws SQLException {
        String selectStatement = String.format("SELECT * FROM userReadChapters WHERE (reader = '%s' AND chapter='%s')",reader,chapterTitle);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static void updateUserProPic(Connection conn, InputStream inputStream, Account account) throws SQLException{
        if(inputStream==null){
            System.out.println("null input stream");
        }
        try(PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET propic = ? WHERE username = ?")){
            try{
                pstmt.setString(2, account.getUsername());
                pstmt.setBlob(1, inputStream);

                pstmt.execute();
                System.out.println(pstmt);
            } catch (Exception e) {
                //TO-DO
            }
        }
    }

    public static void insertSeries(Connection conn, Series series, InputStream seriesCoverInputStream) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO series (title,author,genre1,genre2,genre3,cover,description) values (?,?,?,?,?,?,?)");
        try{
            pstmt.setString(1, series.getTitle());
            pstmt.setString(2,series.getAuthor().getUsername());
            pstmt.setString(3,series.getGenre1().name());
            pstmt.setString(4,series.getGenre2().name());
            pstmt.setString(5,series.getGenre3().name());
            pstmt.setString(7,series.getDescription());

            //Inserting Blob type
            pstmt.setBlob(6, seriesCoverInputStream);
            System.out.println(pstmt);
            //Executing the statement
            pstmt.execute();
        }
        finally {
            pstmt.close();
        }
    }

    public static void insertObjective(Statement stmt, Objective objective,Series series,int bagdeID) throws SQLException {
        int limitDays = objective.getDiscount().getLimitDays();
        String seriesTitle = series.getTitle();
        String level = objective.getLevel().toString();
        String type;
        Float requirement = objective.getRequirement();
        switch (objective.getType()){
            case "reviews":
                type =  "reviews";
                break;
            case "chapters":
                type = "chapters";
                break;
            default:
                type = "";
        }

        Float discountPercentage = objective.getDiscount().getPercentage();

        String insertStatement = String.format("INSERT INTO objectives (limitDays,level,type,seriesTitle,discountPercentage,associatedBadgeID,number) values ('%s', '%s','%s','%s','%s','%d','%s')",limitDays,level,type,seriesTitle,discountPercentage,bagdeID,requirement);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertBadge(Connection conn,Badge badge,InputStream badgeIconStream) throws SQLException {


        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO badges (badgeName,badgeIcon) values (?, ?)");
        try{
            pstmt.setString(1, badge.getName());

            //Inserting Blob type
            pstmt.setBlob(2, badgeIconStream);

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
        String selectStatement = "SELECT * FROM badges";
        return stmt.executeQuery(selectStatement);

    }

    public static ResultSet retreiveDiscountCodesByReader(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT * FROM discountCodes WHERE (username = '%s')  ",username);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static void deleteDiscountCode(Statement stmt, Reader reader, DiscountCode discountCode) throws SQLException {
        String deleteStatement = String.format("DELETE FROM discountCodes WHERE (username = '%s' AND code = '%s')  ",reader.getUsername(),discountCode.getCode());
        System.out.println(deleteStatement);
        stmt.executeUpdate(deleteStatement);
    }

    public static void removeChapterFromRead(Statement stmt, Series series, String chapterTitle, Reader reader) throws SQLException {
        String deleteStatement = String.format("DELETE FROM userReadChapters WHERE (reader = '%s' AND series = '%s' AND chapter = '%s')  ",reader.getUsername(),series.getTitle(),chapterTitle);
        System.out.println(deleteStatement);
        stmt.executeUpdate(deleteStatement);
    }


    public static ResultSet retreiveOrdersByReader(Statement stmt, String reader) throws SQLException {
        String selectStatement = String.format("SELECT * FROM orders WHERE (reader = '%s')",reader);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retreiveAllAuthors(Statement stmt) throws SQLException {
        String selectStatement = String.format("SELECT * FROM users WHERE (role = 'author')");
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static void insertOrder(Statement stmt, Order order, Reader reader) throws SQLException {
        String insertStatement = String.format("INSERT INTO orders values ('%s', '%s', '%s', '%s', '%s')", order.getSeries().getTitle(), order.getChapterTitle(), order.getExpense(), order.getDate(), reader.getUsername());
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet retreiveObjectivesByDiscountCode(Statement stmt, int badgeID) throws SQLException {
        String selectStatement = String.format("SELECT DISTINCT seriesTitle FROM objectives WHERE (associatedBadgeID = '%d')",badgeID);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retrieveFollowersMails(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT DISTINCT email FROM followedAuthors JOIN users ON reader = username WHERE (author = '%s')",username);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }
}