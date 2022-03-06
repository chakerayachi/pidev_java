/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author chaker
 */
public class Evenement {
    
      private  int id ;
    private String libelle ;
    private Date date ;
    private  String description ;
    private String emplacement ;
    private int nb_place ;
    private int duree ;
    private int id_user ;

    // constructeur par default
    public Evenement (){}

    // constructeur paramtere avec le champs id

    public Evenement(int id, String libelle, Date date, String description, String emplacement, int nb_place, int duree,  int id_user) {
        this.id = id;
        this.libelle = libelle;
        this.date = date;
        this.description = description;
        this.emplacement = emplacement;
        this.nb_place = nb_place;
        this.duree = duree;
        this.id_user = id_user;
    }
    // constructeur paramtere sans le champs id

    public Evenement( String libelle, Date date, String description, String emplacement, int nb_place, int duree,  int id_user) {

        this.libelle = libelle;
        this.date = date;
        this.description = description;
        this.emplacement = emplacement;
        this.nb_place = nb_place;
        this.duree = duree;
        this.id_user = id_user;
    }

    // getters and setters

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
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
        return "Evenement{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", emplacement='" + emplacement + '\'' +
                ", nb_place=" + nb_place +
                ", duree=" + duree +
                ", id_user=" + id_user +
                '}';
    }
    
}
