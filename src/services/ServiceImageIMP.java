/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import entities.Images;
import entities.Voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;
import utils.MyDB;

/**
 *
 * @author Hani
 */
public class ServiceImageIMP implements IService<Images>{
    
    Connection cnx;

    public ServiceImageIMP() {
        cnx = MyDB.getInstance().getConnection();
    }
// la requet d'ajout d'une categorie
    @Override
    public void ajout(Images t) {
        try {
            String req = "insert into images  (img_blob,id_voiture) values (?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1,t.getImg_blob());
            st.setInt(2, t.getId_voiture());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
// requete de modification
    @Override
    public void modifier(Images t) {
        try {
            String req = "update images set imag_blob = ? where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
         
          
            ps.setString(2, t.getImg_blob());
            ps.setInt(3, t.getImg_id());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceImageIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
// requete de suppression d'une categorie 
    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from Images where id= ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceImageIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// requete de affichage de la table categorie 
    @Override
    public List<Images> afficher() {
        List<Images> list = new ArrayList<>();
        try {
            String req ="select * from images ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Images im = new Images();
                im.setImg_id(rs.getInt(1));
                im.setImg_blob(rs.getString("img_blob"));
                im.setId_voiture(rs.getInt("id_voiture"));
                list.add(im);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceImageIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
         @Override
    public List<Images> chercherVoiture( String nom) {
         List<Images> list=new ArrayList<>();
      String req="SELECT * FROM images where img_nom='"+nom+"'";
      try {
             //exec
             Statement st=cnx.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
             {
                
                 list.add(new Images (rs.getInt("id_img"),rs.getString("img_blob"),rs.getInt("id_voiture")));
             }
         } catch (SQLException ex) {
           Logger.getLogger(ServiceImageIMP.class.getName()).log(Level.SEVERE, null, ex);
         }
return list;
    }
 

    
    public int getIdCategorie(String libelle){
        int id_cat =0 ;
        try {
            String req ="select id from images where libelle = '"+libelle+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
               while(rs.next()){
                   
                   id_cat= rs.getInt(1);
                   
               }
             
                    } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id_cat;
    }
    @Override
    public List<List> afficherr() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Images> triVoiture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      public Images afficherimagebyid_voiture(int id_img) {
                 Images m = new Images();

         try {
            String req ="SELECT * FROM images where id_voiture="+id_img;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               
                m.setImg_id(rs.getInt(1));
                m.setImg_blob(rs.getString("img_blob"));
                m.setId_voiture(rs.getInt("id_voiture"));    
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
   
}
