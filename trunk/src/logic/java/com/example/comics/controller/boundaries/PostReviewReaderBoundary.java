package com.example.comics.controller.boundaries;

import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.DiscountCodeBean;
import com.example.comics.model.fagioli.SeriesBean;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PostReviewReaderBoundary extends MailProperties {


    public void sendEmailForDiscountCode(AccountBean readerBean, SeriesBean seriesBean, DiscountCodeBean discountCodeBean) {

        String to = readerBean.getEmail();
        String s = "New discount code just received!";
        String m = "Hello from ComicsWorld! Your review just allowed you to receive a new badge! Check it out on your profile" +
                "Use the following code " + discountCodeBean.getCode() + " to purchase from " + seriesBean.getTitle() + ", with a " + discountCodeBean.getDiscountBean().getPercentage() +
                "% off (up to " + discountCodeBean.getDiscountBean().getLimitDays() + " days)";

        sendEmail(to, s, m);

    }
}
