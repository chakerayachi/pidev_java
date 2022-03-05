/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Categorie;
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
public class ServicesVoitureIMP implements IService<Voiture> {

    Connection cnx;

    public ServicesVoitureIMP() {
        cnx = MyDB.getInstance().getConnection();
    }
// requete pour ajouter une voiture 
    
    public int ajouter(Voiture t) {
        String columnNames[] = new String[] { "id" };
        PreparedStatement  pst;
        int voiture_generated_id=0;
        try {
            String req = "insert into voiture (immat,model,marque,couleur,capacite,description,id_categorie,prix) values"
                    + " ( '" + t.getImmat()+ "','" + t.getmodel() + "', '" + t.getMarque() + "', '" + t.getCouleur() + "', " + t.getCapacite() +", '"+t.getDescription() +"',"+t.getId_categorie()+",'"+t.getPrix() +"')";
            pst = cnx.prepareStatement(req,columnNames);
            pst.executeUpdate();
            java.sql.ResultSet generatedKeys = pst.getGeneratedKeys();
            if ( generatedKeys.next() ) {
                    voiture_generated_id = generatedKeys.getInt(1);
                }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voiture_generated_id;
        
    }
// requete pour modifier une voiture 
    @Override
    public void modifier(Voiture t) {
        try {
           String req = "UPDATE `voiture` SET `immat`='"
                    + t.getImmat()+ "',`couleur`='"
                    + t.getCouleur()+ "',`model`='"
                    + t.getmodel()+ "',`marque`='"
                    + t.getMarque()+ "',`description`='"
                    + t.getDescription() +  "',`id_categorie`='"
                    +t.getId_categorie() + "',`capacite`='"
                   +t.getCapacite()+"',`prix`='"
                  +t.getPrix()+ "' WHERE `id`='"
                    + t.getId()+ "'";
            Statement ps = cnx.createStatement();
            ps.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
// requete pour supprimer une voiture 
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
    
   
    @Override
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
          
    
        @Override
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
    @Override
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

    @Override
    public void ajout(Voiture t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     public Voiture affichervoiturebyud(int id_voit) {
                        Voiture v = new Voiture();

         try {
            String req ="SELECT * FROM voiture where id="+id_voit;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               
                v.setId(rs.getInt(1));
                v.setImmat(rs.getString("immat"));
                v.setmodel(rs.getString("model"));
                v.setMarque(rs.getString("marque"));
                v.setCouleur(rs.getString("couleur"));
                v.setCapacite(rs.getInt("capacite"));
                v.setDescription(rs.getString("description"));
               
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesVoitureIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
   
}



    

