package com.example.comics.controller.boundaries;

import com.example.comics.model.fagioli.*;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.example.comics.controller.boundaries.MailProperties.FROM;
import static com.example.comics.controller.boundaries.MailProperties.initializeProperties;

public class PublishChapterReaderBoundary extends MailProperties{

    public void sendEmailForChapterPublished(AuthorBean authorBean, ChapterBean chapterBean, String seriesTitle, ReaderBean readerBean) {
        // Recipient's email ID needs to be mentioned.
        String receiver = readerBean.getEmail();
        String sb = "New chapter just published!";
        String ms = "Hello from ComicsWorld! "+authorBean.getUsername()+" has just published a new chapter,"+chapterBean.getTitle()+" on his series "+seriesTitle+"!";
        sendEmail(receiver, sb, ms);
    }
}
