/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Client;
import entities.Utilisateur;
import services.ServiceUtilisateurIMP;

/**
 *
 * @author chaker
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("hello nexus");
                ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();

        // test ajout utilisateur
      /*
        Utilisateur user = new Utilisateur("chaker", "chaker123", "chaker", "ayachi", "chaker.ayachi@esprit.tn", 58607447, 10017460, "tunis ", "admin", null, "ee","active");
        su.ajoutUtilisateur(user);
      */
        
                                    /*********************************************************************************/
                             
                             
        // test de modification d un utilisateur 
        // voir ServiceUtilisateurIMP pour mettre l id 
        su.modifierUtilisateur(new Utilisateur("updated ", "updated", "updated", "updated", "updated", 0, 0, "updated", "updated", "updated", "updated","updated"),6);
       
       
       
                                    /*********************************************************************************/

        // test de suppression d un utilisateur
       // su.supprimerUtilisateur(a remplacer avec un id );
        
        
                                    /*********************************************************************************/

     
        // test d affichage de tous les utilisateurs 
        /*
        su.afficherUtilisateur().forEach(System.out::println);
        */
        
        
        
        
        
                                            /*********************************************************************************/

        // test d affichage pour les client 
        //su.afficherClientList().forEach(System.out::println);
        
        
                                            /*********************************************************************************/

        

         // test d affichage de tous les agencier
         //su.afficherAgencierList().forEach(System.out::println);
                                    
        
        
       
        
    }
    
}
