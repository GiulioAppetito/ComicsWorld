package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Connector;
import com.example.comics.model.dao.utils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectiveDAO {


    public List<Objective> retrieveSeriesObjectives(String series){
        Statement stmt21 = null;
        Connection conn21;

        List<Objective> objectives = new ArrayList<>();


        try {
            conn21 = Connector.getInstance().getConnection();
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

                objective = objectiveFactory.createObjective(rs.getString("type"),badge,discount,Levels.valueOf(rs.getString("level")),rs.getFloat("number"));
                objectives.add(objective);
            } while (rs.next());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert stmt21 != null;
                stmt21.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return objectives;
    }
}
