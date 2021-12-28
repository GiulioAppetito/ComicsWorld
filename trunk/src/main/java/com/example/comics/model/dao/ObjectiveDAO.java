package com.example.comics.model.dao;

import com.example.comics.model.ChapterObjective;
import com.example.comics.model.Levels;
import com.example.comics.model.Objective;
import com.example.comics.model.ReviewsObjective;

import java.sql.*;
import java.util.ArrayList;

public class ObjectiveDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public ArrayList<Objective> retreiveAchievedObjectives(String username) {

        Statement stmt = null;
        Connection conn = null;

        ArrayList<Objective> achievedObjectives = new ArrayList<Objective>();

        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveObjectivesByReader(stmt, username);

            if(!rs.first()){
                return achievedObjectives;
            }
            rs.first();

            do{
                if(rs.getString("objectiveType").equals("reviews")){

                    ReviewsObjective reviewsObjective = new ReviewsObjective(rs.getInt("associatedBadgeID"));
                    reviewsObjective.setRequiredReviews(rs.getInt("number"));
                    switch (rs.getString("level")){
                        case "beginner":
                            reviewsObjective.setLevel(Levels.BEGINNER);
                            break;
                        case "intermediate":
                            reviewsObjective.setLevel(Levels.INTERMEDIATE);
                            break;
                        case "expert":
                            reviewsObjective.setLevel(Levels.EXPERT);
                            break;
                    }
                    achievedObjectives.add(reviewsObjective);
                }else if(rs.getString("objectiveType").equals("chapters")){
                    ChapterObjective chapterObjective = new ChapterObjective();
                    chapterObjective.setRequiredChapters(rs.getInt("number"));
                    switch (rs.getString("level")){
                        case "beginner":
                            chapterObjective.setLevel(Levels.BEGINNER);
                            break;
                        case "intermediate":
                            chapterObjective.setLevel(Levels.INTERMEDIATE);
                            break;
                        case "expert":
                            chapterObjective.setLevel(Levels.EXPERT);
                            break;
                    }
                    achievedObjectives.add(chapterObjective);

                }

            }while(rs.next());




        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return achievedObjectives;
    }

    public void addAchievedObjective(Objective objective, String username) {

        Statement stmt = null;
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String series_title = objective.getSeries_title();
            String objective_level = objective.getLevel().toString();
            String objective_type = objective.getType();

            Queries.addAchievedObjective(stmt, username, series_title, objective_level, objective_type);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
