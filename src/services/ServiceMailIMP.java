/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import entities.Transaction;
import entities.Utilisateur;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author alaaz
 */
public class ServiceMailIMP {
    private static String username = "wecodeforyouu@gmail.com";
    private static String password = "zerobug123";

    
     
    
    
    public void send_payment_message(Utilisateur user,Reservation reservation,Transaction transaction){ 
        System.out.println("user"+user);
        System.out.println("reser"+reservation);
        System.out.println("tran"+transaction);
                
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //Set TLS encryption enabled
        props.put("mail.smtp.host", "smtp.gmail.com");  //Set SMTP host
        props.put("mail.smtp.port", "587"); //Set smtp port
        Session session = Session.getInstance(props,new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("wecodeforyouu@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Notification de paiement");
            message.setText("MR/MME "+user.getNom()+" "+user.getPrenom()+" "+"nous vous informe que le paiement de la réservation d'un montant :"+transaction.getMontant_avance()+" $" +"a été effectué avec succée");
            Transport.send(message);
         } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
