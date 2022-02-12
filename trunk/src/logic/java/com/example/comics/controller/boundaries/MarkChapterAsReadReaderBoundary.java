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

public class MarkChapterAsReadReaderBoundary {
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
                    "Use the following code " + discountCodeBean.getCode() + " to purchase from " + seriesBean.getTitle() + ", with a " + discountCodeBean.getDiscountBean().getPercentage() +
                    "% off (up to " + discountCodeBean.getDiscountBean().getLimitDays() + " days)");

            // Send message
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
