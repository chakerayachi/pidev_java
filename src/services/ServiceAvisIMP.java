/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Avis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author Hani
 */
public class ServiceAvisIMP {
      Connection cnx;
    
        public ServiceAvisIMP() {
        cnx = MyDB.getInstance().getConnection();
    }
     public void ajout(Avis t) {
        try {
            String req = "insert into avis  (valeur,id_voiture) values (?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setDouble(1,t.getValeur());
            st.setInt(2, t.getId_voiture());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAvisIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     
     
     public Avis get_avis_byid_voiture(int id_img) {
                 Avis a = new Avis();

         try {
            String req ="SELECT * FROM avis where id_voiture="+id_img;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               
                a.setId(rs.getInt(1));
                a.setValeur(rs.getDouble("valeur"));
                a.setId_voiture(rs.getInt("id_voiture"));    
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
}
