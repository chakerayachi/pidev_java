/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import utils.MyDB;
import entities.Commentaire;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            String req="insert into commentaire (contenu,date,idsujet,iduser) values "+"('"+t.getContenu()+"','"+t.getDate()+"','"+t.getIdsujet()+"','"+t.getIduser()+"')";
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

    
              public boolean supprimercommentaires(int id) {
         try {
            String req="delete from commentaire where idsujet=?";
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
            String req="select * from commentaire inner join sujet on commentaire.idsujet="+id_sujet+" and sujet.idsujet="+id_sujet+" order by nblike desc";
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
                Commentaire c = new Commentaire(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getInt(4),rs.getInt(6),rs.getInt(7));
                list.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ObservableList<Commentaire> getcommentbysujet(int id_sujet) {
        //List<Sujet> list = new ArrayList<Sujet>();   
         ObservableList<Commentaire> commentaireliste = FXCollections.observableArrayList();
        try {
            String req="select * from commentaire inner join sujet on commentaire.idsujet="+id_sujet+" and sujet.idsujet="+id_sujet+" order by nblike desc";
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
                Commentaire c = new Commentaire(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getInt(4),rs.getInt(6),rs.getInt(7));
                commentaireliste.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentaireliste;
    }
   
    public void setlike(int id_com,int nb) {
        try {
            String req= "update commentaire set nblike=?  where idcom= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1,nb);
            ps.setInt(2,id_com);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public int getnblike(int id) {
        int s=0;
        try {  
            
            String req="select * from commentaire where idcom="+id;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
           while(rs.next())
            {
                s = rs.getInt(6);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
   public void setdislike(int id_com,int nb) {
        try {
            String req= "update commentaire set nbdislike=?  where idcom= ?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1,nb);
            ps.setInt(2,id_com);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public int getnbdislike(int id) {
        int s=0;
        try {  
            
            String req="select * from commentaire where idcom="+id;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
           while(rs.next())
            {
                s = rs.getInt(7);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
 
 public int veriflikeuser(int iduser,int idcom)
 {
      int s=0;
        try {  
            
            String req="select * from likee where id_user="+iduser+" and id_commentaire="+idcom;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
           while(rs.next())
            {
                s = rs.getInt(1);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
 }
 public boolean ajoutlike(int iduser,int idcom) {
        try {
            String req="insert into likee (id_user,id_commentaire) values "+"('"+iduser+"','"+idcom+"')";
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
  public int verifdislikeuser(int iduser,int idcom)
 {
      int s=0;
        try {  
            
            String req="select * from dislikee where id_user="+iduser+" and id_commentaire="+idcom;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
           while(rs.next())
            {
                s = rs.getInt(1);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
 }
 public boolean ajoutdislike(int iduser,int idcom) {
        try {
            String req="insert into dislikee (id_user,id_commentaire) values "+"('"+iduser+"','"+idcom+"')";
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
 
  public boolean supprimerlike(int id) {
         try {
            String req="delete from likee where id_like=?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return true;

    }
 public boolean supprimerdislike(int id) {
         try {
            String req="delete from dislikee where id_dislike=?";
            PreparedStatement ps=cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSujetIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return true;

    }
 public Commentaire getcommentbyid(int id_com) {
       // List<Commentaire> list = new ArrayList<Commentaire>();   
       Commentaire c = new Commentaire(); 
       try {
            String req="select * from commentaire where idcom="+id_com;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
               c = new Commentaire(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getInt(4));
               // list.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
  public Utilisateur getuserbyid(int id_user) {
       // List<Commentaire> list = new ArrayList<Commentaire>();   
       Utilisateur user = new Utilisateur(); 
       try {
            String req="select * from utilisateur where id="+id_user;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
               user = new Utilisateur();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setCin(rs.getInt("cin"));
                user.setAdresse(rs.getString("adresse"));
                user.setImage(rs.getString("image"));
                user.setDescription(rs.getString("description"));
                user.setRole(rs.getString("role"));
                user.setEtat(rs.getString("etat"));
                user.setAccount_date(rs.getString("account_date"));
               // list.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
   public List<Commentaire> affichercommentairebysujetlikee(int id_sujet,String text) {
        List<Commentaire> list = new ArrayList<Commentaire>();   
        try {
            String req="select * from commentaire where contenu like '%"+text+"%' and idsujet="+id_sujet;
            Statement st=cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while(rs.next())
            {
                Commentaire c = new Commentaire(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getInt(4),rs.getInt(6),rs.getInt(7));
                list.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
