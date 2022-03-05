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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author Hani
 */

public class ServiceReclamationIMP implements IService<Reclamation>{
        Connection cnx;

    public ServiceReclamationIMP() {
        cnx = MyDB.getInstance().getConnection();
    }
     Date date1 = new Date();
        String account_date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
// la requet d'ajout 
    
    @Override
    public void ajout(Reclamation t) {
        try {
            String req = "insert into reclamation (description,type,date) values"
                    + " (  '" + t.getDiscreption()+"', '" + t.getType()+"', '"+account_date +  "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamationIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
// requete de modification
    @Override
    public void modifier(Reclamation t) {
        try {
            String req = "update reclamation set  description = ? , type = ? ,date = ? where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
           
          
            ps.setString(1, t.getDiscreption());
            ps.setString(2,t.getType());
            ps.setString(3, t.getDate());
            ps.setInt(4, t.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamationIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
// requete de suppression  
    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from reclamation where id= ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamationIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// requete de affichage de la table  
    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> list = new ArrayList<>();
        try {
            String req ="select * from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Reclamation c = new Reclamation();
                c.setId(rs.getInt(1));
          
                c.setDiscreption(rs.getString("description"));
                c.setDate(rs.getString("date"));
                c.setType(rs.getString("type"));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamationIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Reclamation> chercherRec(String rec) {
        List<Reclamation> list = new ArrayList<>();
        try {
            String req ="select * from reclamation where type='"+rec+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Reclamation c = new Reclamation();
                c.setId(rs.getInt(1));
          c.setDate(rs.getString("date"));
                c.setDiscreption(rs.getString("description"));
                c.setType(rs.getString("type"));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamationIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Reclamation> chercherVoiture(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> triVoiture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<List> afficherr() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
