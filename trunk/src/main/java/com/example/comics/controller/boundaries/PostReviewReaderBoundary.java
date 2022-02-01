package com.example.comics.controller.boundaries;

import com.example.comics.model.DiscountCode;
import com.example.comics.model.Reader;
import com.example.comics.model.Series;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.DiscountCodeBean;
import com.example.comics.model.fagioli.SeriesBean;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class PostReviewReaderBoundary {
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
    public void sendEmailForDiscountCode(AccountBean readerBean, SeriesBean seriesBean, DiscountCodeBean discountCodeBean) {

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
            message.setSubject("New discount code just received!");

            // Now set the actual message
            message.setText("Hello from ComicsWorld! Your review just allowed you to receive a new badge! Check it out on your profile" +
                    "Use the following code" + discountCodeBean.getCode() + " to purchase from " + seriesBean.getTitle() + ", with a " + discountCodeBean.getPercentage() +
                    "% off (up to " + discountCodeBean.getLimitDays() + " days)");

            // Send message
            Transport.send(message);
            System.out.println("Sent discount code email successfully to "+to);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
