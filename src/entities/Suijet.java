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
public class Suijet {
    
     private int id ;
    private String titre ;
    private String contenu ;
    private Date date ;
    private int etat ;
    private int nb_com ;
    private int id_user ;

    //constructeur par default
    public Suijet(){}

    // constructeur parametere avec le champ id
    public Suijet(int id, String titre, String contenu, Date date, int etat, int nb_com, int id_user) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.etat = etat;
        this.nb_com = nb_com;
        this.id_user = id_user;
    }

    // constructeur parametere sans le champ id
    public Suijet(String titre, String contenu, Date date, int etat, int nb_com, int id_user) {
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.etat = etat;
        this.nb_com = nb_com;
        this.id_user = id_user;
    }

    // getters ans setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getNb_com() {
        return nb_com;
    }

    public void setNb_com(int nb_com) {
        this.nb_com = nb_com;
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
        return "Suijet{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", date=" + date +
                ", etat=" + etat +
                ", nb_com=" + nb_com +
                ", id_user=" + id_user +
                '}';
    }
    
}
