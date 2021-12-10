package com.example.comics.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

public class AccountDAO {
    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    //MANCA LA REFLECTION




    public String verifyCredentials(String email,String password) throws Exception {

        //dichiarazioni
        Statement stmt=null;
        Statement stmtCourses = null;
        Statement stmtEvents = null;
        Statement stmtCourts = null;
        Connection conn=null;
        String role=null;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn= DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.checkSignedUserByEmail(stmt, email);

            if (!rs.first()){
                Exception e = new Exception("No username Found matching with email: "+email);
                throw e;
            }

            rs.first();
            do{
                //verificare se la password � corretta
                String foundPassword=rs.getString("password");
                if(foundPassword.equals(password)) {
                    role = rs.getString("role");
                    if(role.equals("reader")) {

                        //setting the user profile
                        //String nome = rs.getString("firstname");
                        //String cognome = rs.getString("lastname");
                        //String email = rs.getString("email");
                    }
                    else {

                    }

                }


            }while(rs.next());


            return role;

        }finally {


            try {
                if (stmt != null)
                    stmt.close();
                if (stmtCourses != null)
                    stmtCourses.close();
                if (stmtCourts != null)
                    stmtCourts.close();
                if (stmtEvents != null)
                    stmtEvents.close();
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
