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
public class Hotel {
    
     private int id ;
    private String adresse ;
    private  String ville ;
    private String region ;
    private int num_tel ;
    private String description ;
    private String libelle ;
    private int nb_etoile ;
    private String image;
    private  int id_user ;

    // constructeur par default
    public Hotel(){}


    // constructeur parametree avec le champs id
    public Hotel(int id, String adresse, String ville, String region, int num_tel, String description, String libelle, int nb_etoile, String image, int id_user) {
        this.id = id;
        this.adresse = adresse;
        this.ville = ville;
        this.region = region;
        this.num_tel = num_tel;
        this.description = description;
        this.libelle = libelle;
        this.nb_etoile = nb_etoile;
        this.image = image;
        this.id_user = id_user;
    }

  

    // constructeur parametree sans le champs id
    public Hotel( String adresse, String ville, String region, int num_tel, String description, String libelle, int nb_etoile, String image, int id_user) {
        this.adresse = adresse;
        this.ville = ville;
        this.region = region;
        this.num_tel = num_tel;
        this.description = description;
        this.libelle = libelle;
        this.nb_etoile = nb_etoile;
        this.image = image;
        this.id_user = id_user;
    }

   


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

  

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNb_etoile() {
        return nb_etoile;
    }

    public void setNb_etoile(int nb_etoile) {
        this.nb_etoile = nb_etoile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    //toString methode

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", region='" + region + '\'' +
                ", num_tel=" + num_tel +
                ", description='" + description + '\'' +
                ", libelle='" + libelle + '\'' +
                ", nb_etoile=" + nb_etoile +
                ", id_user=" + id_user +
                '}';
    }
    
}
