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
public class Reservation {
    
     private int id ;
    private Date date_debut ;
    private Date date_fin ;
    private int total ;
    private  int id_voiture ;
    private int id_ticket ;
    private  int id_maison ;
    private int id_hotel ;
    private int id_user ;

    //constructeur par default
    public Reservation(){}

    //constructeur parametere avec le champ id
    public Reservation(int id, Date date_debut, Date date_fin, int total, int id_voiture, int id_ticket, int id_maison, int id_hotel, int id_user) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.total = total;
        this.id_voiture = id_voiture;
        this.id_ticket = id_ticket;
        this.id_maison = id_maison;
        this.id_hotel = id_hotel;
        this.id_user = id_user;
    }


    //constructeur parametere sans  le champ id
    public Reservation(Date date_debut, Date date_fin, int total, int id_voiture, int id_ticket, int id_maison, int id_hotel, int id_user) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.total = total;
        this.id_voiture = id_voiture;
        this.id_ticket = id_ticket;
        this.id_maison = id_maison;
        this.id_hotel = id_hotel;
        this.id_user = id_user;
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public int getId_maison() {
        return id_maison;
    }

    public void setId_maison(int id_maison) {
        this.id_maison = id_maison;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
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
        return "Reservation{" +
                "id=" + id +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", total=" + total +
                ", id_voiture=" + id_voiture +
                ", id_ticket=" + id_ticket +
                ", id_maison=" + id_maison +
                ", id_hotel=" + id_hotel +
                ", id_user=" + id_user +
                '}';
    }
    
}
