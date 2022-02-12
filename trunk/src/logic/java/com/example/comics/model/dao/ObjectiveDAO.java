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
        Statement stmt21;
        Connection conn21 = null;

        List<Objective> objectives = new ArrayList<>();


        try {
            conn21 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt21 = conn21.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveObjectivesBySeries(stmt21, series);

            if (!rs.first()) {
                return objectives;
            }
            rs.first();
            BadgeDAO badgeDAO = new BadgeDAO();
            Objective objective;
            ObjectiveFactory objectiveFactory = new ObjectiveFactory();

            do {
                Badge badge = badgeDAO.retreiveAssociatedBadge(rs.getInt("associatedBadgeID"));

                Discount discount = new Discount(rs.getFloat("discountPercentage"));
                discount.setLimitDays(rs.getInt("limitDays"));

                try{
                    objective = objectiveFactory.createObjective(rs.getString("type"),badge,discount,Levels.valueOf(rs.getString("level")),rs.getFloat("number"));
                    objectives.add(objective);
                }catch (Exception e){
                    e.printStackTrace();
                }

            } while (rs.next());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn21!=null;
            try {
                conn21.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return objectives;
    }
}
