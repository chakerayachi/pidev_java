/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chambre;
import entities.Hotel;
import java.sql.Connection;
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
 * @author Wael
 */
public class ServiceChambreIPM {
    
    Connection cnxx ;

    public ServiceChambreIPM() {
        cnxx = MyDB.getInstance().getConnection();
       
    }

    
    public void create(Chambre c) {
        try {
            String req = "INSERT INTO chambre (type,id_hotel,prix) VALUES (?,?,?)";
                        System.out.println(c);
          PreparedStatement ps = cnxx.prepareStatement(req);
          System.out.println(req);
            ps.setString(1,c.getType());
            ps.setInt(2,c.getId_hotel());
            ps.setFloat(3,c.getPrix());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in inserting Chambre");        }
    }

    
    public void modify(Chambre c) {
        try {
            String req= "update chambre set prix = ? , type = ? where id= ?";
            PreparedStatement ps=cnxx.prepareStatement(req);
            ps.setFloat(1,c.getPrix());
            ps.setString(2,c.getType());
            ps.setInt(3,c.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    public void delete(int id) {
        try {
            String req = "DELETE FROM chambre WHERE id = ?" ;
          PreparedStatement ps = cnxx.prepareStatement(req);
          System.out.println(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in deleting chambre");        }
    }

    public List<Chambre> afficher() {
        ArrayList<Chambre>  list = new ArrayList();
       try {
                  String req ="Select * FROM chambre";
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                         Chambre c = new Chambre();
                         c.setId(rs.getInt("id"));
                         c.setPrix(rs.getFloat("prix"));
                         c.setType(rs.getString("type"));
                         c.setId_hotel(rs.getInt("id_hotel"));
                         list.add(c);

             }             
   
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in selecting chambre");

        }   
     return list;    
    } 
    
    
    public List<Chambre> get_chambre_by_hotel_id(int id_hotel){ 
        List<Chambre> Chambre_list = new ArrayList(); 
         String Request = "SELECT * FROM chambre where disponnibiliter > 0 and id_hotel="+id_hotel;
         PreparedStatement  pst;
         try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
                Chambre chc = new Chambre(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getFloat(4),rs.getInt(5));
                Chambre_list.add(chc);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Chambre_list;
    }
    
    public int get_disponibiliter_id(int id_chambre){
        int nbr=0;
         String Request = "SELECT disponnibiliter FROM chambre where id="+id_chambre;
         PreparedStatement  pst;
         try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
                nbr=rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return nbr;
    }  
 
     public void update_chambre_disponibiliter(int id_chambre) {
        String Request = "UPDATE chambre SET disponibiliter= disponibiliter-1 where id="+id_chambre;
        PreparedStatement  pst;
        try {
                pst = cnxx.prepareStatement(Request);
                pst.executeUpdate(Request); 
                        System.out.println("modification effectué avec succés");
        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
    } 
    

    

   
    
}
