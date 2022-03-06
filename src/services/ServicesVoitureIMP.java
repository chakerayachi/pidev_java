/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Categorie;
import entities.Maison;
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
import java.lang.String;

/**
 *
 * @author Hani
 */
public class ServicesVoitureIMP  {

    Connection cnx;

    public ServicesVoitureIMP() {
        cnx = MyDB.getInstance().getConnection();
    }
// requete pour ajouter une voiture 
    public void ajout(Voiture t) {
        try {
            String req = "insert into voiture (immat,model,marque,couleur,capacite,description,id_categorie) values"
                    + " ( '" + t.getImmat()+ "','" + t.getmodel() + "', '" + t.getMarque() + "', '" + t.getCouleur() + "', '" + t.getCapacite() +"', '"+t.getDescription() +"','"+t.getId_categorie()+ ")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
// requete pour modifier une voiture 

    public void modifier(Voiture t) {
        try {
            String req = "update voiture set immat ='"+t.getImmat()+"', model = '"+t.getmodel()+"', marque = '"+t.getMarque()+"', couleur ='"+t.getCouleur()+"',capacite = "+t.getCapacite()+"', description ='"+t.getDescription()+"' where id = '"+t.getId();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
// requete pour supprimer une voiture 

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


    public List<Voiture> afficher() {
        List<Voiture> list = new ArrayList<>();
        try {
            String req ="SELECT * FROM voiture ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Voiture v = new Voiture();
               
                v.setId(rs.getInt(1));
                v.setImmat(rs.getString("immat"));
                v.setmodel(rs.getString("model"));
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
    
   

    public List<List> afficherr() {
         ArrayList<List> a = new ArrayList<>();
         a.clear();
    try {
        
        String  req = "SELECT v.id, model,marque,couleur,capacite,v.description,immat,c.id, libelle FROM categorie c JOIN voiture v";
        Statement st = cnx.createStatement();
        
             try (ResultSet rs = st.executeQuery(req)) {
                 while (rs.next()) {
                     
                 List<Object> list=new ArrayList();
                 
                    list.clear();
                     // a.add(rs.getInt(1));
                     
                     list.add(rs.getString("immat"));
                     list.add(rs.getString("model"));
                     list.add(rs.getString("marque"));
                     list.add(rs.getString("couleur"));
                     list.add(String.valueOf(rs.getInt("capacite")));
                     list.add(rs.getString("description"));
  
                     list.add(rs.getString("libelle"));
                     list.add(rs.getString("v.id"));
                     list.add(rs.getString("c.id"));
                     
                      
                   a.add(list); 
                 } 
             }
        } catch (SQLException se) {
        } catch (Exception e) {
        }
        return a;

    }
        
    
    

    public List<Voiture> chercherVoiture( String nom) {
         List<Voiture> list=new ArrayList<>();
      String req="SELECT * FROM voiture where immat='"+nom+"' or model='"+nom+"' or   marque='"+nom+"' or couleur='"+nom+"' or capacite='"+nom+"'";
      try {
             //exec
             Statement st=cnx.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
             {
                 //String nom, String prenom, String sexe, String date,String email, String login, String mdp, String role
                 list.add(new Voiture (rs.getInt("id"),rs.getString("immat") , rs.getString("model"), rs.getString("marque"), rs.getString("couleur"), rs.getInt("capacite"),rs.getString("description")));
             }
         } catch (SQLException ex) {
           Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
         }
return list;
    }

    public List<Voiture> triVoiture() {
         List<Voiture> list=new ArrayList<>();
      String req="SELECT * FROM voiture ORDER BY immat";
      try {
             //exec
             Statement st=cnx.createStatement();
             ResultSet rs= st.executeQuery(req);
             while(rs.next())
             {
                 //String nom, String prenom, String sexe, String date,String email, String login, String mdp, String role
                 list.add(new Voiture (rs.getInt("id"),rs.getString("immat"),rs.getString("model"), rs.getString("marque"), rs.getString("couleur"), rs.getInt("capacite"),rs.getString("description")));
             }
         } catch (SQLException ex) {
           Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
    }
    public List<Voiture> get_voiture_by_id(int id_voiture){ 
        List<Voiture> voiture_list = new ArrayList(); 
         String Request = "SELECT * FROM voiture where id="+id_voiture;
         PreparedStatement  pst;
         try {
            pst = cnx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
              Voiture v = new Voiture();
                        v.setId(rs.getInt(1));
                        v.setMarque(rs.getString("marque"));
                        v.setCouleur(rs.getString("couleur"));
                        v.setCapacite(rs.getInt("capacite"));
                        v.setDescription(rs.getString("description"));
                        v.setPrix(rs.getFloat("prix"));
                voiture_list.add(v);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return voiture_list;
    }

}

    

