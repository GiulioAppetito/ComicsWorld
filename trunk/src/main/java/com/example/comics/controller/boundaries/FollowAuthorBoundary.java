package com.example.comics.controller.boundaries;

import com.example.comics.model.Author;
import com.example.comics.model.Reader;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FollowAuthorBoundary extends MailProperties {

    public void sendEmailForNewFollower(Author author,Reader reader) {

        // Recipient's email ID needs to be mentioned.
        String to = author.getEmail();
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
            message.setSubject("New follower!");

            // Now set the actual message
            message.setText("Hello from ComicsWorld! User "+reader.getUsername()+" is now following you.");

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
