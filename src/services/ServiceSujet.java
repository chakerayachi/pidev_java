/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import utils.MyDB;
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
public class ServiceSujet implements IService<Sujet>{
    Connection cnx;

    public ServiceSujet() {
        cnx= MyDB.getInstance().getConnection();
    }

    @Override
    public void ajout(Sujet t) {
        try {
            String req="insert into sujet (titresujet,contenu,date,accepter,nbcom,iduser) values "+"('"+t.getTitresujet()+"','"+t.getContenu()+"','"+t.getDate()+"','"+t.isAccepter()+"','"+t.getNbcom()+"','"+t.getIduser()+"')";
            Statement st= cnx.createStatement();
            try {
                st.executeUpdate(req);
            } catch (SQLException ex) {
                Logger.getLogger(ServiceSujet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Sujet t) {
        try {
            String req= "update sujet set titresujet = ? , contenu = ? , date = ?  where idsujet= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setString(1, t.getTitresujet());
            ps.setString(2, t.getContenu());
            ps.setString(3, t.getDate());
            ps.setInt(4, t.getIdsujet());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req="delete from sujet where idsujet=?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Sujet> afficher() {
        List<Sujet> list = new ArrayList<Sujet>();   
        try {
            String req="select * from sujet";
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
                Sujet s = new Sujet(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
                list.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
}
