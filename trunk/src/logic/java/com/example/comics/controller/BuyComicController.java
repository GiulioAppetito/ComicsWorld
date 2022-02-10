package com.example.comics.controller;

import com.example.comics.controller.boundaries.PaymentBoundary;
import com.example.comics.controller.boundaries.BuyComicsAuthorBoundary;
import com.example.comics.model.DiscountCode;
import com.example.comics.model.Order;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.DiscountCodeDAO;
import com.example.comics.model.dao.OrderDAO;
import com.example.comics.model.dao.SeriesDAO;
import com.example.comics.model.exceptions.DiscountCodeException;
import com.example.comics.model.fagioli.*;
import com.example.comics.model.fagioli.bundle.AccountBundle;
import com.example.comics.model.fagioli.bundle.DiscountBundle;

import java.time.LocalDate;

public class BuyComicController {

    public void buyComic(SeriesBean seriesBean, ChapterBean chapterBean, DiscountCodeBean discountCodeBean) throws DiscountCodeException {

        DiscountCode discountCode = null;
        if(discountCodeBean != null){
            discountCode = UserLogin.getInstance().getReader().getDiscountCodeByCode(discountCodeBean.getCode());
            if(discountCode == null){
                throw new DiscountCodeException("You don't own this discount code!");
            }
            if(discountCode.isExpired()){
                throw new DiscountCodeException("Your code is expired!");
            }

        }
        if(discountCodeBean!=null) {
            DiscountBean discountBean = new DiscountBundle();
            discountBean.setPercentage(discountCode.getDiscount().getPercentage());
            discountBean.setLimitDays(discountCode.getDiscount().getLimitDays());

            discountCodeBean.setDiscountBean(discountBean);
        }

        //nel caso vada tutto a buon fine ...
            AccountBean accountBean = new AccountBundle();
            accountBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
            accountBean.setLastName(UserLogin.getInstance().getAccount().getLastName());

            PaymentBoundary paymentBoundary = new PaymentBoundary();
            paymentBoundary.convalidPayment(accountBean, chapterBean, seriesBean, discountCodeBean);

    }

    public void completedPayment(SeriesBean seriesBean, ChapterBean chapterBean, DiscountCodeBean discountCodeBean){

        //mail all'autore
        BuyComicsAuthorBoundary buyComicsAuthorBoundary = new BuyComicsAuthorBoundary();
        buyComicsAuthorBoundary.sendEmailForSoldChapter(seriesBean);

        if(discountCodeBean != null) {
            for (DiscountCode discountCode : UserLogin.getInstance().getReader().getDiscountCodes().keySet()) {
                if (discountCode.getCode().equals(discountCodeBean.getCode())) {
                    UserLogin.getInstance().getReader().removeDiscountCode(discountCode);

                    new Thread(() -> {
                        DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
                        discountCodeDAO.deleteDiscountCode(UserLogin.getInstance().getReader(), discountCode);
                    }).start();

                }
            }
        }

        Series orderedSeries = SeriesDAO.retrieveSeries(seriesBean.getTitle());

        Order order = new Order(orderedSeries);
        order.setDate(LocalDate.now());
        order.setExpense(chapterBean.getPrice());
        order.setChapterTitle(chapterBean.getTitle());


        OrderDAO orderDAO = new OrderDAO();
        orderDAO.insertOrder(order);

        UserLogin.getInstance().getReader().addNewOrder(order);
    }

    public void failedPayment() {
        UserLogin.getInstance().getReader().notifyFailedOrder();
    }
}
