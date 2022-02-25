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
        String Request = "INSERT INTO evenement (libelle,date,description,emplacement,nb_places,duree,id_user) VALUES(?,?,?,?,?,?,?)" ;
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
    public void modify(int id) {
        try {
            Statement stm = cnxx.createStatement();
            String req = "UPDATE evenement SET emplacement='douz' where id="+id;
                    
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            
            System.out.println("Error in updating evenement");
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
                         e.setId_user(rs.getInt(9));

                         list.add(e);

             }             
   
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                            System.out.println("Error in selecting Event");

        }   
     return list;    
    }
    
    }
