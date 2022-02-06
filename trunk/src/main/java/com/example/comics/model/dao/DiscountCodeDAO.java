package com.example.comics.model.dao;

import com.example.comics.model.Discount;
import com.example.comics.model.DiscountCode;
import com.example.comics.model.Reader;
import com.example.comics.model.Series;
import com.example.comics.model.dao.utils.DatesConverter;
import com.example.comics.model.dao.utils.Queries;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountCodeDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    public Map<DiscountCode, Series> retreiveDiscountCodesByReader(String username) {

        Statement stmt = null;
        Connection conn = null;

        Map<DiscountCode,Series> discountCodes = new HashMap<>();
        String code;
        LocalDate expiringDate;
        Float percentage;
        LocalDate startingDate;
        int limitDays;



        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveDiscountCodesByReader(stmt, username);

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
                Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs2 = Queries.retreiveObjectivesByDiscountCode(stmt2,rs.getInt("objectiveID"));

                Series series;
                SeriesDAO seriesDAO = new SeriesDAO();
                if(!rs2.first()){
                    return discountCodes;
                }
                series = seriesDAO.retrieveSeries(rs2.getString("seriesTitle"));

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
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return discountCodes;
    }

    public void saveObtainedDiscountCode(DiscountCode discountCode, Reader reader) throws SQLException {

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.saveReadersDiscountCode(stmt,discountCode,reader);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            assert conn != null;
            conn.close();
        }
    }

    public void deleteDiscountCode(Reader reader,DiscountCode discountCode) {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.deleteDiscountCode(stmt,reader,discountCode);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            assert conn!=null;
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
