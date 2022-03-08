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
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author alaaz
 */
public class ServiceTransactionIMP { 
     Connection cnxx;

    public ServiceTransactionIMP() {
        this.cnxx =MyDB.getInstance().getConnection();
    } 
    
    //---Create reservations--
    public int add_transaction(Transaction tr){
        String Request = "INSERT INTO transaction (taux_avance,taux_commission,taux_garantie,montant_paye_avance,montant_commission,montant_garantie,paymentIntent_id) VALUES(?,?,?,?,?,?,?)" ; 
        String columnNames[] = new String[] { "id" };
        PreparedStatement  pst; 
        int transaction_generated_id=0;
        try { 
                
                pst = cnxx.prepareStatement(Request,columnNames); 
                pst.setInt(1, tr.getTaux_avance());
                pst.setInt(2, tr.getTaux_commission());
                pst.setInt(3, tr.getTaux_garantie());
                pst.setFloat(4, tr.getMontant_avance());
                pst.setFloat(5, tr.getMontant_commission());
                pst.setFloat(6, tr.getMontant_garantie());
                pst.setString(7, tr.getPaymentIntent_id());
                pst.executeUpdate();
                java.sql.ResultSet generatedKeys = pst.getGeneratedKeys();
                if ( generatedKeys.next() ) {
                    transaction_generated_id = generatedKeys.getInt(1);
                }
                            System.out.println("transaction effectué avec  succés");
                            
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }
        return transaction_generated_id;
    } 
    
    //--show transactions
    public Transaction get_transaction_by_id(int  transaction_id) { 
        
        Transaction transaction=null;
        String Request = "SELECT * FROM transaction where id="+transaction_id;
        PreparedStatement  pst;
        try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs;
            rs = pst.executeQuery(Request);
             while (rs.next()) {
                 transaction=new Transaction(transaction_id,rs.getTimestamp(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getFloat(6),rs.getFloat(7),rs.getFloat(8),rs.getString(9));
            }
    
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return transaction;
    }
   
    
    public List<Transaction> get_all_transactions() {
         List<Transaction> transaction_list = new ArrayList(); 
         String Request = "SELECT * FROM transaction";
         PreparedStatement  pst;
         try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
                Transaction transaction=new Transaction(rs.getInt(0),rs.getTimestamp(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getFloat(6),rs.getFloat(7),rs.getFloat(8),rs.getString(9));
                transaction_list.add(transaction);
            }
            } catch (SQLException e) {
            System.err.println(e.getMessage());
            //   return null;
        }
        return transaction_list;
    } 
        
    
    
}
