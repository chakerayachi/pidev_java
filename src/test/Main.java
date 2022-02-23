/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Client;
import entities.Hotel;
import entities.Utilisateur;
import java.text.SimpleDateFormat;
import java.util.Date;
import services.ServiceUtilisateurIMP;
import utils.MailTools;

/**
 *
 * @author chaker
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("hello nexus");
        ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();
        Date date1 = new Date();
        String account_date = new SimpleDateFormat("yyyy-MM-dd").format(date1);

        // test ajout utilisateur
        /*
        Utilisateur user = new Utilisateur("chdddaker", "chakdder123", "chaker", "ayassschi", "chasssker.ayachi@esprit.tn", 586307447, 1001337460, "tunis ", null, "ee","active",account_date);
        su.ajoutClient(user);
         */
        /**
         * ******************************************************************************
         */
        // test de modification d un utilisateur 
        // voir ServiceUtilisateurIMP pour mettre l id 
        //su.modifierUtilisateur(new Utilisateur("updated ", "updated", "updated", "updated", "updated", 0, 0, "updated", "updated", "updated", "updated","updated","2022-11-11"),15);
        /**
         * ******************************************************************************
         */
        // test de suppression d un utilisateur
        //su.supprimerUtilisateur( a remplacer par un id  );
        /**
         * ******************************************************************************
         */
        // test d affichage de tous les utilisateurs 
        /*
        su.afficherUtilisateur().forEach(System.out::println);
         */
        /**
         * ******************************************************************************
         */
        // test d affichage pour les client 
        //su.afficherClientList().forEach(System.out::println);
        /**
         * ******************************************************************************
         */
        // test d affichage de tous les agencier
        //su.afficherAgencierList().forEach(System.out::println);
        /**
         * ******************************************************************************
         */
        //find user by id
        // su.FindNomUserById(16);
        /**
         * ******************************************************************************
         */
        // trier par date creation
        //su.TrieParDateCreation();
        /**
         * ******************************************************************************
         */
        // test du get le role d un utilisateur
        // su.getUtilisateurRole(16);
        /**
         * ******************************************************************************
         */
        // test get userbyid
        // su.getUserById(16);
        /**
         * ******************************************************************************
         */
        // test find by nom
        // Boolean FindByNom = su.FindByNom("chaker");
        // System.out.println(FindByNom);
        /**
         * ******************************************************************************
         */
        // test fing by login and password 
        // Utilisateur result = su.FindByLoginAndPassword("chaker", "chaker123");
        // System.out.println(result);
        /**
         * ******************************************************************************
         */
        //test sending mail
        //MailTools.sendMail("usermail");

    }

}
