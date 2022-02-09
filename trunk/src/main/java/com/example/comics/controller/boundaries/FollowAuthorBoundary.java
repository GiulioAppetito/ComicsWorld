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
        String s = "New follower!";
        String m = "Hello from ComicsWorld! User "+reader.getUsername()+" is now following you.";

        sendEmail(to, s, m);
    }

}
