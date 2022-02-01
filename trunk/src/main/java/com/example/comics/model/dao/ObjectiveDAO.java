package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectiveDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";


    public List<Objective> retrieveSeriesObjectives(String series){
        Statement stmt = null;
        Connection conn = null;

        List<Objective> objectives = new ArrayList<>();


        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveObjectivesBySeries(stmt, series);

            if (!rs.first()) {
                return objectives;
            }
            rs.first();

            do {
                BadgeDAO badgeDAO = new BadgeDAO();
                Badge badge = badgeDAO.retreiveAssociatedBadge(rs.getInt("associatedBadgeID"));

                Discount discount = new Discount(rs.getFloat("discountPercentage"));
                discount.setLimitDays(rs.getInt("limitDays"));
                String seriesTitle = rs.getString("seriesTitle");

                if(rs.getString("type").equals("reviews")){
                    ReviewsObjective reviewsObjective = new ReviewsObjective(badge,discount, rs.getInt("number"));
                    reviewsObjective.setSeriesTitle(seriesTitle);
                    reviewsObjective.setId(rs.getInt("objective_id"));
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

                    objectives.add(reviewsObjective);


                }else if(rs.getString("type").equals("chapters")){
                    ChapterObjective chapterObjective = new ChapterObjective(badge,discount);
                    chapterObjective.setSeriesTitle(seriesTitle);
                    chapterObjective.setId(rs.getInt("objective_id"));
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
