/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Reclamation;
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
 * @author Hani
 */
public class ServiceReclamationIMP implements Iservicecat<Reclamation>{
        Connection cnx;

    public ServiceReclamationIMP() {
        cnx = MyDB.getInstance().getConnection();
    }
// la requet d'ajout d'une categorie
    @Override
    public void ajout(Reclamation t) {
        try {
            String req = "insert into reclamation (id,description) values"
                    + " ( '" + t.getId() + "', '" + t.getDiscreption()+"', '" +  "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
// requete de modification
    @Override
    public void modifier(Reclamation t) {
        try {
            String req = "update reclamation set  description = ? where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
           
          
            ps.setString(1, t.getDiscreption());
            ps.setInt(2, t.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamationIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
// requete de suppression d'une categorie 
    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from categorie where id= ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamationIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// requete de affichage de la table categorie 
    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> list = new ArrayList<>();
        try {
            String req ="select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Reclamation c = new Reclamation();
                c.setId(rs.getInt(1));
          
                c.setDiscreption(rs.getString("description"));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamationIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    /*    @Override
    public List<Reclamation> chercher() {
        List<Reclamation> list = new ArrayList<>();
        try {
            String req ="select * from categorie where id= ? ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Reclamation c = new Reclamation();
                c.setId(rs.getInt(1));
          
                c.setDiscreption(rs.getString("description"));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamationIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }*/
}
