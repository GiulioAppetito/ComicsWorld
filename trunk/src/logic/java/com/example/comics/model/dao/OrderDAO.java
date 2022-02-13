package com.example.comics.model.dao;

import com.example.comics.model.*;
import com.example.comics.model.dao.utils.Connector;
import com.example.comics.model.dao.utils.DatesConverter;
import com.example.comics.model.dao.utils.Queries;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<Order> retrieveOrders(String username) {

        Statement stmt22 = null;
        Connection conn22;

        List<Order> orders = new ArrayList<>();

        LocalDate date;
        float expense;
        Series series;
        String chapterTitle = "unknown";


        try {
            conn22 = Connector.getInstance().getConnection();

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

                assert series != null;
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


        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt22 != null)
                    stmt22.close();
            } catch (SQLException se2) {
                //TO-DO
            }
        }
        return orders;
    }

    public void insertOrder(Order order) {
        Statement stmt23 = null;
        Connection conn23;


        try {
            conn23 = Connector.getInstance().getConnection();

            stmt23 = conn23.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Queries.insertOrder(stmt23, order, UserLogin.getInstance().getReader());

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt23 != null)
                    stmt23.close();
            } catch (SQLException se2) {
                //TO-DO
            }
        }
    }
}
