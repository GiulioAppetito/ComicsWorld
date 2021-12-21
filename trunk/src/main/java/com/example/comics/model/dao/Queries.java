package com.example.comics.model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

public class Queries {


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

    public static ResultSet retriveChapters(Statement stmt, String seriesTitle) throws SQLException {
        String selectStatement = String.format("SELECT * FROM chapters WHERE series_title = '%s' ", seriesTitle);
        System.out.println(selectStatement);
        return stmt.executeQuery(selectStatement);
    }



    //veche query


    //query to check if a user is signed or not
    public static ResultSet checkSignedUserByUsername(Statement stmt, String username) throws SQLException {

        String sql = "SELECT * FROM users WHERE username = '" + username + "';";

        System.out.println(sql);
        return stmt.executeQuery(sql);

    }

    public static ResultSet checkSignedUserByEmail(Statement stmt, String email) throws SQLException {

        String sql;
        sql = "SELECT * FROM users WHERE email = '" + email + "' or username = '" + email + "';";

        System.out.println(sql);
        return stmt.executeQuery(sql);

    }





    public static ResultSet getUsers(Statement stmt,String name) throws SQLException {
        String sql="SELECT * FROM users WHERE name = '" + name + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }






    //query to add a new Course to the DB

    //query to insert a new user
    public static int addProfile(Statement stmt,String name, String surname, String username, String email,String password,String type,String location) throws SQLException {
        String insertStatement = String.format("INSERT INTO `users`(name,surname,username,email,password,type,location) VALUES ('%s','%s','%s','%s','%s','%s','%s')", name,surname,username,email,password,type,location);
        System.out.println("in Queries ho:" + username);
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);


    }

    public static ResultSet doesThisUsernameAlreadyExist(Statement stmt, String username) throws SQLException {

        String sql = "SELECT * FROM users WHERE username = '" + username + "';";

        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet doesThisEmailAlreadyExist(Statement stmt, String email) throws SQLException {

        String sql = "SELECT * FROM users WHERE email = '" + email + "';";

        System.out.println(sql);
        return stmt.executeQuery(sql);
    }


    public static int updateCredentialsNoUsername(Statement stmt, String newName,String newSurname,String oldUsername) throws SQLException {
        String updateStatement = String.format("UPDATE users set name='%s', surname='%s' WHERE username = '%s' ", newName,newSurname,oldUsername);
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
    }

    public static int updatePassword(Statement stmt,String newPassword,String username) throws SQLException {
        String updateStatement = String.format("UPDATE users set password='%s' WHERE username = '%s' ",newPassword,username);
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);

    }



/*
public static void main(String[] args) throws Exception, IOException, SQLException {
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager
.getConnection("jdbc:mysql://localhost/databaseName", "username", "password");
String INSERT_PICTURE = "INSERT INTO my_picures(id, name, photo) VALUES (?, ?, ?)";

conn.setAutoCommit(false);
File file = new File("myPhoto.png");
try (FileInputStream fis = new FileInputStream(file);
PreparedStatement ps = conn.prepareStatement(INSERT_PICTURE)) {
ps.setString(1, "001");
ps.setString(2, "name");
ps.setBinaryStream(3, fis, (int) file.length());
ps.executeUpdate();
conn.commit();
}
}
*/

//query per inserire una immagine sul DB



    public static int addProPic(Statement stmt, String proPic) {

        String insertStatement = String.format("INSERT INTO proPics(proPic) VALUES (?)");


        System.out.println(insertStatement);
        try {
            return stmt.executeUpdate(insertStatement);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;


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
}