package com.example.comics.model.dao;

import com.example.comics.model.Badge;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;

public class BadgeDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    public Badge retreiveAssociatedBadge(int badgeID) {
        Statement stmt = null;
        Connection conn = null;
        Badge associatedBadge = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveBadgeByID(stmt, badgeID);
            rs.first();
            associatedBadge = new Badge();
            associatedBadge.setName(rs.getString("badgeName"));
            //icon
            associatedBadge.setId(rs.getInt("badgeID"));

            Blob bl = rs.getBlob("badgeIcon");
            InputStream inputStream = bl.getBinaryStream();
            Image image = new Image(inputStream);
            associatedBadge.setIcon(image);



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return associatedBadge;

    }
}
