package com.example.comics.controller.boundaries;

import com.example.comics.model.fagioli.SeriesBean;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BuyComicsAuthorBoundary extends MailProperties {

    public void sendEmailForSoldChapter(SeriesBean seriesBean) {

        // Recipient's email ID needs to be mentioned.
        String to = seriesBean.getAuthor().getEmail();
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
            message.setSubject("New chapter sold!");

            // Now set the actual message
            message.setText("Hello from ComicsWorld! A chapter from your series "+seriesBean.getTitle() +" has just been sold.");

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
