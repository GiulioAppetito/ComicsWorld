package com.example.comics.model.dao;

import com.example.comics.model.Badge;
import com.example.comics.model.Review;

import java.sql.*;
import java.util.ArrayList;

public class BadgeDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";


    public Badge retreiveAssociatedBadge(int badgeID) {
        Statement stmt = null;
        Connection conn = null;
        Badge associatedBadge = null;

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveBadgeByID(stmt, badgeID);
            rs.first();
            associatedBadge = new Badge();
            associatedBadge.setName(rs.getString("badgeName"));
            //icon
            associatedBadge.setId(rs.getInt("badgeID"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return associatedBadge;

    }
}
