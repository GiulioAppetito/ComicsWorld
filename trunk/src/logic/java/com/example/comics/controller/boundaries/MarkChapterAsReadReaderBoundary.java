package com.example.comics.controller.boundaries;

import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.DiscountCodeBean;
import com.example.comics.model.fagioli.SeriesBean;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.example.comics.controller.boundaries.MailProperties.FROM;
import static com.example.comics.controller.boundaries.MailProperties.initializeProperties;

public class MarkChapterAsReadReaderBoundary extends MailProperties{
    public void sendEmailForDiscountCode(AccountBean readerBean, SeriesBean seriesBean, DiscountCodeBean discountCodeBean) {

        String to = readerBean.getEmail();
        String s = "New discount code just received!";
        String m = "Hello from ComicsWorld! Your read chapters just allowed you to receive a new badge! Check it out on your profile" +
                "Use the following code " + discountCodeBean.getCode() + " to purchase from " + seriesBean.getTitle() + ", with a " + discountCodeBean.getDiscountBean().getPercentage() +
                "% off (up to " + discountCodeBean.getDiscountBean().getLimitDays() + " days)";

        sendEmail(to, s, m);
    }

}
