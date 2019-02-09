package com.team113.plagiarismdetector.email;

import com.team113.plagiarismdetector.ConfigProperties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

/**
 * This singleton class is used to send email
 */
public class SendEmail {

    /**
     * Private constructor to call static methods of class directly
     */
    private SendEmail(){

    }

    static final Logger logger = Logger.getLogger(SendEmail.class);

    /**
     * This method is used to deliver the email to required recipients
     * @param sub subject of email to be sent
     * @param msg body of email to be sent
     */
    public static void send(String sub,String msg){

        logger.log(Level.INFO, "Received request to send email with sub:" + sub);

        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", ConfigProperties.getGmailHost());
        props.put("mail.smtp.socketFactory.port", ConfigProperties.getGmailPort());
        props.put("mail.smtp.socketFactory.class", ConfigProperties.getSocketFactory());
        props.put("mail.smtp.auth", ConfigProperties.getSmtpAuth());
        props.put("mail.smtp.port", ConfigProperties.getGmailPort());

        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(ConfigProperties.getEmailFrom(), ConfigProperties.getEmailPass());
                    }
                });

        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(ConfigProperties.getEmailTo()));
            message.setSubject(sub);
            message.setText(msg);

            //send message
            Transport.send(message);
        } catch (MessagingException e) {
            logger.log(Level.FATAL, "Error in sending Email with sub: " + sub);
        }
    }
}
