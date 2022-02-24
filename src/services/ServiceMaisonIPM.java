/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Hotel;
import entities.Maison;
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
public class ServiceMaisonIPM implements Ihebergement<Maison>{
Connection cnxx ;

    public ServiceMaisonIPM() {
        cnxx = MyDB.getInstance().getConnection();
       
    }

    @Override
    public void create(Maison m) {
        try {
            String req = "INSERT INTO maison (adresse,region,num_tel,description,capacite,nb_chambres,prix,id_user) VALUES (?,?,?,?,?,?,?,?)";
            System.out.println(m);
            PreparedStatement ps = cnxx.prepareStatement(req);
            System.out.println(req);
            ps.setString(1,m.getAdresse());
            ps.setString(2,m.getRegion());
            ps.setInt(3,m.getNum_tel());
            ps.setString(4,m.getDescription());
            ps.setInt(5,m.getCapacite());
            ps.setInt(6,m.getNb_chambres());
            ps.setFloat(7,m.getPrix());
            ps.setInt(8,m.getId_user());

            ps.executeUpdate();
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in inserting Maison");        }
    }

    @Override
    public void modify(Maison m) {
        try {
            String req= "update maison set adresse = ? , ville = ? , region = ? , num_tel = ? , description = ? , capacite = ? , nb_chambres = ? , prix = ?  where id= ?";
            PreparedStatement ps=cnxx.prepareStatement(req);
            ps.setString(1,m.getAdresse());
            ps.setString(2,m.getRegion());
            ps.setInt(3,m.getNum_tel());
            ps.setString(4,m.getDescription());
            ps.setInt(5,m.getCapacite());
            ps.setInt(6,m.getNb_chambres());
            ps.setFloat(7,m.getPrix());
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
                            System.out.println("Error in deleting maison");        }
    }

    public List<Maison> afficher() {
        ArrayList<Maison>  list = new ArrayList();
        try {
                  String req ="Select * FROM maison";
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Maison m = new Maison();
                         m.setId(rs.getInt("id"));
                         m.setAdresse(rs.getString("adresse"));
                         m.setRegion(rs.getString("region"));
                         m.setNum_tel(rs.getInt("num_tel"));
                         m.setDescription(rs.getString("description"));
                         m.setCapacite(rs.getInt("capacite"));
                         m.setNb_chambres(rs.getInt("nb_chambres"));
                         m.setPrix(rs.getFloat("prix"));


                         list.add(m);

             }             
   
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in selecting Hotel");

        }   
     return list;    
    }    
    
    //afficher les maison d'accueil
    public List<Maison> afficherMaisonAccueil() {
        ArrayList<Maison>  list = new ArrayList();
        try {
                  String req ="Select * FROM maison where prix = 0";
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Maison m = new Maison();
                         m.setId(rs.getInt("id"));
                         m.setAdresse(rs.getString("adresse"));
                         m.setRegion(rs.getString("region"));
                         m.setNum_tel(rs.getInt("num_tel"));
                         m.setDescription(rs.getString("description"));
                         m.setCapacite(rs.getInt("capacite"));
                         m.setNb_chambres(rs.getInt("nb_chambres"));
                         m.setPrix(rs.getFloat("prix"));


                         list.add(m);

             }             
   
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in selecting Hotel");

        }   
     return list;    
    }
    
    //affichage maisons par region
    
    public List<Maison> afficherParRegion(String region) {
        ArrayList<Maison>  list = new ArrayList();
        try {
                  String req ="Select * FROM maison where region ="+region;
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Maison m = new Maison();
                         m.setId(rs.getInt("id"));
                         m.setAdresse(rs.getString("adresse"));
                         m.setRegion(rs.getString("region"));
                         m.setNum_tel(rs.getInt("num_tel"));
                         m.setDescription(rs.getString("description"));
                         m.setCapacite(rs.getInt("capacite"));
                         m.setNb_chambres(rs.getInt("nb_chambres"));
                         m.setPrix(rs.getFloat("prix"));


                         list.add(m);

             }             
   
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in selecting Hotel");

        }   
     return list;    
    }
    
    //affichage maisons par capacite
    
    public List<Maison> afficherParCapacite(int cp) {
        ArrayList<Maison>  list = new ArrayList();
        try {
                  String req ="Select * FROM maison where capacite ="+cp;
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Maison m = new Maison();
                         m.setId(rs.getInt("id"));
                         m.setAdresse(rs.getString("adresse"));
                         m.setRegion(rs.getString("region"));
                         m.setNum_tel(rs.getInt("num_tel"));
                         m.setDescription(rs.getString("description"));
                         m.setCapacite(rs.getInt("capacite"));
                         m.setNb_chambres(rs.getInt("nb_chambres"));
                         m.setPrix(rs.getFloat("prix"));


                         list.add(m);

             }             
   
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in selecting Hotel");

        }   
     return list;    
    }
    
    //affichage maisons par nb_chambre
    
    public List<Maison> afficherParRegion(int nb) {
        ArrayList<Maison>  list = new ArrayList();
        try {
                  String req ="Select * FROM maison where nb_chambres ="+nb;
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Maison m = new Maison();
                         m.setId(rs.getInt("id"));
                         m.setAdresse(rs.getString("adresse"));
                         m.setRegion(rs.getString("region"));
                         m.setNum_tel(rs.getInt("num_tel"));
                         m.setDescription(rs.getString("description"));
                         m.setCapacite(rs.getInt("capacite"));
                         m.setNb_chambres(rs.getInt("nb_chambres"));
                         m.setPrix(rs.getFloat("prix"));


                         list.add(m);

             }             
   
        } catch (SQLException ex) {
                Logger.getLogger(ServiceHotelIPM.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in selecting Hotel");

        }   
     return list;    
    }
    
    
    
}
