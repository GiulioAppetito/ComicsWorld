package com.example.comics.controller.boundaries;

import com.example.comics.model.fagioli.SeriesBean;

public class PostReviewAuthorBoundary extends MailProperties{

    public void sendEmailForNewReviewPosted(SeriesBean seriesBean) {

        // Recipient's email ID needs to be mentioned.
        String to = seriesBean.getAuthor().getEmail();
        // Set Subject: header field
        String msg = "Hello from ComicsWorld! Your series "+seriesBean.getTitle()+" just received a new review ";
        // Now set the actual message
        String sbj = "New review just posted!";

        sendEmail(to, sbj, msg);

    }

}