/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.*;



/**
 *
 * @author chaker
 */
public class Reservation {

    private int id ;
    private Timestamp created_At;
    private Date date_debut ;
    private Date date_fin ;
    private float montant_a_payer;
    private float reste_a_payer=0;
    private int id_voiture;
    private int id_ticket ;
    private int id_maison;
    private int id_chambre;
    private int id_user ;
    private String etat="confirmée";
     private int id_transaction;
    private String type;
    
    
    
    //constructeur par default
    public Reservation(){}

    public Reservation(int id,Timestamp created_At, Date date_debut, Date date_fin, float montant_a_payer, float reste_a_payer, int id_voiture, int id_ticket, int id_maison, int id_chambre, int id_user,String etat,int id_transaction, String type) {
        this.id = id;
        this.created_At=created_At;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.montant_a_payer = montant_a_payer;
        this.reste_a_payer = reste_a_payer;
        this.id_voiture = id_voiture;
        this.id_ticket = id_ticket;
        this.id_maison = id_maison;
        this.id_chambre = id_chambre;
        this.id_user = id_user;
        this.etat=etat;
        this.id_transaction = id_transaction;
        this.type = type;
    }

    public Reservation(Date date_debut, Date date_fin, float montant_a_payer,int id_voiture, int id_ticket, int id_maison, int id_chambre, int id_user, int id_transaction, String type) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.montant_a_payer = montant_a_payer;
        this.id_voiture = id_voiture;
        this.id_ticket = id_ticket;
        this.id_maison = id_maison;
        this.id_chambre = id_chambre;
        this.id_user = id_user;
        this.id_transaction = id_transaction;
        this.type = type;
    }

   
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontant_a_payer() {
        return montant_a_payer;
    }

    public void setMontant_a_payer(float montant_a_payer) {
        this.montant_a_payer = montant_a_payer;
    }
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
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
    public float getReste_a_payer() {
        return reste_a_payer;
    }

    public void setReste_a_payer(float montant_paye) {
        this.reste_a_payer = this.montant_a_payer-montant_paye;
    }
    public int getId_chambre() {
        return id_chambre;
    }
    public void setId_hotel(int id_chambre) {
        this.id_chambre = id_chambre;
    }

    public int getId_maison() {
        return id_maison;
    }

    public void setId_maison(int id_maison) {
        this.id_maison = id_maison;
    }
    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }
    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }
    
    public Timestamp getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Timestamp created_At) {
        this.created_At = created_At;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
     @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", montant_a_payer=" + montant_a_payer + ", reste_a_payer=" + reste_a_payer + ", id_voiture=" + id_voiture + ", id_ticket=" + id_ticket + ", id_maison=" + id_maison + ", id_chambre=" + id_chambre + ", id_user=" + id_user + ", etat=" + etat + ", id_transaction=" + id_transaction + ", type=" + type + '}';
    }
}
