/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Utilisateur;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author chaker
 */
public class MailTools {

    public static void sendMail(Utilisateur user, String object, String body) throws Exception {
        Date date = new Date();
        String dateNow = new SimpleDateFormat("yyyy-MM-dd").format(date);
        makeFile(dateNow, user);

        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String userName = "chaker.ayachi@esprit.tn";
        //Your gmail password
        String password = "17091998danzer";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }

        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(userName));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));

            // Set Subject: header field
            message.setSubject(object);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(body);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "/Users/chaker/NetBeansProjects/pidev_java_chaker/pidev_java/src/Ressources/Docs/" + user.getLogin() + ".pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(user.getLogin()+".pdf");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


    public static void makeFile(String date, Utilisateur user) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(font, 20);
                contentStream.newLineAtOffset(170, 700);
                contentStream.showText("Nom :" + user.getNom());
                contentStream.newLineAtOffset(-140, -150);
                contentStream.setFont(font, 16);
                contentStream.showText("numero du telephone  : " + user.getNum_tel());
                contentStream.newLineAtOffset(-10, -50);
                contentStream.showText("  Prenom : " + user.getPrenom());
                contentStream.newLineAtOffset(0, -50);
                contentStream.showText("  Email: " + user.getEmail());
                contentStream.newLineAtOffset(10, -50);
                contentStream.showText("date : " + date);
                contentStream.endText();
                contentStream.close();
            }

            document.save("/Users/chaker/NetBeansProjects/pidev_java_chaker/pidev_java/src/Ressources/Docs/" + user.getLogin() + ".pdf");
            document.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
