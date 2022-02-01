package com.example.comics.model.dao;

import com.example.comics.model.Account;
import com.example.comics.model.Author;
import com.example.comics.model.Reader;
import com.example.comics.model.dao.utils.Queries;
import com.example.comics.model.exceptions.FailedLoginException;
import com.example.comics.model.exceptions.FailedRegistrationException;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;

public class AccountDAO {
    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";


    public String verifyCredentials(String credential,String password) throws FailedLoginException {

        //dichiarazioni
        Statement stmt=null;
        Connection conn=null;
        String role=null;

        try {
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.checkSignedUserByEmail(stmt, credential);

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

        }
        return role;


    }

    public void changeCredentials(String newName, String newSurname, String email, String newUsername){

        Statement stmt=null;
        Connection conn=null;


        try {
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.updateCredentials(stmt, newName, newSurname, email, newUsername);


        }
        catch (SQLException throwables) {
            System.out.println("ECCEZIONE REGISTRAZIONE");
        } catch (Exception e) {
            e.printStackTrace();
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

        }

    }

    public void changeUsername(String newUsername, String oldUsername){

        Statement stmt=null;
        Connection conn=null;


        try {
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.updateUsername(stmt, newUsername, oldUsername);


        } catch (SQLException e) {
            e.printStackTrace();
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

        }

    }

    public void registerNewAccount(String firstName, String lastName, String username, String email, String password, String role) throws FailedRegistrationException {
        Statement stmt=null;
        Connection conn=null;

        try {
            conn=DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.addProfile(stmt, firstName, lastName, username, email, password,role);

        }
        catch (SQLException se) {
            throw new FailedRegistrationException("Username is already used.");
        }
        finally {
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

        }
    }

    public void changeProPic(InputStream inputStream, Reader reader) {
        Connection conn = null;

        try {
            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.2: creazione ed esecuzione della query
            Queries.updateUserProPic(conn,inputStream,reader);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Account retriveReviewAuthor(String username){
        Statement stmt = null;
        Connection conn = null;

        Author author = null;

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveAuthor(stmt, username);

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
                assert conn != null;
                conn.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        return author;
    }

}
