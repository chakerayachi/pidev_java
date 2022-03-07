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
public class ServiceChambreIPM implements Ihebergement<Chambre>{
    
    Connection cnxx ;

    public ServiceChambreIPM() {
        cnxx = MyDB.getInstance().getConnection();
       
    }

    
    public int create(Chambre c) {
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
        return c.getId();
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

   

    @Override
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

    @Override
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
    // Getting Room By ID_HOTEL
     public  List<Chambre> getRoomById(int id) {
        ArrayList<Chambre>  list = new ArrayList();
       try {
                  String req ="Select * FROM chambre where id_hotel="+id;
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

    

   
    
}
