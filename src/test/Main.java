/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module_hani;

import entities.Voiture;
import services.ServicesVoitureIMP;

/**
 *
 * @author Hani
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServicesVoitureIMP sv = new ServicesVoitureIMP();
        Voiture v1 = new Voiture(1,"touristique","clio","noir",4,"jolie",0) ;
        System.out.println (sv.afficher());
        sv.ajout(v1);
        
    }
    
}
