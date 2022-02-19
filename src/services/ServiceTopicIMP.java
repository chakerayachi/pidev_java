/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Sujet;
import entities.Topic;
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
 * @author Firas CHKOUNDALI
 */
public class ServiceTopicIMP implements IForum<Topic>{
    Connection cnx;

    public ServiceTopicIMP() {
        cnx= MyDB.getInstance().getConnection();
    }

    @Override
    public boolean ajout(Topic t) {
        try {
            String req="insert into topic (titretopic,description,date,accepter,nbsujet,iduser) values "+"('"+t.getTitretopic()+"','"+t.getDescription()+"','"+t.getDate()+"','"+t.isAccepter()+"','"+t.getNbsujet()+"','"+t.getIduser()+"')";
            Statement st= cnx.createStatement();
            try {
                st.executeUpdate(req);
            } catch (SQLException ex) {
                Logger.getLogger(ServiceTopicIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTopicIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean modifier(Topic t) {
        try {
            String req= "update topic set titretopic = ? , description = ? , date = ?  where idtopic= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setString(1, t.getTitretopic());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getDate());
            ps.setInt(4, t.getIdtopic());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean supprimer(int id) {
        try {
            String req="delete from topic where idtopic=?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public List<Topic> afficher() {
        List<Topic> list = new ArrayList<Topic>();   
        try {
            String req="select * from topic";
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
                Topic t = new Topic(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
                list.add(t);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        }
    
     public int getnbsujet(int id) {
        int s=0;
        try {  
            
            String req="select * from topic where idtopic="+id;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
           while(rs.next())
            {s = rs.getInt(6);}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    public void setnbsujet(int id_topic,int nb) {
        try {
            String req= "update topic set nbsujet=?  where idtopic= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1,nb);
            ps.setInt(2,id_topic);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
