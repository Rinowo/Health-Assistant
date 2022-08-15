package com.example.healthassistant.common;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mail {
    public static  void sendMail(String recep, String name, Date date, String time, String location, String messages) throws Exception{
        System.out.println("gmail");
        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");

        String myAccount ="tunganhhh205@gmail.com";
        String password = "shqtmqjcqhnnwukk";
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount,password);
            }
        });
        Message message = prepareMessage(session,myAccount,recep, name, date, time, location, messages);

        Transport.send(message);
        System.out.println("Test nf");
    }
    private static Message prepareMessage(Session session,String myAccount,String recep, String name, Date date, String time, String location, String messages)
    {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recep));
            message.setSubject("Hello " +  name);
            String htmlCode="<br>You have appoinment at: " + time + "<br> Date: "+ date + "<br>Location: " + location + "<br>Message: " + messages;
            String htmlImg="<img src=\"https://aptechvietnam.com.vn/sites/default/files/0001_0_0.png%22%3E\" alt=\"\">";
            message.setContent(htmlCode + htmlImg,"text/html");
            return message;
        }catch (Exception ex){
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
}
