/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Categorie;
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
public class ServiceCategorieIMP implements IService<Categorie> {

    Connection cnx;

    public ServiceCategorieIMP() {
        cnx = MyDB.getInstance().getConnection();
    }
// la requet d'ajout d'une categorie
    @Override
    public void ajout(Categorie t) {
        try {
            String req = "insert into categorie (id,libelle,description) values"
                    + " ( '" + t.getId() + "', '" + t.getLibelle()+"', '" + t.getDescription() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
// requete de modification
    @Override
    public void modifier(Categorie t) {
        try {
            String req = "update categorie set libelle = ?  , description = ? where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getLibelle());
          
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieIMP.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServiceCategorieIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// requete de affichage de la table categorie 
    @Override
    public List<Categorie> afficher() {
        List<Categorie> list = new ArrayList<>();
        try {
            String req ="select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Categorie c = new Categorie();
                c.setId(rs.getInt(1));
                c.setLibelle(rs.getString("libelle"));
                c.setDescription(rs.getString("description"));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
         @Override
    public List<Categorie> chercherVoiture( String nom) {
         List<Categorie> list=new ArrayList<>();
      String req="SELECT * FROM voiture where  libelle='"+nom+"'  or capacite='"+nom+"'";
      try {
             //exec
             Statement st=cnx.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
             {
                 //String nom, String prenom, String sexe, String date,String email, String login, String mdp, String role
                 list.add(new Categorie (rs.getInt("id"),rs.getString("libelle"),rs.getString("description")));
             }
         } catch (SQLException ex) {
           Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
         }
return list;
    }
    @Override
    public List<Categorie> triVoiture() {
         List<Categorie> list=new ArrayList<>();
      String req="SELECT * FROM voiture ORDER BY libelle";
      try {
             //exec
             Statement st=cnx.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
             {
                 
                 list.add(new Categorie (rs.getInt("id"),rs.getString("libelle"),rs.getString("description")));
             }
         } catch (SQLException ex) {
           Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
         }
return list;
    }

}
