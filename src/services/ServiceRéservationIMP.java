/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import entities.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    public void add_reservation_hotel(Reservation rs){
        String Request = "INSERT INTO reservation (date_debut,date_fin,montant_a_payer,reste_a_payer,id_hotel,id_user,etat,id_transaction,type) VALUES(?,?,?,?,?,?,?,?,?)" ;
        PreparedStatement  pst;
        try {   
                ServiceTransactionIMP service=new ServiceTransactionIMP(); 
                Transaction transaction =new Transaction(rs.getMontant_a_payer()); 
                transaction.setId(service.add_transaction(transaction)); 
                rs.setId_transaction(transaction.getId()); 
                rs.setReste_a_payer(transaction.getMontant_avance());
                pst = cnxx.prepareStatement(Request);
                pst.setDate(1, rs.getDate_debut());
                pst.setDate(2, rs.getDate_fin());
                pst.setFloat(3, rs.getMontant_a_payer());
                pst.setFloat(4, rs.getReste_a_payer());
                pst.setInt(5, rs.getId_hotel());
                pst.setInt(6,rs.getId_user()); 
                pst.setString(7, rs.getEtat());
                pst.setInt(8,rs.getId_transaction());
                pst.setString(9,"hotel");
                pst.executeUpdate();
                            System.out.println("Réservation à l''hotel effectué avec succés");
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }

    }  

    public void add_reservation_house(Reservation rs){
        String Request = "INSERT INTO reservation (date_debut,date_fin,montant_a_payer,reste_a_payer,id_maison,id_user,etat,id_transaction,type) VALUES(?,?,?,?,?,?,?,?,?)" ;
        PreparedStatement  pst;
        try {    
                ServiceTransactionIMP service=new ServiceTransactionIMP(); 
                Transaction transaction =new Transaction(rs.getMontant_a_payer()); 
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
                            System.out.println("Réservation à la maison a été effectué avec succés");
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }

    }
    public void add_reservation_car(Reservation rs){
        String Request = "INSERT INTO reservation (date_debut,date_fin,montant_a_payer,reste_a_payer,id_maison,id_user,etat,id_transaction,type) VALUES(?,?,?,?,?,?,?,?,?)" ;
        PreparedStatement  pst;
        try {    
                ServiceTransactionIMP service=new ServiceTransactionIMP(); 
                Transaction transaction =new Transaction(rs.getMontant_a_payer()); 
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
                            System.out.println("Réservation de la voiture a été effectué avec succés");
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }
    }
    public void add_reservation_ticket(Reservation rs){
        String Request = "INSERT INTO reservation (montant_a_payer,reste_a_payer,id_ticket,id_user,etat,id_transaction,type) VALUES(?,?,?,?,?,?,?)" ;
        PreparedStatement  pst;
        try {    
                ServiceTransactionIMP service=new ServiceTransactionIMP(); 
                Transaction transaction =new Transaction();  
                transaction.setTaux_avance(100);
                transaction.setTaux_commission(0);
                transaction.setTaux_garantie(0);
                transaction.setMontant_avance(rs.getMontant_a_payer());
                transaction.setId(service.add_transaction(transaction)); 
                rs.setId_transaction(transaction.getId()); 
                pst = cnxx.prepareStatement(Request);
                pst.setFloat(3, rs.getMontant_a_payer());
                pst.setFloat(4, rs.getReste_a_payer());
                pst.setInt(5, rs.getId_ticket());
                pst.setInt(6,rs.getId_user()); 
                pst.setString(7, rs.getEtat());
                pst.setInt(8,rs.getId_transaction());
                pst.setString(9,"ticket");
                pst.executeUpdate();
                            System.out.println("Réservation du billet d'événement a été effectué avec succés");
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }
    }
    
    //---Show reservations--  
    public Reservation get_reservation_by_id(int  reservation_id) { 
        String Request = "SELECT * FROM reservation where id="+reservation_id;
        PreparedStatement  pst;
        Reservation  res=null;
        try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request) ; 
            res = new Reservation(rs.getInt(1),rs.getTimestamp(2),rs.getDate(3),rs.getDate(4),rs.getFloat(5),rs.getFloat(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }
    
     public List<Reservation> get_all_reservations() {
         List<Reservation> reservation_list = new ArrayList(); 
         String Request = "SELECT * FROM reservation ORDER BY created_At DESC";
         PreparedStatement  pst;
         try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
                Reservation res = new Reservation(rs.getInt(1),rs.getTimestamp(2),rs.getDate(3),rs.getDate(4),rs.getFloat(5),rs.getFloat(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14));
                reservation_list.add(res);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //   return null;
        }
        return reservation_list;
    }
     
     public List<Reservation> get_reservations_by_type(String choice) {
         
         List<Reservation> reservation_list = new ArrayList();
         String Request;
         PreparedStatement  pst;
         
         try {
           switch(choice){
            case "hotel": 
                Request = "SELECT * FROM reservation where type='hotel' ORDER BY created_At DESC";
            case "maison":
                Request = "SELECT * FROM reservation where type='maison' ORDER BY created_At DESC";
            break;
            
            case "voiture":
                Request = "SELECT * FROM reservation where type='voiture' ORDER BY created_At DESC";
            break;

            default:
                Request = "SELECT * FROM reservation where type='ticket' ORDER BY created_At DESC";
            break;
         }
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
                Reservation res = new Reservation(rs.getInt(1),rs.getTimestamp(2),rs.getDate(3),rs.getDate(4),rs.getFloat(5),rs.getFloat(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14));
                reservation_list.add(res);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //   return null;
        }
        return reservation_list;
    }
     
    public List<Reservation> get_reservations_by_user_id(int user_id) {
         List<Reservation> reservation_list = new ArrayList(); 
         String Request = "SELECT * FROM reservation where id_user="+user_id+"ORDER BY created_At DESC";
         PreparedStatement  pst;
         try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
                Reservation res = new Reservation(rs.getInt(1),rs.getTimestamp(2),rs.getDate(3),rs.getDate(4),rs.getFloat(5),rs.getFloat(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14));
                reservation_list.add(res);
            }
            } catch (SQLException e) {
            System.err.println(e.getMessage());
            //   return null;
        }
        return reservation_list;
    }
    
    public List<Reservation> get_reservations_ordred_by_price() {
         List<Reservation> reservation_list = new ArrayList(); 
         String Request = "SELECT * FROM reservation ORDER BY montant_a_payer DESC";
         PreparedStatement  pst;
         try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
                Reservation res = new Reservation(rs.getInt(1),rs.getTimestamp(2),rs.getDate(3),rs.getDate(4),rs.getFloat(5),rs.getFloat(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14));
                reservation_list.add(res);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //   return null;
        }
        return reservation_list;
    }
    public List<Reservation> get_reservations_ordred_by_date() {
         List<Reservation> reservation_list = new ArrayList(); 
         String Request = "SELECT * FROM reservation ORDER BY created_At DESC";
         PreparedStatement  pst;
         try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
                Reservation res = new Reservation(rs.getInt(1),rs.getTimestamp(2),rs.getDate(3),rs.getDate(4),rs.getFloat(5),rs.getFloat(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14));
                reservation_list.add(res);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            //   return null;
        }
        return reservation_list;
    }
      
     
     
    
   
    //--Update reservation--
    public void update_reservation_by_id(int reservation_id) {
        String Request = "UPDATE reservation SET etat='annulé' where id="+reservation_id;
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
    
   
  
    
    
    
    
    
    
    
    
}
