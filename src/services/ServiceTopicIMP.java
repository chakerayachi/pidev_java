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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
            String req="select * from topic order by nbsujet desc";
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
                Topic t = new Topic(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));
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
    
    public ObservableList<Topic> gettopicliste() throws SQLException {
           
        ObservableList<Topic> topicliste = FXCollections.observableArrayList();
        
         List <Topic> id = new ArrayList<>(); 
        Statement stm = cnx.createStatement();
        String req = "select * from topic order by accepter ";

        //ResultSet rs;
        ResultSet rs= stm.executeQuery(req);
        rs = stm.executeQuery(req);
       
        while (rs.next()) {
            
                Topic t = new Topic(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));
            //System.out.println(events);
            
            topicliste.add(t);

        }
        return topicliste;

    }
    
    public Topic gettopicbyid(int id)
    {
        Topic t = null;
      
        try {
            String req="select * from topic where idtopic="+id;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
             t = new Topic(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));
            }
            
                  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTopicIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return t;
    
    }
    
   public int verifexisttopic(String  titre,String description)
 {
     System.out.println(titre);
      int s=0;
        try {  
            
            String req="SELECT * FROM topic where titretopic='"+titre+"' and description='"+description+"'";
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
//            return rs.getFetchSize();
           while(rs.next())
            {
                s = rs.getInt(1);
            }
//            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTopicIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
 }
    public void hidetopic(int id_topic) {
        try {
            String req= "update topic set hide=?  where idtopic= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            int i=1;
            ps.setInt(1,i);
            ps.setInt(2,id_topic);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void hideofftopic(int id_topic) {
        try {
            String req= "update topic set hide=?  where idtopic= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            int i=0;
            ps.setInt(1,i);
            ps.setInt(2,id_topic);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public int gethide(int id) {
        int s=0;
        try {  
            
            String req="select * from topic where idtopic="+id;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
           while(rs.next())
            {s = rs.getInt(8);}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
     public ObservableList<Topic> gettopiclisteafficher() throws SQLException {
           
        ObservableList<Topic> topicliste = FXCollections.observableArrayList();
        
         List <Topic> id = new ArrayList<>(); 
        Statement stm = cnx.createStatement();
        String req = "select * from topic where ( hide="+0+" and accepter="+1+") order by nbsujet desc";

        //ResultSet rs;
        ResultSet rs= stm.executeQuery(req);
        rs = stm.executeQuery(req);
       
        while (rs.next()) {
            
                Topic t = new Topic(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));
            //System.out.println(events);
            
            topicliste.add(t);

        }
        return topicliste;

    }
     public int getaccept(int id) {
        int s=0;
        try {  
            
            String req="select * from topic where idtopic="+id;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
           while(rs.next())
            {s = rs.getInt(5);}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
     public void accepttopic(int id_topic) {
        try {
            String req= "update topic set accepter=?  where idtopic= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            int i=1;
            ps.setInt(1,i);
            ps.setInt(2,id_topic);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
