package com.example.comics.controller.boundaries;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public abstract class MailProperties {

    protected static final String FROM = "comicsworldISPW@gmail.com";
    protected static final String HOST  = "smtp.gmail.com";
    protected static final String PASSWORD = "bhccvubcmpzggdbn";

    protected MailProperties(){
        //costruttore
    }

    protected static Session initializeProperties(){
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.user","giulio.appetito.ga@gmail.com");
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.auth","true");

        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.checkserveridentity","true");
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
}
