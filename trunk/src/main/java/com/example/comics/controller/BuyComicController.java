package com.example.comics.controller;

import com.example.comics.controller.boundaries.PaymentBoundary;
import com.example.comics.controller.boundaries.BuyComicsAuthorBoundary;
import com.example.comics.model.DiscountCode;
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
        if(discountCodeBean.getCode() != null){
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

    public void completedPayment(SeriesBean seriesBean, DiscountCodeBean discountCodeBean){

        //mail all'autore
        BuyComicsAuthorBoundary buyComicsAuthorBoundary = new BuyComicsAuthorBoundary();
        buyComicsAuthorBoundary.sendEmailForSoldChapter(seriesBean);

        for(DiscountCode discountCode : UserLogin.getInstance().getReader().getDiscountCodes()){
            if(discountCode.getCode().equals(discountCodeBean.getCode())){
                UserLogin.getInstance().getReader().removeDiscountCode(discountCode);

                new Thread(()->{
                    DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
                    discountCodeDAO.deleteDiscountCode(UserLogin.getInstance().getReader(), discountCode);
                }).start();

            }
        }


        //mostri il pagamento avvenuto
        System.out.println("Payment went well");
    }

    public void failedPayment(SeriesBean seriesBean, DiscountCodeBean discountCodeBean) {
        //mostri il pagamento non avvenuto
        for(Series series : seriesBean.getAuthor().getPublishedSeries()){
            if(series.getTitle().equals(seriesBean.getTitle())){
                //to-do
            }
        }

        System.out.println("Payment of "+seriesBean.getTitle()+" went wrong");
    }



}
