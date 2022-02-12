package com.example.comics.model.dao;

import com.example.comics.model.Account;
import com.example.comics.model.Author;
import com.example.comics.model.Reader;
import com.example.comics.model.dao.utils.Queries;
import com.example.comics.model.exceptions.FailedLoginException;
import com.example.comics.model.exceptions.FailedProfileCustomizationException;
import com.example.comics.model.exceptions.FailedRegistrationException;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";


    public String verifyCredentials(String credential,String password) throws FailedLoginException {

        //dichiarazioni
        Statement stmt1=null;
        Connection conn1=null;
        String role=null;

        try {
            conn1= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt1 = conn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.checkSignedUserByEmail(stmt1, credential);

            if (!rs.first()){
                throw new FailedLoginException("Username not registered!");
            }
            rs.first();
            do{
                //verificare se la password � corretta
                String foundPassword=rs.getString("password");
                if(foundPassword.equals(password)) {
                    role = rs.getString("role");
                }else{
                    throw new FailedLoginException("Wrong password!");
                }


            }while(rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt1 != null)
                    stmt1.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return role;


    }

    public void changeCredentials(String newName, String newSurname, String email, String newUsername){

        Statement stmt2=null;
        Connection conn2=null;


        try {
            conn2= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt2 = conn2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.updateCredentials(stmt2, newName, newSurname, email, newUsername);


        }
        catch (SQLException throwables) {
            //to-do
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    }

    public void changeUsername(String newUsername, String oldUsername) throws FailedProfileCustomizationException {

        Statement stmt3=null;
        Connection conn3=null;


        try {
            conn3= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt3 = conn3.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.updateUsername(stmt3, newUsername, oldUsername);


        } catch (SQLException e) {
            throw new FailedProfileCustomizationException("This username is already used!");
        } finally {
            try {
                if (stmt3 != null)
                    stmt3.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn3 != null)
                    conn3.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }

    }

    public void registerNewAccount(String firstName, String lastName, String username, String email, String password, String role) throws FailedRegistrationException {
        Statement stmt4=null;
        Connection conn4=null;

        try {
            conn4=DriverManager.getConnection(DB_URL,USER,PASS);
            stmt4 = conn4.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.addProfile(stmt4, firstName, lastName, username, email, password,role);

        }
        catch (SQLException se) {
            throw new FailedRegistrationException("Username is already used.");
        }
        finally {
            try {
                if (stmt4 != null)
                    stmt4.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn4 != null)
                    conn4.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
    }

    public void changeProPic(InputStream inputStream, Reader reader) {
        Connection conn5 = null;

        try {
            conn5 = DriverManager.getConnection(DB_URL, USER, PASS);
            Queries.updateUserProPic(conn5,inputStream,reader);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Account retriveReviewAuthor(String username){
        Statement stmt6 = null;
        Connection conn6 = null;

        Author author = null;

        try {

            conn6 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt6 = conn6.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveAuthor(stmt6, username);

            if (!rs.first()) {
                return null;
            }
            rs.first();

            author = new Author();
            author.setFirstName(rs.getString("firstname"));
            author.setLastName(rs.getString("lastname"));
            author.setUsername(rs.getString("username"));
            author.setEmail(rs.getString("email"));


            Blob bl = rs.getBlob("proPic");
            if(bl != null){
                InputStream inputStream = bl.getBinaryStream();
                Image image = new Image(inputStream);
                author.setProPic(image);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            try {
                assert conn6 != null;
                conn6.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        return author;
    }

    public List<String> retreiveAuthorFollowersMails(String authorUsername) {
        Statement stmt7 = null;
        Connection conn7 = null;
        List<String> mails = new ArrayList<>();
        String mail;

        try {

            conn7 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt7 = conn7.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveFollowersMails(stmt7, authorUsername);

            if (!rs.first()) {
                return mails;
            }
            rs.first();

            do{
                mail = rs.getString("email");
                mails.add(mail);
            }while (rs.next());




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            try {
                assert conn7 != null;
                conn7.close();

            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }

            try{
                assert stmt7!= null;
                stmt7.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        return mails;
    }
}
