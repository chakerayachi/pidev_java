/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.*;

/**
 *
 * @author alaaz
 */
public class Transaction { 
    private int id; 
    private Timestamp created_At;
    private int taux_avance=20; 
    private int taux_commission=40; 
    private int taux_garantie=60;
    private float montant_avance; 
    private float montant_commission;
    private float montant_garantie;
    private String paymentIntent_id; 

   
     
    public Transaction (){} 
    
    
    
    //constructeur de récupération des données
    public Transaction(int id, Timestamp created_At,int taux_avance,int taux_commission,int taux_garantie,float montant_avance, float montant_commission, float montant_garantie,String paymentIntent_id) {
        this.id = id;
        this.created_At = created_At;
        this.montant_avance = montant_avance;
        this.montant_commission = montant_commission;
        this.montant_garantie = montant_garantie;
        this.paymentIntent_id=paymentIntent_id;
    }
    //constructeur d'insertion des données 
    public Transaction(float montant_a_payer,String paymentIntent_id) {
        setMontant_avance(montant_a_payer); 
        setMontant_commission();
        setMontant_garantie();
        this.paymentIntent_id=paymentIntent_id;
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Timestamp created_At) {
        this.created_At = created_At;
    }

    public int getTaux_avance() {
        return taux_avance;
    }

    public void setTaux_avance(int taux_avance) {
        this.taux_avance = taux_avance;
    }

    public int getTaux_commission() {
        return taux_commission;
    }

    public void setTaux_commission(int taux_commission) {
        this.taux_commission = taux_commission;
    }

    public int getTaux_garantie() {
        return taux_garantie;
    }

    public void setTaux_garantie(int taux_garantie) {
        this.taux_garantie = taux_garantie;
    }

    public float getMontant_avance() {
        return montant_avance;
    }

    public void setMontant_avance(float  montant_a_payer) {
        this.montant_avance = (montant_a_payer*this.taux_avance)/100;
    }

    public float getMontant_commission() {
        return montant_commission;
    }

    public void setMontant_commission() {
        this.montant_commission = (this.montant_avance*this.taux_commission)/100;
    }

    public float getMontant_garantie() {
        return montant_garantie;
    }

    public void setMontant_garantie() {
        this.montant_garantie = (this.montant_avance*this.taux_garantie)/100;
    }
     public String getPaymentIntent_id() {
        return paymentIntent_id;
    }

    public void setPaymentIntent_id(String paymentIntent_id) {
        this.paymentIntent_id = paymentIntent_id;
    }
    

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", created_At=" + created_At + ", taux_avance=" + taux_avance + ", taux_commission=" + taux_commission + ", taux_garantie=" + taux_garantie + ", montant_avance=" + montant_avance + ", montant_commission=" + montant_commission + ", montant_garantie=" + montant_garantie + '}';
    }
    
    
    
    
    
    
    
    
    
}
