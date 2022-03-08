/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import entities.Transaction;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;
import services.ServiceTransactionIMP;
/**
 *
 * @author alaaz
 */
public class ServiceRéservationIMP {
     Connection cnxx;
     

    public ServiceRéservationIMP() {
        this.cnxx =MyDB.getInstance().getConnection();
    }
    
    
    //---Create reservations--
    public int add_reservation_hotel_chambre(Reservation rs,String paymentIntent_id){
        String Request = "INSERT INTO reservation (date_debut,date_fin,montant_a_payer,reste_a_payer,id_chambre,id_user,id_transaction,type) VALUES(?,?,?,?,?,?,?,?)" ;
        PreparedStatement  pst;
        int id_transaction=0;
        try {   
                ServiceTransactionIMP service=new ServiceTransactionIMP(); 
                Transaction transaction =new Transaction(rs.getMontant_a_payer(),paymentIntent_id);
                transaction.setId(service.add_transaction(transaction)); 
                rs.setId_transaction(transaction.getId()); 
                rs.setReste_a_payer(transaction.getMontant_avance());
                pst = cnxx.prepareStatement(Request);
                pst.setDate(1, rs.getDate_debut());
                pst.setDate(2, rs.getDate_fin());
                pst.setFloat(3, rs.getMontant_a_payer());
                pst.setFloat(4, rs.getReste_a_payer());
                pst.setInt(5, rs.getId_chambre());
                pst.setInt(6,rs.getId_user()); 
                pst.setString(7, rs.getEtat());
                pst.setInt(7,rs.getId_transaction());
                pst.setString(8,"hotel");
                pst.executeUpdate();
                id_transaction=transaction.getId();

                
                            System.out.println("Réservation à l''hotel effectué avec succés");
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }
        return id_transaction;
    }  

    public int add_reservation_house(Reservation rs,String paymentIntent_id){
        String Request = "INSERT INTO reservation (date_debut,date_fin,montant_a_payer,reste_a_payer,id_maison,id_user,etat,id_transaction,type) VALUES(?,?,?,?,?,?,?,?,?)" ;
        PreparedStatement  pst;
        int id_transaction=0;
        try {    
                ServiceTransactionIMP service=new ServiceTransactionIMP(); 
                Transaction transaction =new Transaction(rs.getMontant_a_payer(),paymentIntent_id); 
                transaction.setId(service.add_transaction(transaction)); 
                rs.setId_transaction(transaction.getId()); 
                rs.setReste_a_payer(transaction.getMontant_avance());
                pst = cnxx.prepareStatement(Request);
                pst.setDate(1, rs.getDate_debut());
                pst.setDate(2, rs.getDate_fin());
                pst.setFloat(3, rs.getMontant_a_payer());
                pst.setFloat(4, rs.getReste_a_payer());
                pst.setInt(5, rs.getId_maison());
                pst.setInt(6,rs.getId_user()); 
                pst.setString(7, rs.getEtat());
                pst.setInt(8,rs.getId_transaction());
                pst.setString(9,"maison");
                pst.executeUpdate();
                 id_transaction=transaction.getId();
               
                            System.out.println("Réservation à la maison a été effectué avec succés");
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }
        return id_transaction;
    }
    public int add_reservation_car(Reservation rs,String paymentIntent_id){
        String Request = "INSERT INTO reservation (date_debut,date_fin,montant_a_payer,reste_a_payer,id_voiture,id_user,etat,id_transaction,type) VALUES(?,?,?,?,?,?,?,?,?)" ;
        PreparedStatement  pst;
        int id_transaction=0;
        try {    
                ServiceTransactionIMP service=new ServiceTransactionIMP(); 
                Transaction transaction =new Transaction(rs.getMontant_a_payer(),paymentIntent_id); 
                
                
                transaction.setId(service.add_transaction(transaction)); 
                rs.setId_transaction(transaction.getId()); 
                rs.setReste_a_payer(transaction.getMontant_avance());
                pst = cnxx.prepareStatement(Request);
                pst.setDate(1, rs.getDate_debut());
                pst.setDate(2, rs.getDate_fin());
                pst.setFloat(3, rs.getMontant_a_payer());
                pst.setFloat(4, rs.getReste_a_payer());
                pst.setInt(5, rs.getId_voiture());
                pst.setInt(6,rs.getId_user()); 
                pst.setString(7, rs.getEtat());
                pst.setInt(8,rs.getId_transaction());
                pst.setString(9,"voiture");
                pst.executeUpdate();
                id_transaction=transaction.getId();
                System.out.println("Réservation de la voiture a été effectué avec succés");
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }
        return id_transaction;
    }
    public int add_reservation_ticket(Reservation rs,String paymentIntent_id){
        String Request = "INSERT INTO reservation (montant_a_payer,reste_a_payer,id_ticket,id_user,etat,id_transaction,type) VALUES(?,?,?,?,?,?,?)" ;
        PreparedStatement  pst;
        int id_transaction=0;
        try {    
                ServiceTransactionIMP service=new ServiceTransactionIMP(); 
                Transaction transaction =new Transaction();  
                transaction.setTaux_avance(100);
                transaction.setTaux_commission(0);
                transaction.setTaux_garantie(0);
                transaction.setMontant_avance(rs.getMontant_a_payer());
                transaction.setPaymentIntent_id(paymentIntent_id);
                transaction.setId(service.add_transaction(transaction));
                rs.setId_transaction(transaction.getId()); 
                pst = cnxx.prepareStatement(Request);
                pst.setFloat(1, rs.getMontant_a_payer());
                pst.setFloat(2, rs.getReste_a_payer());
                pst.setInt(3, rs.getId_ticket());
                pst.setInt(4,rs.getId_user()); 
                pst.setString(5, rs.getEtat());
                pst.setInt(6,rs.getId_transaction());
                pst.setString(7,"ticket");
                pst.executeUpdate();
                id_transaction=transaction.getId();
                System.out.println("id "+id_transaction);
                
                System.out.println("Réservation du billet d'événement a été effectué avec succés");
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }
        return id_transaction;
    }
    
    public List<List> get_reservations_user_email() {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
    
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,libelle,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join chambre c ON c.id=r.id_chambre JOIN hotel h ON h.id=c.id_hotel ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     public List<List> get_reservations_by_user_id (int id_user) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,type,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id where id_user="+id_user+" ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getInt(1));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getFloat(6));
                 list.add(rs.getInt(7));
                 list.add(rs.getFloat(8));
                 list.add(rs.getString(9));
                 list.add(rs.getInt(10));
                 list.add(rs.getInt(11));
                 list.add(rs.getInt(12));
                 list.add(rs.getInt(13));
                 list.add(rs.getString(14));
                 list.add(rs.getString(15));
                 reservation_list.add(list);
             }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    } 
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    //Client
     public List<List> get_reservations_client_by_type (int id_owner,String type) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String Request="";
           if(type=="maison"){ 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ut.nom,ut.prenom,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join maison m ON m.id=r.id_maison join utilisateur ut ON ut.id=m.id_user where m.id_user="+id_owner+" and type='maison' ORDER BY created_At DESC";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12)+" "+rs.getString(13));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(14));
                 reservation_list.add(list);

             }
           }
           else{ 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,v.`libelle`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join voiture v ON v.id=r.id_voiture  where v.id_user="+id_owner+" and type='voiture' ORDER BY created_At DESC";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }
           }
             
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     public List<List> get_reservations_client_by_date(String date,int id_owner,String type) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String Request="";
            if(type=="maison"){ 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ut.nom,ut.prenom,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join maison m ON m.id=r.id_maison join utilisateur ut ON ut.id=m.id_user where m.id_user="+id_owner+" and DATE(created_At) = '"+date+"' and r.`type`='maison' ORDER BY created_At DESC";
                 pst = cnxx.prepareStatement(Request);
                 ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12)+" "+rs.getString(13));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(14));
                 reservation_list.add(list);
                }
                 
            }else{
                 Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,v.`libelle`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join voiture v ON v.id=r.id_voiture  where v.id_user="+id_owner+" and type='voiture' and DATE(created_At) = '"+date+"' ORDER BY created_At DESC";
                 pst = cnxx.prepareStatement(Request);
                 ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }
            }
            
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    } 
     
     public List<List> get_reservations_client_by_user_email(String email,int id_owner ,String type) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String Request="";
            if(type=="maison"){ 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ut.nom,ut.prenom,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join maison m ON m.id=r.id_maison join utilisateur ut ON ut.id=m.id_user WHERE u.email='"+email+"' and m.id_user='"+id_owner+"' ORDER BY created_At DESC";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12)+" "+rs.getString(13));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(14));
                 reservation_list.add(list);
             }
                
            }else{ 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,v.`libelle`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join voiture v ON v.id=r.id_voiture WHERE email='"+email+"' and v.id_user='"+id_owner+"' ORDER BY created_At DESC";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     
     
    public List<List> get_reservations_client_by_car_name(String name,int id_owner) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;

        try {
                String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,v.`libelle`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join voiture v ON v.id=r.id_voiture WHERE v.libelle LIKE('%"+name+"%') and v.id_user="+id_owner+" ORDER BY created_At DESC";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     
     

     
    
     
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    //User 
    public List<List> get_reservation_details_by_id(int reservation_id,String type) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try { 
            System.out.println("type"+type);
            String Request="";
            if(type.equals("hotel")){ 
             Request = "SELECT r.`id`,created_At,date_debut,date_fin,h.`libelle`,h.`adresse`,h.`ville`,h.`num_tel`,c.`type`,c.`prix`,montant_a_payer,taux_avance,montant_paye_avance,reste_a_payer,r.`etat` from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join chambre c ON c.id=r.id_chambre JOIN hotel h ON h.id=c.id_hotel  where r.id ="+reservation_id;
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getInt(1));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4)); 
                 list.add(rs.getString(5));
                 list.add(rs.getString(6));
                 list.add(rs.getString(7));
                 list.add(rs.getInt(8)); 
                 list.add(rs.getString(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(11));
                 list.add(rs.getInt(12));
                 list.add(rs.getFloat(13));
                 list.add(rs.getFloat(14));
                 list.add(rs.getString(15));
                 reservation_list.add(list);
             }
            }else if(type=="maison"){ 
                Request = "SELECT r.`id`,created_At,date_debut,date_fin,ut.`nom`,ut.`prenom`,m.`adresse`,m.`region`,ut.`num_tel`,m.`capacite`,m.`nb_chambres`,m.`prix`,montant_a_payer,taux_avance,montant_paye_avance,reste_a_payer,r.`etat` from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join maison m ON m.id=r.id_maison join utilisateur ut ON ut.id=m.id_user where r.id ="+reservation_id;
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getInt(1));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4)); 
                 list.add(rs.getString(5));
                 list.add(rs.getString(6));
                 list.add(rs.getString(7));
                 list.add(rs.getString(8));
                 list.add(rs.getInt(9));
                 list.add(rs.getInt(10));
                 list.add(rs.getInt(11)); 
                 list.add(rs.getFloat(12));
                 list.add(rs.getFloat(13));
                 list.add(rs.getInt(14));
                 list.add(rs.getFloat(15));
                 list.add(rs.getFloat(16));
                 list.add(rs.getString(17));
                 reservation_list.add(list);
             }
            }else if(type.equals("voiture")){ 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,ut.`nom`,ut.`adresse`,ut.`num_tel`,v.`libelle`,v.`marque`,v.`capacite`,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat` from utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join voiture v ON v.id=r.id_voiture join utilisateur ut ON ut.id=v.id_user where r.id ="+reservation_id;
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getInt(1));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4)); 
                 list.add(rs.getString(5));
                 list.add(rs.getString(6));
                 list.add(rs.getInt(7));
                 list.add(rs.getString(8));
                 list.add(rs.getString(9));
                 list.add(rs.getInt(10)); 
                 list.add(rs.getFloat(11));
                 list.add(rs.getFloat(12));
                 list.add(rs.getInt(13));
                 list.add(rs.getFloat(14));
                 list.add(rs.getString(15));
                 reservation_list.add(list);
                }
            }else { 
                Request = "SELECT r.`id`,t.`created_At`,ut.`num_tel`,ev.`date`,ev.`libelle`,ev.`emplacement`,ev.`duree`,tk.`type`,montant_a_payer,r.`etat` from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join ticket tk ON tk.id=r.id_ticket join evenement ev ON ev.id=tk.id_evenement join utilisateur ut ON ut.id=ev.id_user where r.id ="+reservation_id;
                 pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getInt(1));
                 list.add(rs.getTimestamp(2)); 
                 list.add(rs.getInt(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getString(5));
                 list.add(rs.getString(6));
                 list.add(rs.getInt(7));
                 list.add(rs.getString(8)); 
                 list.add(rs.getFloat(9));
                 list.add(rs.getString(10));
                 reservation_list.add(list);
                }
            
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
    
     public List<List> get_reservations_user_by_type (int id_user,String type) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String Request="";
            if(type=="tous"){ 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,type,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id where id_user="+id_user+" ORDER BY created_At DESC";
            }else if(type=="hotel"){ 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,type,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id where id_user="+id_user+" and type='hotel' ORDER BY created_At DESC";

            }else if(type=="maison"){ 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,type,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id where id_user="+id_user+" and type='maison' ORDER BY created_At DESC";

            }else if(type=="voiture"){ 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,type,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id where id_user="+id_user+" and type='voiture' ORDER BY created_At DESC";

            }else { 
                Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,type,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id where id_user="+id_user+" and type='ticket' ORDER BY created_At DESC";
            }
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getInt(1));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getFloat(6));
                 list.add(rs.getInt(7));
                 list.add(rs.getFloat(8));
                 list.add(rs.getString(9));
                 list.add(rs.getInt(10));
                 list.add(rs.getInt(11));
                 list.add(rs.getInt(12));
                 list.add(rs.getInt(13));
                 list.add(rs.getString(14));
                 list.add(rs.getString(15));
                 reservation_list.add(list);
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
      
      
      
    public List<List> get_reservations_user_by_date(String date,String type,int user_id) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String Request="";
            if(type=="tous"){ 
                Request = "SELECT  r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,r.`type`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id WHERE id_user='"+user_id+"' and DATE(created_At) = '"+date+"' ORDER BY created_At DESC";

            }else if(type=="hotel"){ 
                Request = "SELECT  r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,r.`type`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id  WHERE r.`type`='hotel' and r.`id_user`='"+user_id+"' and DATE(created_At) = '"+date+"' ORDER BY created_At DESC";

            }else if(type=="maison"){ 
                Request = "SELECT  r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,r.`type`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id  WHERE r.`type`='maison' and r.`id_user`='"+user_id+"' and DATE(created_At) = '"+date+"' ORDER BY created_At DESC";

            }else if(type=="voiture"){ 
                Request = "SELECT  r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,r.`type`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id WHERE r.`type`='voiture' and r.`id_user`='"+user_id+"' and DATE(created_At) = '"+date+"' ORDER BY created_At DESC";

            }else { 
                Request = "SELECT  r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,taux_avance,montant_paye_avance,r.`etat`,id_chambre,id_voiture,id_ticket,id_maison,r.`type`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join chambre c ON c.id=r.id_chambre JOIN hotel h ON h.id=c.id_hotel WHERE r.`type`='ticket' and r.`id_user`='"+user_id+"' and DATE(created_At) = '"+date+"' ORDER BY created_At DESC";
            }
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                   list.add(rs.getInt(1));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getFloat(6));
                 list.add(rs.getInt(7));
                 list.add(rs.getFloat(8));
                 list.add(rs.getString(9));
                 list.add(rs.getInt(10));
                 list.add(rs.getInt(11));
                 list.add(rs.getInt(12));
                 list.add(rs.getInt(13));
                 list.add(rs.getString(14));
                 list.add(rs.getString(15));
                 reservation_list.add(list);
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  
    //Admin reservations
     public List<List> get_reservations_hotels() {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
              String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,libelle,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join chambre c ON c.id=r.id_chambre JOIN hotel h ON h.id=c.id_hotel ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     
     
     public List<List> get_reservations_hotel_by_user_email(String email) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
        
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,libelle,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join chambre c ON c.id=r.id_chambre JOIN hotel h ON h.id=c.id_hotel WHERE email='"+email+"' ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     
    
    public List<List> get_reservations_by_hotel_name(String hotel_name) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,libelle,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join chambre c ON c.id=r.id_chambre JOIN hotel h ON h.id=c.id_hotel WHERE libelle LIKE('%"+hotel_name+"%') ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
    public List<List> get_reservations_hotels_by_date(String date) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,created_At,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,libelle,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join chambre c ON c.id=r.id_chambre JOIN hotel h ON h.id=c.id_hotel WHERE DATE(created_At) = '"+date+"' ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
    public List<List> get_reservations_voiture() {
        List<List> reservation_list = new ArrayList();
             //List<Reservation> reservation_list = new ArrayList();
             PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,v.`libelle`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join voiture v ON v.id=r.id_voiture ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
                 
                 
  
             }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
    public List<List> get_reservations_voiture_by_user_email(String email) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
        
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,v.`libelle`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join voiture v ON v.id=r.id_voiture WHERE email='"+email+"' ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     public List<List> get_reservations_by_voiture_name(String voiture_name) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,v.`libelle`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join voiture v ON v.id=r.id_voiture WHERE v.libelle LIKE('%"+voiture_name+"%') ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     public List<List> get_reservations_voitures_by_date(String date) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,nom,prenom,taux_avance,montant_paye_avance,r.`etat`,v.`libelle`,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join voiture v ON v.id=r.id_voiture WHERE DATE(created_At) = '"+date+"' ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getString(13));
                 reservation_list.add(list);
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     
      
    
   
    public List<List> get_reservations_maisons() {
        List<List> reservation_list = new ArrayList();
             PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ut.nom,ut.prenom,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join maison m ON m.id=r.id_maison join utilisateur ut ON ut.id=m.id_user ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12)+" "+rs.getString(13));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(14));
                 reservation_list.add(list);

             }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
    public List<List> get_reservations_maison_by_user_email(String email) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
        
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ut.nom,ut.prenom,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join maison m ON m.id=r.id_maison join utilisateur ut ON ut.id=m.id_user WHERE u.email='"+email+"' ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12)+" "+rs.getString(13));
                 list.add(rs.getString(14));
                 reservation_list.add(list);
             }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
    public List<List> get_reservations_maison_by_owner_name(String owner_name) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ut.nom,ut.prenom,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join maison m ON m.id=r.id_maison join utilisateur ut ON ut.id=m.id_user WHERE CONCAT(ut.nom, ut.prenom) LIKE('%"+owner_name+"%') ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12)+" "+rs.getString(13));
                 list.add(rs.getString(14));

                 reservation_list.add(list);
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
    
    public List<List> get_reservations_maison_by_date(String date) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ut.nom,ut.prenom,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join maison m ON m.id=r.id_maison join utilisateur ut ON ut.id=m.id_user WHERE DATE(created_At) = '"+date+"' ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12)+" "+rs.getString(13));
                 list.add(rs.getString(14));
                 reservation_list.add(list);
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
    
    
    public List<List> get_reservations_évenement_ticket() {
        List<List> reservation_list = new ArrayList();
             PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ev.libelle,tk.type,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join ticket tk ON tk.id=r.id_ticket join evenement ev ON ev.id=tk.id_evenement ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getString(13));
                 list.add(rs.getInt(1));
                 list.add(rs.getString(14));
                 reservation_list.add(list);

             }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     public List<List> get_reservations_evenement_ticket_by_user_email(String email) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
        
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ev.libelle,tk.type,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join ticket tk ON tk.id=r.id_ticket join evenement ev ON ev.id=tk.id_evenement WHERE u.email='"+email+"' ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getString(13));
                 list.add(rs.getString(14));
                 reservation_list.add(list);
             }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     
     public List<List> get_reservations_event_name(String event_name) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ev.libelle,tk.type,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join ticket tk ON tk.id=r.id_ticket join evenement ev ON ev.id=tk.id_evenement  WHERE ev.libelle LIKE('%"+event_name+"%') ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getString(13));
                 list.add(rs.getString(14));
                 reservation_list.add(list);
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
     
    public List<List> get_reservations_evenement_by_date(String date) {
        List<List> reservation_list = new ArrayList();
        PreparedStatement  pst;
        try {
             String Request = "SELECT r.`id`,t.`created_At`,date_debut,date_fin,montant_a_payer,reste_a_payer,u.nom,u.prenom,taux_avance,montant_paye_avance,r.`etat`,ev.libelle,tk.type,paymentIntent_id from  utilisateur u join reservation r ON u.id=r.id_user join transaction t ON r.id_transaction=t.id join ticket tk ON tk.id=r.id_ticket join evenement ev ON ev.id=tk.id_evenement WHERE DATE(created_At) = '"+date+"' ORDER BY created_At DESC";
             pst = cnxx.prepareStatement(Request);
             ResultSet rs = pst.executeQuery(Request);
             
             while (rs.next()) {
                 List<Object> list=new ArrayList();
                 list.clear();
                 System.out.println("date"+rs.getDate(3));
                 list.add(rs.getTimestamp(2));
                 list.add(rs.getDate(3));
                 list.add(rs.getDate(4));
                 list.add(rs.getFloat(5));
                 list.add(rs.getInt(9));
                 list.add(rs.getFloat(10));
                 list.add(rs.getFloat(6));
                 list.add(rs.getString(11));
                 list.add(rs.getString(7)+" "+rs.getString(8));
                 list.add(rs.getString(12));
                 list.add(rs.getString(13));
                 list.add(rs.getString(14));
                 reservation_list.add(list);
             }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reservation_list;
    }
   
    
    //--Update reservation--
    public void update_reservation_by_id(int reservation_id) {
        String Request = "UPDATE reservation SET etat='annulée' where id="+reservation_id;
        PreparedStatement  pst;
        try {
                pst = cnxx.prepareStatement(Request);
                pst.executeUpdate(Request); 
                        System.out.println("modification effectué avec succés");
        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
    } 
    
    //--Delete reservation--
    public void delete_reservation_by_id(int reservation_id) {
        String Request = "DELETE FROM reservation where id="+reservation_id;
        PreparedStatement  pst;
        try {
                pst = cnxx.prepareStatement(Request);                    
                pst.executeUpdate(Request);
                        System.out.println("supression effectué avec succés");
        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
    } 
    //statistics 
    public List<List>  get_statistics_incomes_hotels(){  
        PreparedStatement  pst; 
         List<List> hotels = new ArrayList();
        try { 
                String Request = "SELECT h.`libelle`,SUM(tk.`montant_paye_avance`) from reservation r join chambre ch on r.id_chambre=ch.id join hotel h on h.id=ch.id_hotel join transaction tk on tk.id=r.id_transaction GROUP BY (ch.id_hotel) LIMIT 10;";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                    List<Object> list=new ArrayList();
                    list.clear();
                    list.add(rs.getString(1));
                    list.add(rs.getFloat(2));
                    hotels.add(list);
                }               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return hotels;
    }
    
    
    public List<List>  get_statistics_nb_reservations_hotels(){  
        PreparedStatement  pst; 
        List<List> hotels = new ArrayList();
        try { 
                String Request = "SELECT h.`libelle`,count(*) as nb_reservations from reservation r join chambre ch on r.id_chambre=ch.id join hotel h on h.id=ch.id_hotel join transaction tk on tk.id=r.id_transaction GROUP BY (ch.id_hotel)  ORDER BY nb_reservations DESC LIMIT 10;";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                    List<Object> list=new ArrayList();
                    list.clear();
                    list.add(rs.getString(1));
                    list.add(rs.getInt(2));
                    hotels.add(list);
                }                               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return hotels;
    }
    
     public Float  get_statistics_incomes_annualy_hotels(String year){  
        PreparedStatement  pst; 
        Float result=0f;
        try { 
                String Request = "SELECT SUM(tk.montant_paye_avance) from reservation r join chambre ch on r.id_chambre=ch.id join hotel h on h.id=ch.id_hotel join transaction tk on tk.id=r.id_transaction where EXTRACT(year from tk.created_At)='"+year+"'";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                    result=rs.getFloat(1);
                }         
               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return result;
     } 
     
     public Float  get_statistics_incomes_monthly_hotels(String month,String year){  
        PreparedStatement  pst; 
        Float result=0f;
        try { 
                String Request = "SELECT SUM(tk.montant_paye_avance) from reservation r join chambre ch on r.id_chambre=ch.id join hotel h on h.id=ch.id_hotel join transaction tk on tk.id=r.id_transaction where EXTRACT(month from tk.created_At)='"+month+"' and EXTRACT(year from tk.created_At)='"+year+"'";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                    result=rs.getFloat(1);
                }         
               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return result;
    } 
     
    
     public List<List>  get_statistics_incomes_maisons(){  
        PreparedStatement  pst; 
         List<List> hotels = new ArrayList();
        try { 
                String Request = "SELECT ut.`email`,SUM(tk.`montant_paye_avance`) from reservation r join maison m on r.id_maison=m.id  join transaction tk on tk.id=r.id_transaction join utilisateur ut on ut.id=m.id_user GROUP BY m.id_user LIMIT 10;";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                    List<Object> list=new ArrayList();
                    list.clear();
                    list.add(rs.getString(1));
                    list.add(rs.getFloat(2));
                    hotels.add(list);
                }               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return hotels;
    }
    
    
    public List<List> get_statistics_nb_reservations_maisons(){  
        PreparedStatement  pst; 
        List<List> hotels = new ArrayList();
        try{
                String Request = "SELECT ut.`nom`,count(*) as nb_reservations from reservation r join maison m on r.id_maison=m.id  join transaction tk on tk.id=r.id_transaction join utilisateur ut on ut.id=m.id_user GROUP BY (r.id_maison)  ORDER BY nb_reservations DESC LIMIT 10;";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                    List<Object> list=new ArrayList();
                    list.clear();
                    list.add(rs.getString(1));
                    list.add(rs.getInt(2));
                    hotels.add(list);
                }                               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return hotels;
    }
    
     public Float  get_statistics_incomes_annualy_maisons(String year){  
        PreparedStatement  pst; 
        Float result=0f;
        try { 
                String Request = "SELECT SUM(tk.montant_paye_avance)  from reservation r join maison m on r.id_maison=m.id  join transaction tk on tk.id=r.id_transaction join utilisateur ut on ut.id=m.id_user where EXTRACT(year from tk.created_At)='"+year+"'";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                    result=rs.getFloat(1);
                }         
               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return result;
     } 
     
     public Float  get_statistics_incomes_monthly_maisons(String month,String year){  
        PreparedStatement  pst; 
        Float result=0f;
        try { 
                String Request = "SELECT SUM(tk.montant_paye_avance) from reservation r join maison m on r.id_maison=m.id  join transaction tk on tk.id=r.id_transaction join utilisateur ut on ut.id=m.id_user where EXTRACT(month from tk.created_At)='"+month+"' and EXTRACT(year from tk.created_At)='"+year+"'";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                    result=rs.getFloat(1);
                }         
               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return result;
    }
     
     
       public List<List>  get_statistics_incomes_voitures(){  
        PreparedStatement  pst; 
         List<List> hotels = new ArrayList();
        try { 
                String Request = "SELECT ut.`nom`,SUM(tk.`montant_paye_avance`) from reservation r join voiture v on r.id_voiture=v.id  join transaction tk on tk.id=r.id_transaction join utilisateur ut on ut.id=v.id_user GROUP BY v.id_user LIMIT 10;";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                    List<Object> list=new ArrayList();
                    list.clear();
                    list.add(rs.getString(1));
                    list.add(rs.getFloat(2));
                    hotels.add(list);
                }               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return hotels;
    }
    
    
    public List<List> get_statistics_nb_reservations_voitures(){  
        PreparedStatement  pst; 
        List<List> hotels = new ArrayList();
        try{
                String Request = "SELECT ut.`nom`,count(*) as nb_reservations from reservation r join voiture v on r.id_voiture=v.id  join transaction tk on tk.id=r.id_transaction join utilisateur ut on ut.id=v.id_user  GROUP BY (v.id_user)  ORDER BY nb_reservations DESC LIMIT 10;";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                    List<Object> list=new ArrayList();
                    list.clear();
                    list.add(rs.getString(1));
                    list.add(rs.getInt(2));
                    hotels.add(list);
                }                               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return hotels;
    }
    
     public Float  get_statistics_incomes_annualy_voitures(String year){  
        PreparedStatement  pst; 
        Float result=0f;
        try { 
                String Request = "SELECT SUM(tk.montant_paye_avance)  from reservation r join voiture v on r.id_voiture=v.id  join transaction tk on tk.id=r.id_transaction join utilisateur ut on ut.id=v.id_user  where EXTRACT(year from tk.created_At)='"+year+"'";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                    result=rs.getFloat(1);
                }         
               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return result;
     } 
     
     public Float  get_statistics_incomes_monthly_voitures(String month,String year){  
        PreparedStatement  pst; 
        Float result=0f;
        try { 
                String Request = "SELECT SUM(tk.montant_paye_avance) from reservation r join voiture v on r.id_voiture=v.id  join transaction tk on tk.id=r.id_transaction join utilisateur ut on ut.id=v.id_user  where EXTRACT(month from tk.created_At)='"+month+"' and EXTRACT(year from tk.created_At)='"+year+"'";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                    result=rs.getFloat(1);
                }         
               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return result;
    }
     
     
      public List<List>  get_statistics_incomes_evenements(){  
        PreparedStatement  pst; 
         List<List> hotels = new ArrayList();
        try { 
                String Request = "SELECT ev.`libelle`,SUM(tk.`montant_paye_avance`) from reservation r join ticket ti on r.id_ticket=ti.id  join transaction tk on tk.id=r.id_transaction join evenement ev on ev.id=ti.id_evenement GROUP BY ti.id_evenement LIMIT 10;";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                    List<Object> list=new ArrayList();
                    list.clear();
                    list.add(rs.getString(1));
                    list.add(rs.getFloat(2));
                    hotels.add(list);
                }               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return hotels;
    }
     
     
     public List<List> get_statistics_nb_reservations_evenements(){  
        PreparedStatement  pst; 
        List<List> hotels = new ArrayList();
        try{
                String Request = "SELECT ev.`libelle`,count(*) as nb_reservations  from reservation r join ticket ti on r.id_ticket=ti.id  join transaction tk on tk.id=r.id_transaction join evenement ev on ev.id=ti.id_evenement  GROUP BY ti.id_evenement  ORDER BY nb_reservations DESC LIMIT 10;";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                while (rs.next()) {
                    List<Object> list=new ArrayList();
                    list.clear();
                    list.add(rs.getString(1));
                    list.add(rs.getInt(2));
                    hotels.add(list);
                }                               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return hotels;
    }
    
     public Float  get_statistics_incomes_annualy_evenements(String year){  
        PreparedStatement  pst; 
        Float result=0f;
        try { 
                String Request = "SELECT SUM(tk.montant_paye_avance)   from reservation r join ticket ti on r.id_ticket=ti.id  join transaction tk on tk.id=r.id_transaction join evenement ev on ev.id=ti.id_evenement  where EXTRACT(year from tk.created_At)='"+year+"'";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                    result=rs.getFloat(1);
                }         
               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return result;
     } 
     
     public Float  get_statistics_incomes_monthly_evenements(String month,String year){  
        PreparedStatement  pst; 
        Float result=0f;
        try { 
                String Request = "SELECT SUM(tk.montant_paye_avance)  from reservation r join ticket ti on r.id_ticket=ti.id  join transaction tk on tk.id=r.id_transaction join evenement ev on ev.id=ti.id_evenement  where EXTRACT(month from tk.created_At)='"+month+"' and EXTRACT(year from tk.created_At)='"+year+"'";
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                    result=rs.getFloat(1);
                }         
               
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return result;
    } 
     
     public Utilisateur  get_user_by_reservation_id(int reservation_id){  
        PreparedStatement  pst;  
        Utilisateur user=new Utilisateur();
        try { 
                String Request = "SELECT u.`nom`,u.`prenom`,u.`email` from utilisateur u join reservation r on r.id_user=u.id  where r.id="+reservation_id;
                pst = cnxx.prepareStatement(Request);
                ResultSet rs = pst.executeQuery(Request);
                 while (rs.next()) {
                   user.setNom(rs.getString(1));
                   user.setPrenom(rs.getString(2));
                   user.setEmail(rs.getString(3));
                }         
        }catch (SQLException e) {
                System.err.println(e.getMessage());
        }
        return user;
    } 
     
    
     
     
    
   
   
 
    
    
    
    
    
    
    
    
}
