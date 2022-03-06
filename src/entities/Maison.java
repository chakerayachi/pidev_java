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
public class Maison {
    
    private  int id ;
    private  String adresse ;
    private  String region ;
    private int num_tel ;
    private String description ;
    private int capacite ;
    private int nb_chambres ;
    private float prix ;
    private String image;
    private  int id_user ;

    // constructeur par default
    public Maison(){}

    // constructeur paramtrer avec le champs id
    public Maison(int id, String adresse, String region, int num_tel,  String description, int capacite, int nb_chambres, float prix, String image, int id_user) {
        this.id = id;
        this.adresse = adresse;
        this.region = region;
        this.num_tel = num_tel;
        this.description = description;
        this.capacite = capacite;
        this.nb_chambres = nb_chambres;
        this.prix = prix;
        this.image=image;
        this.id_user = id_user;
    }

    // constructeur paramtrer sans le champs id
    public Maison(String adresse, String region, int num_tel,  String description, int capacite, int nb_chambres, float prix, String image, int id_user) {
        this.adresse = adresse;
        this.region = region;
        this.num_tel = num_tel;
        this.description = description;
        this.capacite = capacite;
        this.nb_chambres = nb_chambres;
        this.prix = prix;
        this.image=image;
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

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getNb_chambres() {
        return nb_chambres;
    }

    public void setNb_chambres(int nb_chambres) {
        this.nb_chambres = nb_chambres;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
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

    // toString methode

    @Override
    public String toString() {
        return "Maison{" +
                "id=" + id +
                ", adresse='" + adresse + '\'' +
                ", region='" + region + '\'' +
                ", num_tel=" + num_tel +
                ", description='" + description + '\'' +
                ", capacite=" + capacite +
                ", nb_chambres=" + nb_chambres +
                ", prix=" + prix +
                ", id_user=" + id_user +
                '}';
    }
    
    
}
