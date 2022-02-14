/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
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
 * @author chaker
 */
public class ServiceUtilisateurIMP implements Iutilisateur<Utilisateur>{
    
        Connection cnx;
        
        public ServiceUtilisateurIMP(){
            
                cnx = MyDB.getInstance().getConnection();

        }


    @Override
    public void ajoutUtilisateur(Utilisateur t) {
            try {
                
                
                String req = "INSERT INTO utilisateur (login , password , nom ,prenom , email , num_tel , cin , adresse , role , image , description , etat) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, t.getLogin());
                ps.setString(2, t.getPassword());
                ps.setString(3, t.getNom());
                ps.setString(4, t.getPrenom());
                ps.setString(5, t.getEmail());
                ps.setInt(6, t.getNum_tel());
                ps.setInt(7, t.getCin());
                ps.setString(8, t.getAdresse());
                ps.setString(9, t.getRole());
                ps.setString(10, t.getImage());
                ps.setString(11, t.getDescription());
                ps.setString(12, t.getEtat());
                
                ps.executeUpdate();
                
                
                
            } catch (SQLException ex) {
                
                Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        
    }

    @Override
    public void modifierUtilisateur(Utilisateur t) {
        try {
            
                String req = "UPDATE utilisateur set login = ? , password = ? , nom = ? ,prenom = ?, email = ?, num_tel= ?, cin = ? , adresse = ? , role = ? , image = ? , description= ?, etat = ? WHERE id = ?";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, t.getLogin());
                ps.setString(2, t.getPassword());
                ps.setString(3, t.getNom());
                ps.setString(4, t.getPrenom());
                ps.setString(5, t.getEmail());
                ps.setInt(6, t.getNum_tel());
                ps.setInt(7, t.getCin());
                ps.setString(8, t.getAdresse());
                ps.setString(9, t.getRole());
                ps.setString(10, t.getImage());
                ps.setString(11, t.getDescription());
                ps.setString(12, t.getEtat());
               // ps.setInt(13, t.getId());
                // pour la test on lui ajout manuellement
                 ps.setInt(13,5);
                
                
                ps.executeUpdate();
                
                
                
            } catch (SQLException ex) {
                
                Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @Override
    public void supprimerUtilisateur(int id) {
          try {
              
            String req = "delete from utilisateur where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public List<Utilisateur> afficherUtilisateur() {

         List<Utilisateur> list_Utilisateur = new ArrayList<>();
        try {
            
            String req ="select * from utilisateur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt(1));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setCin(rs.getInt("cin"));
                user.setAdresse(rs.getString("adresse"));
                user.setImage(rs.getString("image"));
                user.setDescription(rs.getString("description"));
                user.setEtat(rs.getString("etat"));
                
                list_Utilisateur.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_Utilisateur;

    }

    @Override
    public List<Utilisateur> afficherClientList() {
            return null;



    }

    @Override
    public List<Utilisateur> afficherAgencierList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
