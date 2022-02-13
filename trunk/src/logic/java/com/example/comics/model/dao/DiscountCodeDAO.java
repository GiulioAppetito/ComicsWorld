package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Connector;
import com.example.comics.model.dao.utils.DatesConverter;
import com.example.comics.model.dao.utils.Queries;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DiscountCodeDAO {

    public Map<DiscountCode, Series> retreiveDiscountCodesByReader(String username) {

        Statement stmt17 = null;
        Statement stmt18 = null;
        Connection conn17 = null;

        Map<DiscountCode,Series> discountCodes = new HashMap<>();
        String code;
        LocalDate expiringDate;
        Float percentage;
        LocalDate startingDate;
        int limitDays;

        try {
            conn17 = Connector.getInstance().getConnection();

            stmt17 = conn17.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveDiscountCodesByReader(stmt17, username);

            if (!rs.first()) {
                return discountCodes;
            }
            rs.first();

            do {

                percentage = rs.getFloat("percentage");
                code = rs.getString("code");

                startingDate = DatesConverter.toLocalDate(rs.getString("startingDate"));
                expiringDate = DatesConverter.toLocalDate(rs.getString("expiringDate"));

                limitDays = (int)DatesConverter.daysDifference(startingDate,expiringDate);

                Discount discount = new Discount(percentage);
                discount.setLimitDays(limitDays);

                ResultSet rs2;
                stmt18 = conn17.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs2 = Queries.retreiveObjectivesByDiscountCode(stmt18,rs.getInt("objectiveID"));

                Series series;
                if(!rs2.first()){
                    return discountCodes;
                }
                series = SeriesDAO.retrieveSeries(rs2.getString("seriesTitle"));

                DiscountCode discountCode = new DiscountCode(discount,code,expiringDate);
                discountCodes.put(discountCode,series);


            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt18 != null)
                    stmt18.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (stmt17 != null)
                    stmt17.close();
            } catch (SQLException se2) {
                //TO-DO
            }
        }
        return discountCodes;
    }

    public void saveObtainedDiscountCode(DiscountCode discountCode, Reader reader, Series series, Objective objective) {

        Connection conn19 = null;
        Statement stmt19 = null;

        try {
            conn19 = Connector.getInstance().getConnection();
            stmt19 = conn19.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.saveReadersDiscountCode(stmt19,discountCode,reader,series,objective);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                stmt19.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteDiscountCode(Reader reader,DiscountCode discountCode) {
        Statement stmt20 = null;
        Connection conn20;

        try {
            conn20 = Connector.getInstance().getConnection();
            stmt20 = conn20.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.deleteDiscountCode(stmt20,reader,discountCode);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                stmt20.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
