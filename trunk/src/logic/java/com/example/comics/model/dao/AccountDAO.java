package com.example.comics.model.dao;

import com.example.comics.model.Account;
import com.example.comics.model.Author;
import com.example.comics.model.Reader;
import com.example.comics.model.dao.utils.Connector;
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
    public String verifyCredentials(String credential,String password) throws FailedLoginException {

        //dichiarazioni
        Statement stmt1=null;
        Connection conn1=null;
        String role=null;

        try {
            conn1= Connector.getInstance().getConnection();

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
        }
        return role;


    }

    public void changeCredentials(String newName, String newSurname, String email, String newUsername){

        Statement stmt2=null;
        Connection conn2=null;


        try {
            conn2= Connector.getInstance().getConnection();

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

        }

    }

    public void changeUsername(String newUsername, String oldUsername) throws FailedProfileCustomizationException {

        Statement stmt3=null;
        Connection conn3=null;


        try {
            conn3= Connector.getInstance().getConnection();

            stmt3 = conn3.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.updateUsername(stmt3, newUsername, oldUsername);


        } catch (SQLException e) {
            throw new FailedProfileCustomizationException("This username is already used!",e.getCause());
        } finally {
            try {
                if (stmt3 != null)
                    stmt3.close();
            } catch (SQLException se2) {
                //TO-DO
            }

        }

    }

    public void registerNewAccount(String firstName, String lastName, String username, String email, String password, String role) throws FailedRegistrationException {
        Statement stmt4=null;
        Connection conn4=null;

        try {
            conn4=Connector.getInstance().getConnection();
            stmt4 = conn4.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.addProfile(stmt4, firstName, lastName, username, email, password,role);

        }
        catch (SQLException se) {
            throw new FailedRegistrationException("Username is already used.",se.getCause());
        }
        finally {
            try {
                if (stmt4 != null)
                    stmt4.close();
            } catch (SQLException se2) {
                //TO-DO
            }

        }
    }

    public void changeProPic(InputStream inputStream, Reader reader) {
        Connection conn5 = null;

        try {
            conn5 = Connector.getInstance().getConnection();
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

            conn6 = Connector.getInstance().getConnection();
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
                stmt6.close();
            } catch (SQLException e) {
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

            conn7 = Connector.getInstance().getConnection();
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
