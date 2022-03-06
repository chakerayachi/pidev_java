/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
public class ServiceHotelIPM implements Ihebergement<Hotel>{
    
    Connection cnxx ;

    public ServiceHotelIPM() {
        cnxx = MyDB.getInstance().getConnection();
       
    }
    
    public int create(Hotel h) {
        String columnNames[] = new String[] { "id" };
        PreparedStatement  pst;
        int hId=0;
         try {
            String req = "INSERT INTO hotel (adresse,ville,region,num_tel,description,libelle,nb_etoiles,image,id_user) VALUES (?,?,?,?,?,?,?,?,?)";
                        System.out.println(h);
          PreparedStatement ps = cnxx.prepareStatement(req, columnNames);
          System.out.println(req);
            ps.setString(1,h.getAdresse());
            ps.setString(2,h.getVille());
            ps.setString(3,h.getRegion());
            ps.setInt(4,h.getNum_tel());
            ps.setString(5,h.getDescription());
            ps.setString(6,h.getLibelle());
            ps.setInt(7,h.getNb_etoile());
            ps.setString(8,h.getImage());
            ps.setInt(9,h.getId_user());

            ps.executeUpdate();
            java.sql.ResultSet generatedKeys = ps.getGeneratedKeys();
            if ( generatedKeys.next() ) {
                    hId = generatedKeys.getInt(1);
                }
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in inserting Hotel");        }
         return hId;
    }

    @Override
    public void modify(Hotel h) {
        try {
            String req= "update hotel set adresse = ? , ville = ? , region = ? , num_tel = ? , description = ? , libelle = ? , nb_etoiles = ?  where id= ?";
            PreparedStatement ps=cnxx.prepareStatement(req);
            ps.setString(1,h.getAdresse());
            ps.setString(2,h.getVille());
            ps.setString(3,h.getRegion());
            ps.setInt(4,h.getNum_tel());
            ps.setString(5,h.getDescription());
            ps.setString(6,h.getLibelle());
            ps.setInt(7,h.getNb_etoile());
            ps.setInt(8,h.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void delete(int id) {
        try {
            String req = "DELETE FROM hotel WHERE id = ?" ;
          PreparedStatement ps = cnxx.prepareStatement(req);
          System.out.println(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in deleting Hotel");        }
    }
    

    @Override
    public List<Hotel> afficher() {
     ArrayList<Hotel>  list = new ArrayList();
       try {
                  String req ="Select * FROM hotel";
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Hotel h = new Hotel();
                         h.setId(rs.getInt("id"));
                         h.setAdresse(rs.getString("adresse"));
                         h.setVille(rs.getString("ville"));
                         h.setRegion(rs.getString("region"));
                         h.setNum_tel(rs.getInt("num_tel"));
                         h.setDescription(rs.getString("description"));
                         h.setLibelle(rs.getString("libelle"));
                         h.setImage(rs.getString("image"));
                         h.setNb_etoile(rs.getInt("nb_etoiles"));


                         list.add(h);

             }             
   
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in selecting Hotel");

        }   
     return list;    
    }
    // select hotels par nbEtoile
    public List<Hotel> afficherHotelsParNbEtoile(int nb) {
     ArrayList<Hotel>  list = new ArrayList();
       try {
                  String req ="Select * FROM hotel where nb_etoiles="+nb;
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Hotel h = new Hotel();
                         h.setId(rs.getInt("id"));
                         h.setAdresse(rs.getString("adresse"));
                         h.setVille(rs.getString("ville"));
                         h.setRegion(rs.getString("region"));
                         h.setNum_tel(rs.getInt("num_tel"));
                         h.setDescription(rs.getString("description"));
                         h.setLibelle(rs.getString("libelle"));
                         h.setNb_etoile(rs.getInt("nb_etoiles"));
                         h.setImage(rs.getString("image"));
                         list.add(h);

             }             
   
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in selecting Hotel");

        }   
     return list;    
    }
    
    // select hotels par region
    public List<Hotel> afficherHotelsParNbRegion(String reg) {
     ArrayList<Hotel>  list = new ArrayList();
       try {
                  String req ="Select * FROM hotel where region='"+reg+"'";
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Hotel h = new Hotel();
                         h.setId(rs.getInt("id"));
                         h.setAdresse(rs.getString("adresse"));
                         h.setVille(rs.getString("ville"));
                         h.setRegion(rs.getString("region"));
                         h.setNum_tel(rs.getInt("num_tel"));
                         h.setDescription(rs.getString("description"));
                         h.setLibelle(rs.getString("libelle"));
                         h.setNb_etoile(rs.getInt("nb_etoiles"));
                         h.setImage(rs.getString("image"));
                         list.add(h);

             }             
   
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in selecting Hotel");

        }   
     return list;    
    }
    
    // select hotels par mot
    public List<Hotel> afficherHotelsParMot(String mot) {
     ArrayList<Hotel>  list = new ArrayList();
       try {
                  String req ="Select * FROM hotel description like '%"+mot+"%'";
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Hotel h = new Hotel();
                         h.setId(rs.getInt("id"));
                         h.setAdresse(rs.getString("adresse"));
                         h.setVille(rs.getString("ville"));
                         h.setRegion(rs.getString("region"));
                         h.setNum_tel(rs.getInt("num_tel"));
                         h.setDescription(rs.getString("description"));
                         h.setLibelle(rs.getString("libelle"));
                         h.setNb_etoile(rs.getInt("nb_etoiles"));
                         list.add(h);

             }             
   
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in selecting Hotel");

        }   
     return list;    
    }
    
    
    
}
