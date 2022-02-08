package com.example.comics.controller.boundaries;

import com.example.comics.model.fagioli.*;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.example.comics.controller.boundaries.MailProperties.FROM;
import static com.example.comics.controller.boundaries.MailProperties.initializeProperties;

public class PublishSeriesReaderBoundary {
    public void sendEmailForSeriesPublished(AuthorBean authorBean, SeriesBean seriesBean, ReaderBean readerBean) {

        // Recipient's email ID needs to be mentioned.
        String to = readerBean.getEmail();
        // Get the default Session object.
        Session session = initializeProperties();

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(FROM));


            // Set To: header field of the header.
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("New series just published!");

            // Now set the actual message
            message.setText("Hello from ComicsWorld! "+authorBean.getUsername()+" has just published a new series, "+seriesBean.getTitle());

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
