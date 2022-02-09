package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.DatesConverter;
import com.example.comics.model.dao.utils.Queries;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private static final String USER = "anastasia";
    private static final String PASS = "passwordanastasia";
    private static final String DB_URL = "jdbc:mysql://comics-world.ce9t0fxhansh.eu-west-2.rds.amazonaws.com:3306/ComicsWorld?autoReconnect=true&useSSL=false";

    public List<Order> retrieveOrders(String username) {

        Statement stmt22 = null;
        Connection conn22 = null;

        List<Order> orders = new ArrayList<>();

        LocalDate date;
        Float expense;
        Series series;
        String chapterTitle = "unknown";


        try {
            conn22 = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt22 = conn22.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.retreiveOrdersByReader(stmt22, username);

            if (!rs.first()) {
                return orders;
            }
            rs.first();

            do {

                expense = rs.getFloat("expense");
                date = DatesConverter.toLocalDate(rs.getString("date"));

                series = SeriesDAO.retrieveSeries(rs.getString("series"));

                for(Chapter c : series.getChapters()){
                    if(c.getTitle().equals(rs.getString("chapterTitle"))){
                        chapterTitle = c.getTitle();
                    }
                }


                Order order = new Order(series);
                order.setDate(date);
                order.setExpense(expense);
                order.setChapterTitle(chapterTitle);

                orders.add(order);


            } while (rs.next());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt22 != null)
                    stmt22.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn22 != null)
                    conn22.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return orders;
    }

    public void insertOrder(Order order) {
        Statement stmt23 = null;
        Connection conn23 = null;


        try {
            conn23 = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt23 = conn23.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.insertOrder(stmt23, order, UserLogin.getInstance().getReader());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt23 != null)
                    stmt23.close();
            } catch (SQLException se2) {
                //TO-DO
            }
            try {
                if (conn23 != null)
                    conn23.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
    }
}
