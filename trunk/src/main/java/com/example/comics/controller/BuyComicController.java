package com.example.comics.controller;

import com.example.comics.controller.boundaries.PaymentBoundary;
import com.example.comics.controller.boundaries.BuyComicsAuthorBoundary;
import com.example.comics.model.DiscountCode;
import com.example.comics.model.Order;
import com.example.comics.model.Series;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.DiscountCodeDAO;
import com.example.comics.model.exceptions.DiscountCodeException;
import com.example.comics.model.exceptions.InvalidPaymentException;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.DiscountCodeBean;
import com.example.comics.model.fagioli.SeriesBean;
import com.example.comics.model.fagioli.bundle.AccountBundle;

import java.time.LocalDate;

public class BuyComicController {

    public void buyComic(SeriesBean seriesBean, ChapterBean chapterBean, DiscountCodeBean discountCodeBean) throws InvalidPaymentException, DiscountCodeException {

        DiscountCode discountCode;
        if(discountCodeBean != null){
            discountCode = UserLogin.getInstance().getReader().getDiscountCodeByCode(discountCodeBean.getCode());
            if(discountCode == null){
                throw new DiscountCodeException("You don't own this discount code!");
            }
            if(discountCode.getExpiringDate().isBefore(LocalDate.now())){
                throw new DiscountCodeException("Your code is expired!");
            }

        }

            //nel caso vada tutto a buon fine ...
            //chiamo altra boundary per la carta
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

        Series orderedSeries = null;
        for(Series series : seriesBean.getAuthor().getPublishedSeries()){
            if(series.getTitle().equals(seriesBean.getTitle())){
                orderedSeries = series;
            }
        }

        Order order = new Order(orderedSeries);
        order.setDate(LocalDate.now());
        order.setExpense(chapterBean.getPrice());

        System.out.println("BCC : new order to be inserted");
        UserLogin.getInstance().getReader().addNewOrder(order);
    }

    public void failedPayment() {
        //mostri il pagamento non avvenuto
    }



}
