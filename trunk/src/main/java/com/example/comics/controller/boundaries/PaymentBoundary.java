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


    public void convalidPayment(AccountBean accountBean, ChapterBean chapterBean, SeriesBean seriesBean, DiscountCodeBean discountCodeBean) {
        //contattiamo la boundary di paypal, tipo set di api offerto
        PayPalInterface paypal = new PayPalBoundary();

        String payment = String.valueOf(chapterBean.getPrice());
        //magari non facciamo due stringhette
        paypal.startTransaction(accountBean.getFirstName(), accountBean.getLastName(), payment);

        final int[] waiting = {0};
        Thread waitForPayment = new Thread(()->{
            //attendo pagamento
            while(waiting[0] == 0){
                waiting[0] = paypal.convalidPayment();
            }
            //quando arriva
            if(waiting[0]==1){
                signalPayment(true, seriesBean , chapterBean, discountCodeBean);
            }else{
                signalPayment(false, seriesBean, chapterBean, discountCodeBean);
            }

        });
        waitForPayment.start();
    }

    private void signalPayment(boolean b, SeriesBean seriesBean, ChapterBean chapterBean, DiscountCodeBean discountCodeBean) {
        BuyComicController buyComicController = new BuyComicController();
        if(b){
            System.out.println("BUYCOMICBOUNDARY: "+ "good payment");
            buyComicController.completedPayment(seriesBean, chapterBean, discountCodeBean);
        }else{
            System.out.println("BUYCOMICBOUNDARY: "+ "bad payment");
            buyComicController.failedPayment();
        }
    }

}
