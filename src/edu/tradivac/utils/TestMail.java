/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.utils;

import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;
/**
 *
 * @author devhk
 */
public class TestMail {
  private final static String MAILER_VERSION = "Java";
  public static boolean envoyerMailSMTP(String host, boolean debug) {
    boolean result = false;  
    final String user="tradivac@ahmeddhahak.com"; 
  final String password="1dfqA#qdq";
    try {
      Properties props = System.getProperties();
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true");  
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.port", "465");
     props.put("mail.smtp.ssl.trust", host);
       props.put("mail.smtp.ssl.protocols", "TLSv1.2");
          props.put("mail.smtp.ssl.enable", host);
      Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    }); 
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("moi@chez-moi.fr"));
      InternetAddress[] internetAddresses = new InternetAddress[1];
      internetAddresses[0] = new InternetAddress("devhkahmed@gmail.com");
      message.setRecipients(Message.RecipientType.TO,internetAddresses);
      message.setSubject("Test");
      message.setText("test mail");
      message.setHeader("X-Mailer", MAILER_VERSION);
      message.setSentDate(new Date());
      session.setDebug(debug);
      Transport.send(message);
      result = true;
    } catch (AddressException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return result;
  }
   
  public static void main(String[] args) {
    TestMail.envoyerMailSMTP("smtp.hostinger.com",true);
  }
}