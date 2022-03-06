/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import entities.Reservation;
import entities.Ticket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author sabri belhaj amor
 */
public class ServiceEvenementIMP {
     Connection cnxx;

    public ServiceEvenementIMP() {
        this.cnxx =MyDB.getInstance().getConnection();
    }
    
    
    //---Create events--
    public void add_event(Evenement e){
        String Request = "INSERT INTO evenement (libelle,date,description,emplacement,nb_place,duree,id_user) VALUES(?,?,?,?,?,?,?)" ;
        PreparedStatement  pst;
        try {
                pst = cnxx.prepareStatement(Request);
                pst.setString(1,e.getLibelle());
                pst.setDate(2, (Date) e.getDate());
                pst.setString(3, e.getDescription());
                pst.setString(4,e.getEmplacement());
                pst.setInt(5,e.getNb_place());
                pst.setInt(6,e.getDuree());
                pst.setInt(7,e.getId_user());
                pst.executeUpdate();
                            System.out.println("Ajout d'évènement effectué avec succés");
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }

    }
    
    //--Modify events--
    public void modify(Evenement e) {
        try {
            String req= "update evenement set libelle = ? , date = ? , description = ? , emplacement = ? , nb_place = ? , duree = ? , id_user = ? where id= ?";
            PreparedStatement ps=cnxx.prepareStatement(req);
            ps.setString(1,e.getLibelle());
            ps.setDate(2, (Date) e.getDate());
            ps.setString(3,e.getDescription());
            ps.setString(4,e.getEmplacement());
            ps.setInt(5,e.getNb_place());
            ps.setInt(6,e.getDuree());
            ps.setInt(7,16);
            ps.setInt(8,e.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //--delete events--
    public void delete(int id) {
        try {
            String req = "DELETE FROM evenement WHERE id = ?" ;
          PreparedStatement ps = cnxx.prepareStatement(req);
          System.out.println(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
      }
    }
    
    //--display events--
    public List<Evenement> afficher() {
     ArrayList<Evenement>  list = new ArrayList();
       try {
                  String req ="Select * FROM evenement";
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Evenement e = new Evenement();
                         e.setId(rs.getInt(1));
                         e.setLibelle(rs.getString(2));
                         e.setDate(rs.getDate(3));
                         e.setDescription(rs.getString(4));
                         e.setEmplacement(rs.getString(5));
                         e.setNb_place(rs.getInt(6));
                         e.setDuree(rs.getInt(7));
                         e.setId_user(rs.getInt(8));

                         list.add(e);

             }             
   
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                            System.out.println("Error in selecting Event");

        }   
     return list;    
    }
    
    }
