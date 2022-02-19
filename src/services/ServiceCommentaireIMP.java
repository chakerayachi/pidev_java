/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import utils.MyDB;
import entities.Commentaire;
import entities.Sujet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Firas CHKOUNDALI
 */
public class ServiceCommentaireIMP implements IForum<Commentaire>{
    Connection cnx;

    public ServiceCommentaireIMP() {
        cnx= MyDB.getInstance().getConnection();

    }

    @Override
    public boolean ajout(Commentaire t) {
        try {
            String req="insert into commentaire (contenu,date,idsujet,iduser,idtopic) values "+"('"+t.getContenu()+"','"+t.getDate()+"','"+t.getIdsujet()+"','"+t.getIduser()+"','"+t.getIdtopic()+"')";
            Statement st= cnx.createStatement();
            try {
                st.executeUpdate(req);
            } catch (SQLException ex) {
                Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
                return true;

    }

    @Override
    public boolean modifier(Commentaire t) {
        try {
            String req= "update commentaire set contenu = ? , date = ?   where idcom= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setString(1, t.getContenu());
            ps.setString(2, t.getDate());
            ps.setInt(3, t.getIdcom());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
                return true;

    }

    @Override
    public boolean supprimer(int id) {
         try {
            String req="delete from commentaire where idcom=?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return true;

    }

    @Override
    public List<Commentaire> afficher() {
        List<Commentaire> list = new ArrayList<Commentaire>();   
        try {
            String req="select * from commentaire";
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
                Commentaire c = new Commentaire(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getInt(4));
                list.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Commentaire> affichercommentairebysujet(int id_sujet) {
        List<Commentaire> list = new ArrayList<Commentaire>();   
        try {
            String req="select * from commentaire inner join sujet on commentaire.idsujet="+id_sujet+" and sujet.idsujet="+id_sujet;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
                Commentaire c = new Commentaire(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getInt(4));
                list.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
