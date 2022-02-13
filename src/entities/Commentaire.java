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
public class Commentaire {
    
     private int id ;
    private String contenu ;
    private Date date ;
    private  int etat ;
    private int id_suijet;

    // constructeur par default
    public Commentaire (){}

    // constructeur parametere avec le champs id


    public Commentaire(int id, String contenu, Date date, int etat, int id_suijet) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
        this.etat = etat;
        this.id_suijet = id_suijet;
    }

    // constructeur parametere sans le champs id


    public Commentaire( String contenu, Date date, int etat, int id_suijet) {

        this.contenu = contenu;
        this.date = date;
        this.etat = etat;
        this.id_suijet = id_suijet;
    }

    // getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId_suijet() {
        return id_suijet;
    }

    public void setId_suijet(int id_suijet) {
        this.id_suijet = id_suijet;
    }

    // to String methode

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", date=" + date +
                ", etat=" + etat +
                ", id_suijet=" + id_suijet +
                '}';
    }
    
}
