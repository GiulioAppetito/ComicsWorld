package com.example.comics.model.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    private static Connector instance = null;
    private Connection conn = null;

    private Connector(){
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connector getInstance(){
        if(instance == null){
            instance = new Connector();
        }
        return instance;
    }

    public Connection getConnection(){
        return conn;
    }
}
