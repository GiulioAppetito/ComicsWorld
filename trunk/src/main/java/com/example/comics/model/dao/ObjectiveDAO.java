package com.example.comics.model.dao;

import com.example.comics.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectiveDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    public List<Objective> retreiveAchievedObjectives(String username) {

        Statement stmt = null;
        Connection conn = null;

        List<Objective> achievedObjectives = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retrieveObjectivesByReader(stmt, username);

            if(!rs.first()){
                return achievedObjectives;
            }
            rs.first();

            do{

                BadgeDAO badgeDAO = new BadgeDAO();
                Badge badge = badgeDAO.retreiveAssociatedBadge(rs.getInt("associatedBadgeID"));

                Discount discount = new Discount(rs.getFloat("discountPercentage"));
                discount.setLimitDays(rs.getInt("limitDays"));

                int numberOfReviews = rs.getInt("number");
                String level = rs.getString("level");

                if(rs.getString("objectiveType").equals("reviews")){
                    ReviewsObjective reviewsObjective = new ReviewsObjective(badge,discount);
                    reviewsObjective.setRequiredReviews(numberOfReviews);
                    switch (level){
                        case "beginner":
                            reviewsObjective.setLevel(Levels.BEGINNER);
                            break;
                        case "intermediate":
                            reviewsObjective.setLevel(Levels.INTERMEDIATE);
                            break;
                        case "expert":
                            reviewsObjective.setLevel(Levels.EXPERT);
                            break;
                        default:
                            break;
                    }
                    achievedObjectives.add(reviewsObjective);
                }else if(rs.getString("objectiveType").equals("chapters")){
                    ChapterObjective chapterObjective = new ChapterObjective(badge, discount);
                    chapterObjective.setRequiredChapters(numberOfReviews);
                    switch (level){
                        case "beginner":
                            chapterObjective.setLevel(Levels.BEGINNER);
                            break;
                        case "intermediate":
                            chapterObjective.setLevel(Levels.INTERMEDIATE);
                            break;
                        case "expert":
                            chapterObjective.setLevel(Levels.EXPERT);
                            break;
                        default:
                            break;
                    }
                    achievedObjectives.add(chapterObjective);

                }

            }while(rs.next());




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return achievedObjectives;
    }

    public void addAchievedObjective(Objective objective, String username) {

        Statement stmt = null;
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String seriesTitle = objective.getSeries_title();
            String objectiveLevel = objective.getLevel().toString();
            String objectiveType = objective.getType();

            Queries.addAchievedObjective(stmt, username, seriesTitle, objectiveLevel, objectiveType);


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

    public List<Objective> retrieveSeriesObjectives(Series series){
        Statement stmt = null;
        Connection conn = null;

        List<Objective> objectives = new ArrayList<>();


        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveObjectivesBySeries(stmt, series.getTitle());

            if (!rs.first()) {
                System.out.println("No objectives found on series : "+series.getTitle());
                return objectives;
            }
            rs.first();

            do {
                BadgeDAO badgeDAO = new BadgeDAO();
                Badge badge = badgeDAO.retreiveAssociatedBadge(rs.getInt("associatedBadgeID"));

                Discount discount = new Discount(rs.getFloat("discountPercentage"));
                discount.setLimitDays(rs.getInt("limitDays"));

                if(rs.getString("type").equals("reviews")){
                    ReviewsObjective reviewsObjective = new ReviewsObjective(badge,discount);
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
                        default:
                            break;
                    }

                    reviewsObjective.setSeries_title(series.getTitle());
                    reviewsObjective.setRequiredReviews(rs.getInt("number"));
                    objectives.add(reviewsObjective);


                }else if(rs.getString("type").equals("chapters")){
                    ChapterObjective chapterObjective = new ChapterObjective(badge,discount);
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
                        default:
                            break;
                    }

                    chapterObjective.setSeries_title(series.getTitle());
                    chapterObjective.setRequiredChapters(rs.getInt("number"));
                    objectives.add(chapterObjective);
                }


            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return objectives;
    }
}
