/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author chaker
 */
public class Voiture {
    
     private  int id ;
    private String libelle ;
    private String marque ;
    private  String couleur ;
    private int capacite ;
    private String description ;
    private int id_user ;
    private int id_categorie;

    //constructeur par default
    public Voiture(){}

    // constructeur parametere avec le champs id
    public Voiture(int id, String libelle, String marque, String couleur, int capacite, String description, int id_user,int id_categorie) {
        this.id = id;
        this.libelle = libelle;
        this.marque = marque;
        this.couleur = couleur;
        this.capacite = capacite;
        this.description = description;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
    }

    // constructeur parametere sans le champs id
    public Voiture( String libelle, String marque, String couleur, int capacite, String description, int id_user , int id_categorie) {
        this.libelle = libelle;
        this.marque = marque;
        this.couleur = couleur;
        this.capacite = capacite;
        this.description = description;
        this.id_user = id_user;
        this.id_categorie = id_categorie ;
    }

    public Voiture(int id, String libelle, String marque, String couleur, int capacite, String description) {
        this.id = id;
        this.libelle = libelle;
        this.marque = marque;
        this.couleur = couleur;
        this.capacite = capacite;
        this.description = description;
    }
    

    //getters ans setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }
    
    

    //toString methode

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", marque='" + marque + '\'' +
                ", couleur='" + couleur + '\'' +
                ", capacite=" + capacite +
                ", description='" + description + '\'' +
                ", id_user=" + id_user +
                '}';
    }
    
}
