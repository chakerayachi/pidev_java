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
     private String immat;
    private String model ;
    private String marque ;
    private  String couleur ;
    private int capacite ;
    private String description ;
    private int id_user ;
    private int id_categorie;
    private String prix;

    //constructeur par default
    public Voiture(){}

    // constructeur parametere avec le champs id
    
    public Voiture(int id, String immat, String model, String marque, String couleur, int capacite, String description, int id_user, int id_categorie, String prix) {
        this.id = id;
        this.immat=immat;
        this.model = model;
        this.marque = marque;
        this.couleur = couleur;
        this.capacite = capacite;
        this.description = description;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.prix = prix;
    }

    

    // constructeur parametere sans le champs id

    public Voiture(String immat, String model, String marque, String couleur, int capacite, String description, int id_user, int id_categorie, String prix) {
        this.immat = immat;
        this.model = model;
        this.marque = marque;
        this.couleur = couleur;
        this.capacite = capacite;
        this.description = description;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.prix = prix;
    }

    public Voiture(int aInt, String string, String string0, String string1, String string2, int aInt0, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Voiture(int id_voiture, String imma, String mode, String marq, String coul, int capa, String desc, int cate, String pri) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

    
    

    //getters ans setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmodel() {
        return model;
    }

    public void setmodel(String model) {
        this.model = model;
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

    public String getImmat() {
        return immat;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public String getModel() {
        return model;
    }

    public String getPrix() {
        return prix;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
    
    

    //toString methode

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", immat='" + immat + '\'' +
                ", model='" + model + '\'' +
                ", marque='" + marque + '\'' +
                ", couleur='" + couleur + '\'' +
                ", capacite=" + capacite +
                ", description='" + description + '\'' +
                ", id_user=" + id_user +
                '}';
    }
    
}
