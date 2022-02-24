/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Firas CHKOUNDALI
 */
public class Commentaire {
    private int idcom;
    private String contenu;
    private String date;
    private int idsujet;
    private int iduser;

    public Commentaire() {
    }

    public Commentaire(String contenu, String date, int idsujet, int iduser) {
        this.contenu = contenu;
        this.date = date;
        this.idsujet = idsujet;
        this.iduser = iduser;
    }

    public Commentaire(int idcom, String contenu, String date, int idsujet, int iduser) {
        this.idcom = idcom;
        this.contenu = contenu;
        this.date = date;
        this.idsujet = idsujet;
        this.iduser = iduser;
    }

    public int getIdcom() {
        return idcom;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDate() {
        return date;
    }

    public void setIdcom(int idcom) {
        this.idcom = idcom;
    }

    public int getIdsujet() {
        return idsujet;
    }

    public int getIduser() {
        return iduser;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdsujet(int idsujet) {
        this.idsujet = idsujet;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idcom=" + idcom + ", contenu=" + contenu + ", date=" + date + ", idsujet=" + idsujet + ", iduser=" + iduser + '}';
    }
    
    
    
}
