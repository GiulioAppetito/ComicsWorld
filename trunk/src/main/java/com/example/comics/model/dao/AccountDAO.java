package com.example.comics.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    //MANCA LA REFLECTION




    public String verifyCredentials(String credential,String password) throws Exception {

        //dichiarazioni
        Statement stmt=null;
        Connection conn=null;
        String role=null;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.checkSignedUserByEmail(stmt, credential);

            if (!rs.first()){
                Exception e = new Exception("No username Found matching with email or username: "+ credential);
                throw e;
            }
            rs.first();
            do{
                //verificare se la password � corretta
                String foundPassword=rs.getString("password");
                if(foundPassword.equals(password)) {
                    role = rs.getString("role");
                }


            }while(rs.next());
            return role;

        }finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }


    }

    public void changeCredentials(String newName, String newSurname, String newUsername, String oldUsername){

        Statement stmt=null;
        Connection conn=null;


        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.updateCredentials(stmt, newName, newSurname, oldUsername, newUsername);


        }
        catch (SQLException throwables) {
            System.out.println("ECCEZIONE REGISTRAZIONE");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }

    }

    public void changeEmail(String newEmail, String username){

        Statement stmt=null;
        Connection conn=null;


        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.updateEmail(stmt, newEmail, username);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }

    }

    public ArrayList<String> retreiveCredentials(String username) {
        Statement stmt=null;
        Connection conn=null;

        String foundFirstName;
        String foundLastName;
        String foundUsername;
        String foundEmail;

        ArrayList<String> credentialList = new ArrayList<String>();


        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveCredentials(stmt,username);

            if (!rs.first()){
                Exception e = new Exception("No username Found matching with email or username: "+ username);
                throw e;
            }
            rs.first();

            //verificare se la password � corretta
             foundFirstName = rs.getString("firstname");
             credentialList.add(foundFirstName);
             foundLastName = rs.getString("lastname");
             credentialList.add(foundLastName);
             foundUsername = rs.getString("username");
             credentialList.add(foundUsername);
             foundEmail = rs.getString("email");
             credentialList.add(foundEmail);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }


        }
        return credentialList;




    }

    public void registerNewAccount(String firstName, String lastName, String username, String email, String password, String role) throws AlreadyUsedUsernameException{
        Statement stmt=null;
        Connection conn=null;

        try {
            conn=DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.addProfile(stmt, firstName, lastName, username, email, password,role);

        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
    }
}
