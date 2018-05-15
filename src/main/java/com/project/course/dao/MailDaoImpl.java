package com.project.course.dao;

import com.project.course.entity.User;
import com.project.course.exception.MailException;

import javax.jms.*;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by kleba on 09.05.2018.
 */
public class MailDaoImpl implements MailDao {
    private Session session;
    private Properties properties;

    public void config(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("mail.properties");
         properties = new Properties();
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
         session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties.getProperty("mail.smtp.username"), properties.getProperty("mail.smtp.password"));
                    }
                });

    }

    @Override
    public void send(User user) throws MailException {
        config();
        try {
            StringBuilder sb = new StringBuilder();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject("Registration");
            sb.append("Dear ");
            sb.append(user.getLogin());
            sb.append("\n");
            sb.append("Thanks for registration");
            message.setText(sb.toString());
            Transport.send(message);
        } catch (MessagingException e) {
            throw new  MailException(e);
    }
    }
}
