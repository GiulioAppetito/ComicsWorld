package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Queries;
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
        Statement stmt11 = null;
        Connection conn11 = null;
        Badge associatedBadge = null;

        try {
            conn11 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt11 = conn11.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveBadgeByID(stmt11, badgeID);
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
                assert conn11 != null;
                conn11.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return associatedBadge;

    }

    public List<Badge> retrieveAchievedBadges(String username) {

        Statement stmt12 = null;
        Connection conn12 = null;

        List<Badge> achievedBadges = new ArrayList<>();
        Badge badge;

        try {
            conn12 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt12 = conn12.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveBadgesByReader(stmt12, username);
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
                assert conn12!=null;
                conn12.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return achievedBadges;
    }


    public void saveObtainedBadge(Badge badge, Reader reader) {

        Statement stmt13 = null;
        Connection conn13 = null;

        try {

            conn13 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt13 = conn13.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            int achievedbadgeID = badge.getId();
            String username = reader.getUsername();

            Queries.addAchievedBadge(stmt13, username, achievedbadgeID);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert conn13 != null;
                conn13.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
