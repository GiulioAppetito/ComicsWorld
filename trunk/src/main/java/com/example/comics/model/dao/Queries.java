package com.example.comics.model.dao;

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

    public static void updateEmail(Statement stmt, String newEmail, String username) throws SQLException {
        String updateStatement = String.format("UPDATE users set email='%s' WHERE username = '%s' ", newEmail, username);
        System.out.println(updateStatement);
        stmt.executeUpdate(updateStatement);
    }

    public static ResultSet retriveLatestSeries(Statement stmt) throws SQLException {
        String selectStatement = String.format("SELECT * FROM series");
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveSeries(Statement stmt, String title) throws SQLException {
        String selectStatement = String.format("SELECT * FROM series where title = '%s'", title);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveFavouriteSeries(Statement stmt, String user) throws SQLException {
        String selectStatement = String.format("SELECT * from userFavouriteSeries where user = '%s' ", user);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet retriveChapters(Statement stmt, String seriesTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM chapters WHERE series_title = '%s' ", seriesTitle);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }

    public static ResultSet checkSignedUserByEmail(Statement stmt, String email) throws SQLException {

        String sql;
        sql = "SELECT * FROM users WHERE email = '" + email + "' or username = '" + email + "';";

        System.out.println(sql);
        return stmt.executeQuery(sql);

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

    public static ResultSet retreiveReviewsByChapter(Statement stmt, String series, String chapter) throws SQLException {
        String selectStatement = String.format("SELECT * FROM review WHERE series_title = '%s' AND chapter_id = '%s'",series,chapter);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);

    }
}