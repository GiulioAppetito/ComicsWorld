package com.example.comics.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {

    //query to check if a user is signed or not
    public static ResultSet checkSignedUserByUsername(Statement stmt, String username) throws SQLException {

        String sql = "SELECT * FROM users WHERE username = '" + username + "';";

        System.out.println(sql);
        return stmt.executeQuery(sql);

    }

    public static ResultSet checkSignedUserByEmail(Statement stmt, String email) throws SQLException {

        String sql = "SELECT * FROM users WHERE email = '" + email + "';";

        System.out.println(sql);
        return stmt.executeQuery(sql);

    }





    public static ResultSet getUsers(Statement stmt,String name) throws SQLException {
        String sql="SELECT * FROM users WHERE name = '" + name + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

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

    //per il singolo utente
    public static int updateCredentials(Statement stmt, String newName,String newSurname,String newUsername,String oldUsername) throws SQLException {
        String updateStatement = String.format("UPDATE users set name='%s', surname='%s', username='%s' WHERE username = '%s' ", newName,newSurname,newUsername,oldUsername);
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
    }

    public static int updateCredentialsNoUsername(Statement stmt, String newName,String newSurname,String oldUsername) throws SQLException {
        String updateStatement = String.format("UPDATE users set name='%s', surname='%s' WHERE username = '%s' ", newName,newSurname,oldUsername);
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
    }

    public static int updateEmail(Statement stmt,String newEmail,String username) throws SQLException {
        String updateStatement = String.format("UPDATE users set email='%s' WHERE username = '%s' ",newEmail,username);
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



}