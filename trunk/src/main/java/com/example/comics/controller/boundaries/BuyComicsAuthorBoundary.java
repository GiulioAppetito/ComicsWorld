package com.example.comics.controller.boundaries;

import com.example.comics.model.Author;
import com.example.comics.model.fagioli.AuthorBean;
import com.example.comics.model.fagioli.SeriesBean;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class BuyComicsAuthorBoundary {

    private static final String FROM = "comicsworldISPW@gmail.com";
    private static final String HOST  = "smtp.gmail.com";
    private static final String PASSWORD = "bhccvubcmpzggdbn";

    private static Session initializeProperties(){
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.user","giulio.appetito.ga@gmail.com");
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.auth","true");

        properties.put("mail.smtp.ssl.checkserveridentity","true");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback","false");
        properties.put("mail.smtp.ssl.trust", HOST);
        properties.put("mail.smtp.ssl.protocols","TLSv1.2");

        // Get the default Session object.
        return javax.mail.Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM,PASSWORD);
            }
        });

    }

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
            System.out.println("Sent message successfully...");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
