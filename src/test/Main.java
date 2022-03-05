/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Avis;
import entities.Categorie;
import entities.Voiture;
import java.util.List;
import services.ServiceAvisIMP;
import services.ServiceCategorieIMP;
import services.ServicesVoitureIMP;

/**
 *
 * @author Hani
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServicesVoitureIMP sv = new ServicesVoitureIMP();
        ServiceCategorieIMP sc = new ServiceCategorieIMP() ;
        ServiceAvisIMP sa = new ServiceAvisIMP();
        Categorie c = new Categorie ("familiale","dfhdfh");
        Categorie c2 = new Categorie (1,"normale","dfhdfh");
        Voiture v1 = new Voiture("123tn100","touristique","clio","noir",4,"jolie") ;
        Voiture v2 = new Voiture(21,"456tn123","touristique","clio","vert",4,"jolie",1,"1") ;
        Avis t = new Avis(4,35); 
        //System.out.println (sv.chercherVoiture("vert"));
         //System.out.println (sc.getIdCategorie("normale"));
        // System.out.println (sv.afficherr());
       System.out.println( sa.get_moy_avis(35));
        //sv.ajout(v1);
        //sv.afficher();
        //sv.supprimer(2);
        //sc.ajout(c);
        //sc.modifier(c2);
        //sv.modifier(v2);
       //sc.afficher();
        //sc.supprimer(0);
        
        
    }
    
}
