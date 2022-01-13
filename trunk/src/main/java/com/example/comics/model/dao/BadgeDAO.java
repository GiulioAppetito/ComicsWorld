package com.example.comics.model.dao;

import com.example.comics.model.*;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            if(!rs.first()){
                return associatedBadge;
            }
            rs.first();
            associatedBadge = new Badge();
            associatedBadge.setName(rs.getString("badgeName"));
            //icon
            associatedBadge.setId(rs.getInt("badgeID"));

            Blob bl = rs.getBlob("badgeIcon");
            if(bl!=null){
                InputStream inputStream = bl.getBinaryStream();
                Image image = new Image(inputStream);
                associatedBadge.setIcon(image);
            }




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

    public List<Badge> retrieveAchievedBadges(String username) {

        Statement stmt = null;
        Connection conn = null;

        List<Badge> achievedBadges = new ArrayList<>();
        Badge badge;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveBadgesByReader(stmt, username);
            if(!rs.first()){
                return achievedBadges;
            }
            rs.first();

            do{
                badge = new Badge();
                badge.setId(rs.getInt("badgeID"));
                badge.setName(rs.getString("badgeName"));
                Blob bl = rs.getBlob("badgeIcon");
                InputStream inputStream = bl.getBinaryStream();
                Image image = new Image(inputStream);
                badge.setIcon(image);

                achievedBadges.add(badge);

            } while(rs.next());




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert conn!=null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return achievedBadges;
    }


    public void addAchievedBadge(Badge badge, Reader reader) {

        Statement stmt = null;
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            int achievedbadgeID = badge.getId();
            String username = reader.getUsername();

            Queries.addAchievedBadge(stmt, username, achievedbadgeID);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
