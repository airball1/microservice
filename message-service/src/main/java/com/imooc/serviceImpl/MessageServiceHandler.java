package com.imooc.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.immoc.service.MessageService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Author liuzike
 * @Date 1/6/21
 **/

@Service
public class MessageServiceHandler implements MessageService {
    public boolean sendMobileMessage(String mobile, String message) {
        System.out.println("sendMobileMessage, mobile:" + mobile +", message:" + message);
        return true;
    }

    public boolean sendEmailMessage(String email, String eMessage) {
        System.out.println("sendMobileMessage, email:" + email +", message:" + eMessage);
        final String username = "lzkx1234@gmail.com";
        final String password = "nssfss07";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lzkx1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Testing Subject");
            message.setText(eMessage);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static void main(String[] args) {
        MessageServiceHandler messageServiceHandler = new MessageServiceHandler();

        messageServiceHandler.sendEmailMessage("zliu3@scu.edu", "hahaha");
    }
}
