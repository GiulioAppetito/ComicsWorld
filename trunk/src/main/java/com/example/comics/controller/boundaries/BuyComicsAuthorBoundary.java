package com.example.comics.controller.boundaries;

import com.example.comics.model.fagioli.SeriesBean;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BuyComicsAuthorBoundary extends MailProperties {

    public void sendEmailForSoldChapter(SeriesBean seriesBean) {

        // Recipient's email ID needs to be mentioned.
        String to = seriesBean.getAuthor().getEmail();
        String sub = "New chapter sold!";
        String mess = "Hello from ComicsWorld! A chapter from your series "+seriesBean.getTitle() +" has just been sold.";

        sendEmail(to, sub, mess);
    }
}
