/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Commentaire;
import entities.Sujet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServiceCommentaire;

import services.ServiceSujet;
import utils.MyDB;

/**
 *
 * @author Firas CHKOUNDALI
 */
public class Main {
    public static void main(String[] args) {
       
        MyDB.getInstance();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Today's date is: "+dateFormat.format(date));
        //DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy/mm/dd");
        //System.out.println(dateTime.format(datePattern));
//        ServiceSujet sp = new ServiceSujet();
     Sujet sujet= new Sujet(4,"test","ce-ci est un udpate",dateFormat.format(date),0,0,1);
        //sp.ajout(new Sujet("test","ce-ci est un test",dateFormat.format(date),0,0,1));
        //sp.supprimer(1);
        //sp.modifier(sujet);
//        sp.afficher().forEach(System.out::println);


    ServiceCommentaire sc= new ServiceCommentaire();
  //  sc.ajout(new Commentaire("ceci est un commentaire de test",dateFormat.format(date),2,1));
//    sc.supprimer(4);
//    
//    sc.afficher().forEach(System.out::println);
   // sc.modifier(new Commentaire(3,"ceci est un commentaire d'update",dateFormat.format(date),2,1));
        System.out.println(sujet);
        System.out.println("les commentaires :");
    sc.affichercommentairebysujet(sujet).forEach(System.out::println);
    }
}
