package com.example.comics.model.dao;

import com.example.comics.model.DiscountCode;
import com.example.comics.model.Reader;
import com.example.comics.model.Review;
import com.example.comics.model.Series;

import java.sql.*;

public class Queries {

    private Queries(){}

    public static void updateCredentials(Statement stmt, String newName, String newSurname, String oldUsername, String newUsername) throws SQLException {
        String updateStatement = String.format("UPDATE users set firstname='%s', lastname='%s', username='%s' WHERE username = '%s' ", newName,newSurname,newUsername,oldUsername);
        stmt.executeUpdate(updateStatement);
    }

    public static ResultSet retreiveCredentials(Statement stmt, String username) throws SQLException {
        String query = String.format("SELECT * FROM users WHERE username = '%s'", username);
        return stmt.executeQuery(query);

    }

    public static void updateEmail(Statement stmt, String newEmail, String password) throws SQLException {
        String updateStatement = String.format("UPDATE users set email='%s' WHERE password = '%s' ", newEmail, password);
        stmt.executeUpdate(updateStatement);
    }

    public static ResultSet retriveLatestSeries(Statement stmt) throws SQLException {
        String selectStatement ="SELECT * FROM series";
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retrieveSeries(Statement stmt, String title) throws SQLException {
        String selectStatement = String.format("SELECT * FROM series where title = '%s'", title);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveFavouriteSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userFavouriteSeries where user = '%s' ", user);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retrivePublishedSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from publishedSeries where user = '%s' ", user);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveToReadSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userToReadSeries where user = '%s' ", user);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveReadingSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userReadingSeries where user = '%s' ", user);
        return stmt.executeQuery(selectStatement);
    }



    public static ResultSet retriveChapters(Statement stmt, String seriesTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM chapters WHERE series_title = '%s' ", seriesTitle);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet checkSignedUserByEmail(Statement stmt, String credential, String password) throws SQLException {
        String selectStatement = String.format("SELECT * FROM users WHERE (email = '%s' OR username = '%s') AND password = '%s'", credential, credential, password);
        return stmt.executeQuery(selectStatement);
    }

    public static void addProfile(Statement stmt, String firstName, String lastName, String username, String email, String password, String role) throws AlreadyUsedUsernameException {

        try{
            String insertStatement = String.format("INSERT INTO `users`(firstname,lastname,username,email,password,role) VALUES ('%s','%s','%s','%s','%s','%s')", firstName,lastName,username,email,password,role);
            stmt.executeUpdate(insertStatement);

        }
        catch (SQLIntegrityConstraintViolationException e){
            throw new AlreadyUsedUsernameException();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ResultSet retrieveReviewsByReader(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * FROM review WHERE user = '%s' ",user);
        return stmt.executeQuery(selectStatement);

    }

    public static ResultSet retreiveReviewsByChapter(Statement stmt, String series, String chapter) throws SQLException {
        String selectStatement = String.format("SELECT * FROM review WHERE seriesTitle = '%s' AND chapter_id = '%s'",series,chapter);
        return stmt.executeQuery(selectStatement);

    }

    public static ResultSet retreiveReader(Statement stmt, String identifier, String password) throws SQLException {
        String selectStatement = String.format("SELECT * FROM users WHERE (username = '%s' OR email = '%s')  AND password = '%s'",identifier,identifier,password);
        return stmt.executeQuery(selectStatement);
    }

    public static int saveReview(Statement stmt, Review review) throws SQLException {
        String insertStatement = String.format("INSERT INTO review (user, comment, seriesTitle, chapter_id) VALUES ('%s','%s','%s','%s')", review.getUsername(), review.getComment(),review.getSeries(),review.getChapter());
        stmt.executeUpdate(insertStatement);
        return 0;
    }

    public static ResultSet retreiveObjectivesBySeries(Statement stmt,String seriesTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM objectives WHERE seriesTitle = '%s'",seriesTitle);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retreiveBadgeByID(Statement stmt, int badgeID) throws SQLException {
        String selectStatement = String.format("SELECT * FROM badges WHERE badgeID = '%d'",badgeID);
        return stmt.executeQuery(selectStatement);
    }


    public static void saveReadersDiscountCode(Statement stmt, DiscountCode discountCode, Reader reader) throws SQLException {
        String insertStatement = String.format("INSERT INTO discountCodes (username, code, expiringDate, percentage) values ('%s', '%s', '%s', '%s')", reader.getUsername(), discountCode.getCode(), discountCode.getExpiringDate(), discountCode.getDiscount().getPercentage());
        stmt.executeUpdate(insertStatement);
    }

    public static void addSeriesToFavourites(Statement stmt, String seriesTitle, String username) throws SQLException {
        String insertStatement = String.format("INSERT INTO userFavouriteSeries(user, series) values ('%s', '%s')", username, seriesTitle);
        stmt.executeUpdate(insertStatement);
    }

    public static void removeSeriesFromFavourites(Statement stmt, String seriesTitle, String username) throws SQLException {
        String insertStatement = String.format("DELETE FROM userFavouriteSeries WHERE user = '%s' AND series = '%s'", username, seriesTitle);
        stmt.executeUpdate(insertStatement);
    }

    public static void addSeriesToToRead(Statement stmt, Series series, Reader reader) throws SQLException {
        String insertStatement = String.format("INSERT INTO userToReadSeries (user, series) values ('%s', '%s')", reader.getUsername(),series.getTitle());
        stmt.executeUpdate(insertStatement);
    }

    public static void removeSeriesFromToRead(Statement stmt, Series series, Reader reader) throws SQLException {
        String deleteStatement = String.format("DELETE FROM userToReadSeries WHERE user = '%s' AND series = '%s'", reader.getUsername(), series.getTitle());
        stmt.executeUpdate(deleteStatement);
    }

    public static ResultSet retrieveBadgesByReader(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT * FROM achievedBadges join badges on achievedbadges_id = badgeID WHERE user = '%s'", username);
        return stmt.executeQuery(selectStatement);
    }

    public static void addAchievedBadge(Statement stmt, String username, int achievedbadgeID) throws SQLException {
        String insertStatement = String.format("INSERT INTO achievedBadges (user, achievedbadges_id) values ('%s', '%d')", username, achievedbadgeID);
        stmt.executeUpdate(insertStatement);
    }
}