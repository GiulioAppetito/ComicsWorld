package com.example.comics.model.dao;

import com.example.comics.model.*;

import java.sql.*;

public class Queries {

    private Queries(){}

    public static void updateCredentials(Statement stmt, String newName, String newSurname, String oldUsername, String newUsername) throws SQLException {
        String updateStatement = String.format("UPDATE users set firstname='%s', lastname='%s', username='%s' WHERE username = '%s' ", newName,newSurname,newUsername,oldUsername);
        System.out.println(updateStatement);
        stmt.executeUpdate(updateStatement);
    }

    public static ResultSet retreiveCredentials(Statement stmt, String username) throws SQLException {
        String query = String.format("SELECT * FROM users WHERE username = '%s'", username);
        System.out.println(query);
        return stmt.executeQuery(query);

    }

    public static void updateEmail(Statement stmt, String newEmail, String password) throws SQLException {
        String updateStatement = String.format("UPDATE users set email='%s' WHERE password = '%s' ", newEmail, password);
        System.out.println(updateStatement);
        stmt.executeUpdate(updateStatement);
    }

    public static ResultSet retriveLatestSeries(Statement stmt) throws SQLException {
        String selectStatement = String.format("SELECT * FROM series");
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retrieveSeries(Statement stmt, String title) throws SQLException {
        String selectStatement = String.format("SELECT * FROM series where title = '%s'", title);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveFavouriteSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userFavouriteSeries where user = '%s' ", user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retrivePublishedSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from series where author = '%s' ", user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveToReadSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userToReadSeries where user = '%s' ", user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveReadingSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userReadingSeries where user = '%s' ", user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }



    public static ResultSet retriveChapters(Statement stmt, String seriesTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM chapters WHERE series_title = '%s' ", seriesTitle);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet checkSignedUserByEmail(Statement stmt, String credential, String password) throws SQLException {
        String selectStatement = String.format("SELECT * FROM users WHERE (email = '%s' OR username = '%s') AND password = '%s'", credential, credential, password);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static void addProfile(Statement stmt, String firstName, String lastName, String username, String email, String password, String role) throws AlreadyUsedUsernameException {

        try{
/*
            File file = new File("immagine.jpg");
            InputStream fin = new java.io.FileInputStream(file);
            int fileLength = (int)file.length();
*/
            String insertStatement = String.format("INSERT INTO `users`(firstname,lastname,username,email,password,role) VALUES ('%s','%s','%s','%s','%s','%s')", firstName,lastName,username,email,password,role);
            System.out.println(insertStatement);
            stmt.executeUpdate(insertStatement);

            /*File file = new File("immagine.jpg");
            InputStream fin = new java.io.FileInputStream(file);
            int fileLength = (int)file.length();

            insertStatement = String.format("UPDATE INTO users (NAME, IMG) "+"VALUES (?, ?)");
            //pstmt.setString(1, file.getName());
            //pstmt.setBinaryStream (2, fin, fileLength);
            //pstmt.executeUpdate();*/
        }
        catch (SQLIntegrityConstraintViolationException e){
            throw new AlreadyUsedUsernameException();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ResultSet retrieveReviewsByReaderAndSeries(Statement stmt, String seriesTitle, String user) throws SQLException {
        String selectStatement = String.format("SELECT * FROM review WHERE series_title = '%s' AND user = '%s' ", seriesTitle, user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);

    }
    public static ResultSet retrieveReviewsByReader(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * FROM review WHERE user = '%s' ", user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);

    }

    public static ResultSet retreiveReviewsByChapter(Statement stmt, String chapterTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM review WHERE chapter_title = '%s'",chapterTitle);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);

    }

    public static ResultSet retreiveReader(Statement stmt, String identifier, String password) throws SQLException {
        String selectStatement = String.format("SELECT * FROM users WHERE (username = '%s' OR email = '%s')  AND password = '%s'",identifier,identifier,password);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static int saveReview(Statement stmt, Review review, String seriesTitle) throws SQLException {
        String insertStatement = String.format("INSERT INTO review (user, comment, series_title, chapter_title, rating) VALUES ('%s','%s','%s','%s','%s')", review.getUsername(), review.getComment(), seriesTitle,review.getChapter(), review.getRating());
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

    public static void addSeriesToFavourites(Statement stmt, String series_title, String username) throws SQLException {
        String insertStatement = String.format("INSERT INTO userFavouriteSeries(user, series) values ('%s', '%s')", username, series_title);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void removeSeriesFromFavourites(Statement stmt, String series_title, String username) throws SQLException {
        String insertStatement = String.format("DELETE FROM userFavouriteSeries WHERE user = '%s' AND series = '%s'", username, series_title);
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

    public static void addAchievedBadge(Statement stmt, String username, int achievedbadge_id) throws SQLException {
        String insertStatement = String.format("INSERT INTO achievedBadges (user, achievedbadges_id) values ('%s', '%d')", username, achievedbadge_id);
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertChapter(Statement stmt, Chapter chapter,String seriesTitle) throws SQLException {
        String insertStatement = String.format("INSERT INTO chapters (series_title,chapter_title,chapterDescription) values ('%s', '%s','%s')", seriesTitle, chapter.getTitle(),chapter.getDescription());
        System.out.println(insertStatement);
        stmt.executeUpdate(insertStatement);
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

    public static ResultSet retreiveFollowedAuthors(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT * FROM followedAuthors WHERE (reader = '%s')  ",username);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }
}