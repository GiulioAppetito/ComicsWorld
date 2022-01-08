package com.example.comics.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";


    public String verifyCredentials(String credential,String password) throws Exception {

        //dichiarazioni
        Statement stmt=null;
        Connection conn=null;
        String role=null;

        try {
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.checkSignedUserByEmail(stmt, credential, password);

            if (!rs.first()){
                throw new Exception("No username Found matching with email or username: "+ credential);
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

    public void changeCredentials(String newName, String newSurname, String newUsername, String oldUsername){

        Statement stmt=null;
        Connection conn=null;


        try {
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.updateCredentials(stmt, newName, newSurname, oldUsername, newUsername);


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

    public void changeEmail(String newEmail, String password){

        Statement stmt=null;
        Connection conn=null;


        try {
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Queries.updateEmail(stmt, newEmail, password);


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
}
