/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Hani
 */
public class Avis {
    private int id;
    private double valeur;
    private int id_voiture;

    public Avis() {
    }

    public Avis(double valeur, int id_voiture) {
        this.valeur = valeur;
        this.id_voiture = id_voiture;
    }

    public Avis(int id, float valeur, int id_voiture) {
        this.id = id;
        this.valeur = valeur;
        this.id_voiture = id_voiture;
    }

    public int getId() {
        return id;
    }

    public double getValeur() {
        return valeur;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", valeur=" + valeur + ", id_voiture=" + id_voiture + '}';
    }

    public void setId_voiture(Voiture idvoiture) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
