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
public class ServiceSujetIMP implements IForum<Sujet>{
    Connection cnx;

    public ServiceSujetIMP() {
        cnx= MyDB.getInstance().getConnection();
    }

    @Override
    public boolean ajout(Sujet t) {
        try {
            String req="insert into sujet (titresujet,contenu,date,accepter,nbcom,iduser,idtopic) values "+"('"+t.getTitresujet()+"','"+t.getContenu()+"','"+t.getDate()+"','"+t.isAccepter()+"','"+t.getNbcom()+"','"+t.getIduser()+"','"+t.getIdtopic()+"')";
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
    public boolean modifier(Sujet t) {
        try {
            String req= "update sujet set titresujet = ? , contenu = ? , date = ?  where idsujet= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setString(1, t.getTitresujet());
            ps.setString(2, t.getContenu());
            ps.setString(3, t.getDate());
            ps.setInt(4, t.getIdsujet());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean supprimer(int id) {
        try {
            String req="delete from sujet where idsujet=?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
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
                Sujet s = new Sujet(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));
                list.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean validersujet(int id_sujet) {
        try {
            String req= "update sujet set accepter=1  where idsujet= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1,id_sujet);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public List<Sujet> affichersujetvalide() {
        List<Sujet> list = new ArrayList<Sujet>();   
        try {
            String req="select * from sujet where accepter=1";
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
                Sujet s = new Sujet(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));
                list.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public int getnbcom(int id) {
        int s=0;
        try {  
            
            String req="select * from sujet where idsujet="+id;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
           while(rs.next())
            {s = rs.getInt(6);}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    public void setnbcom(int id_sujet,int nb) {
        try {
            String req= "update sujet set nbcom=?  where idsujet= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1,nb);
            ps.setInt(2,id_sujet);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Sujet> affichersujetbytopic(int id_topic) {
        List<Sujet> list = new ArrayList<Sujet>();   
        try {
            String req="select * from sujet inner join topic on sujet.idtopic="+id_topic+" and topic.idtopic="+id_topic;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
                Sujet s = new Sujet(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));
                list.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
