/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
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
import utils.MyDB;

/**
 *
 * @author Hani
 */
public class ServicesVoitureIMP implements IService<Voiture> {

    Connection cnx;

    public ServicesVoitureIMP() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajout(Voiture t) {
        try {
            String req = "insert into voiture (libelle,marque,couleur,capacite,description) values"
                    + " ( '" + t.getLibelle() + "', '" + t.getMarque() + "', '" + t.getCouleur() + "', '" + t.getCapacite() +"', '"+t.getDescription() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modifier(Voiture t) {
        try {
            String req = "update voiture set libelle = ? , marque = ? , couleur = ? ,capacite = ? , discription = ? where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, t.getLibelle());
            ps.setString(1, t.getMarque());
            
            ps.setString(3, t.getCouleur());
            ps.setInt(4, t.getCapacite());
            ps.setString(4, t.getDescription());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from voiture where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Voiture> afficher() {
        List<Voiture> list = new ArrayList<>();
        try {
            String req ="select * from voiture";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Voiture v = new Voiture();
                v.setId(rs.getInt(1));
                v.setLibelle(rs.getString("libelle"));
                v.setMarque(rs.getString("marque"));
                v.setCouleur(rs.getString("couleur"));
                v.setCapacite(rs.getInt("capacite"));
                v.setDescription(rs.getString("description"));
                list.add(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}

    

