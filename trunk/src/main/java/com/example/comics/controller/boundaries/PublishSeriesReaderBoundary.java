package com.example.comics.controller.boundaries;

import com.example.comics.model.fagioli.*;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.example.comics.controller.boundaries.MailProperties.FROM;
import static com.example.comics.controller.boundaries.MailProperties.initializeProperties;

public class PublishSeriesReaderBoundary extends  MailProperties{
    public void sendEmailForSeriesPublished(AuthorBean authorBean, SeriesBean seriesBean, ReaderBean readerBean) {

        // Recipient's email ID needs to be mentioned.
        String to = readerBean.getEmail();
        String sss = "New series just published!";
        String mmm = "Hello from ComicsWorld! "+authorBean.getUsername()+" has just published a new series, "+seriesBean.getTitle();

        sendEmail(to, sss, mmm);
    }
}
