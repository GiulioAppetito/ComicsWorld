package com.example.comics.controller.boundaries;

import com.example.comics.controller.BuyComicController;
import com.example.comics.fakepaypal.PayPalBoundary;
import com.example.comics.fakepaypal.PayPalInterface;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.ChapterBean;
import com.example.comics.model.fagioli.DiscountCodeBean;
import com.example.comics.model.fagioli.SeriesBean;

public class PaymentBoundary {

    public PaymentBoundary(){
        //costruttore
    }


    public void convalidPayment(AccountBean accountBean, ChapterBean chapterBean, SeriesBean seriesBean, DiscountCodeBean discountCodeBean){
        //contattiamo la boundary di paypal, tipo set di api offerto
        PayPalInterface paypal = new PayPalBoundary();

        float expense = chapterBean.getPrice();

        if(discountCodeBean != null){
            expense = (chapterBean.getPrice()*discountCodeBean.getDiscountBean().getPercentage())/100;
        }


        String payment = String.valueOf(expense);
        //magari non facciamo due stringhette
        paypal.startTransaction(accountBean.getFirstName(), accountBean.getLastName(), payment);

        final int[] waiting = {0};
        Thread waitForPayment = new Thread(()->{
            //attendo pagamento
            while(waiting[0] == 0){
                waiting[0] = paypal.convalidPayment();
            }

            signalPayment(waiting[0], seriesBean , chapterBean, discountCodeBean);

        });
        waitForPayment.start();
    }

    private synchronized void signalPayment(int b, SeriesBean seriesBean, ChapterBean chapterBean, DiscountCodeBean discountCodeBean){

        BuyComicController buyComicController = new BuyComicController();
        if(b==1) {
            buyComicController.completedPayment(seriesBean, chapterBean, discountCodeBean);
        }else{
            buyComicController.failedPayment();
        }
    }

}