package com.example.comics.controller.boundaries;

import com.example.comics.model.fagioli.SeriesBean;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PostReviewAuthorBoundary extends MailProperties{



    public void sendEmailForNewReviewPosted(SeriesBean seriesBean) {

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
            message.setSubject("New review just posted!");

            // Now set the actual message
            message.setText("Hello from ComicsWorld! Your series "+seriesBean.getTitle()+" just received a new review ");

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


}