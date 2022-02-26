/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Admin;
import entities.Agencier;
import entities.Client;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author chaker
 */
public class ServiceUtilisateurIMP implements Iutilisateur<Utilisateur> {

    Connection cnx;

    public ServiceUtilisateurIMP() {

        cnx = MyDB.getInstance().getConnection();

    }

    @Override
    public void ajoutUtilisateur(Utilisateur t) {
        Date date1 = new Date();
        String account_date = new SimpleDateFormat("yyyy-MM-dd").format(date1);

        try {

            String req = "INSERT INTO utilisateur (login , password , nom ,prenom , email , num_tel , cin , adresse , role , image , description , etat , account_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getLogin());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getNom());
            ps.setString(4, t.getPrenom());
            ps.setString(5, t.getEmail());
            ps.setInt(6, t.getNum_tel());
            ps.setInt(7, t.getCin());
            ps.setString(8, t.getAdresse());
            ps.setString(9, t.getRole());
            ps.setString(10, t.getImage());
            ps.setString(11, t.getDescription());
            ps.setString(12, t.getEtat());
            ps.setString(13, t.getAccount_date());

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println(" l insertion de l utilisateur :" + t.getNom() + " " + t.getPrenom() + " a ete effectuer avec sucess , date de l ajout est : " + t.getAccount_date());
            }

        } catch (SQLException ex) {

            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ajoutClient(Utilisateur t) {
        Date date1 = new Date();
        String account_date = new SimpleDateFormat("yyyy-MM-dd").format(date1);

        try {

            String req = "INSERT INTO utilisateur (login , password , nom ,prenom , email , num_tel , cin , adresse , role , image , description , etat , account_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getLogin());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getNom());
            ps.setString(4, t.getPrenom());
            ps.setString(5, t.getEmail());
            ps.setInt(6, t.getNum_tel());
            ps.setInt(7, t.getCin());
            ps.setString(8, t.getAdresse());
            ps.setString(9, "client");
            ps.setString(10, t.getImage());
            ps.setString(11, t.getDescription());
            ps.setString(12, t.getEtat());
            ps.setString(13, t.getAccount_date());

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println(" l insertion du client  :" + t.getNom() + " " + t.getPrenom() + " a ete effectuer avec sucess , date de l ajout est : " + t.getAccount_date());
            }

        } catch (SQLException ex) {

            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ajoutAgencier(Utilisateur t) {
        Date date1 = new Date();
        String account_date = new SimpleDateFormat("yyyy-MM-dd").format(date1);

        try {

            String req = "INSERT INTO utilisateur (login , password , nom ,prenom , email , num_tel , cin , adresse , role , image , description , etat , account_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getLogin());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getNom());
            ps.setString(4, t.getPrenom());
            ps.setString(5, t.getEmail());
            ps.setInt(6, t.getNum_tel());
            ps.setInt(7, t.getCin());
            ps.setString(8, t.getAdresse());
            ps.setString(9, "agencier");
            ps.setString(10, t.getImage());
            ps.setString(11, t.getDescription());
            ps.setString(12, t.getEtat());
            ps.setString(13, t.getAccount_date());

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println(" l insertion de l agencier :" + t.getNom() + " " + t.getPrenom() + " a ete effectuer avec sucess , date de l ajout est : " + t.getAccount_date());
            }

        } catch (SQLException ex) {

            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifierUtilisateur(Utilisateur t, int id) {
        try {

            String req = "UPDATE utilisateur set login = ? , password = ? , nom = ? ,prenom = ?, email = ?, num_tel= ?, cin = ? , adresse = ? , role = ? , image = ? , description= ?, etat = ? , account_date = ?  WHERE id =" + id;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getLogin());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getNom());
            ps.setString(4, t.getPrenom());
            ps.setString(5, t.getEmail());
            ps.setInt(6, t.getNum_tel());
            ps.setInt(7, t.getCin());
            ps.setString(8, t.getAdresse());
            ps.setString(9, t.getRole());
            ps.setString(10, t.getImage());
            ps.setString(11, t.getDescription());
            ps.setString(12, t.getEtat());
            ps.setString(13, t.getAccount_date());

            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la modification de l utilisateur :" + t.getNom() + " " + t.getPrenom() + " a ete effectuer avec sucess");
            }

        } catch (SQLException ex) {

            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerUtilisateur(int id) {
        try {

            String req = "delete from utilisateur where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int value_supp = ps.executeUpdate();
            if (value_supp > 0) {
                System.out.println(" Suppression a ete effectuer avec sucess");
            }

        } catch (SQLException ex) {

            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DescativerUser(int id) {
        try {
            String req = "Update utilisateur set etat = 'desactive' where id =" + id;
            PreparedStatement ps = cnx.prepareStatement(req);
            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la desactivation de l utilisateur   a ete effectuer avec sucess");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ActiverUser(int id) {
        try {
            String req = "Update utilisateur set etat = 'active' where id =" + id;
            PreparedStatement ps = cnx.prepareStatement(req);
            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la activation de l utilisateur   a ete effectuer avec sucess");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Utilisateur> afficherUtilisateur() {

        List<Utilisateur> list_Utilisateur = new ArrayList<>();
        try {

            String req = "select * from utilisateur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Utilisateur user = new Utilisateur();
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

                list_Utilisateur.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_Utilisateur;

    }

    @Override
    public List<Utilisateur> afficherClientList() {
        List<Utilisateur> list_Clients = new ArrayList<>();
        try {

            String req = "select * from utilisateur where role = 'client'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt(1));
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

                list_Clients.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list_Clients;

    }

    @Override
    public List<Utilisateur> afficherAgencierList() {

        List<Utilisateur> list_agenciers = new ArrayList<>();
        try {

            String req = "select * from utilisateur where role = 'agencier'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt(1));
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

                list_agenciers.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list_agenciers;

    }

    public String FindNomUserById(int id) {
        String nom = null;
        try {
            String req = "select * from utilisateur where id =" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                nom = rs.getString(4);
                System.out.println(nom);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;

    }

    public List<Utilisateur> TrieParDateCreation() {

        List<Utilisateur> list_utilisateur = new ArrayList<>();

        try {
            String req = "SELECT * FROM utilisateur ORDER BY account_date ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            Utilisateur user = null;
            while (rs.next()) {
                user = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14));
                list_utilisateur.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list_utilisateur);

        return list_utilisateur;

    }

    public String getUtilisateurRole(int id) {
        String role = null;
        try {
            String req = "SELECT role FROM utilisateur WHERE id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                role = rs.getString("role");

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(role);
        return role;

    }

    public Utilisateur getUserById(int id) {
        Utilisateur user = new Utilisateur();
        Admin admin = new Admin();
        Client client = new Client();
        Agencier agencier = new Agencier();

        // cas admin
        if ("admin".equals(getUtilisateurRole(id))) {
            try {
                String req = "SELECT * FROM utilisateur WHERE id=" + id;
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);

                while (rs.next()) {
                    admin.setId(rs.getInt("id"));
                    admin.setLogin(rs.getString("login"));
                    admin.setPassword(rs.getString("password"));
                    admin.setNom(rs.getString("nom"));
                    admin.setPrenom(rs.getString("prenom"));
                    admin.setEmail(rs.getString("email"));
                    admin.setNum_tel(rs.getInt("num_tel"));
                    admin.setCin(rs.getInt("cin"));
                    admin.setAdresse(rs.getString("adresse"));
                    admin.setRole(rs.getString("role"));
                    admin.setImage(rs.getString("image"));
                    admin.setDescription(rs.getString("description"));
                    admin.setEtat(rs.getString("etat"));
                    admin.setAccount_date(rs.getString("account_date"));

                }

            } catch (SQLException ex) {
                Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
            user = admin;

        }

        // cas client
        if ("client".equals(getUtilisateurRole(id))) {
            try {
                String req = "SELECT * FROM utilisateur WHERE id=" + id;
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);
                while (rs.next()) {
                    admin.setId(rs.getInt("id"));
                    admin.setLogin(rs.getString("login"));
                    admin.setPassword(rs.getString("password"));
                    admin.setNom(rs.getString("nom"));
                    admin.setPrenom(rs.getString("prenom"));
                    admin.setEmail(rs.getString("email"));
                    admin.setNum_tel(rs.getInt("num_tel"));
                    admin.setCin(rs.getInt("cin"));
                    admin.setAdresse(rs.getString("adresse"));
                    admin.setRole(rs.getString("role"));
                    admin.setImage(rs.getString("image"));
                    admin.setDescription(rs.getString("description"));
                    admin.setEtat(rs.getString("etat"));
                    admin.setAccount_date(rs.getString("account_date"));

                }
                user = client;

            } catch (SQLException ex) {
                Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        // cas agencier
        if ("agencier".equals(getUtilisateurRole(id))) {
            try {
                String req = "SELECT * FROM utilisateur WHERE id=" + id;
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);
                while (rs.next()) {
                    admin.setId(rs.getInt("id"));
                    admin.setLogin(rs.getString("login"));
                    admin.setPassword(rs.getString("password"));
                    admin.setNom(rs.getString("nom"));
                    admin.setPrenom(rs.getString("prenom"));
                    admin.setEmail(rs.getString("email"));
                    admin.setNum_tel(rs.getInt("num_tel"));
                    admin.setCin(rs.getInt("cin"));
                    admin.setAdresse(rs.getString("adresse"));
                    admin.setRole(rs.getString("role"));
                    admin.setImage(rs.getString("image"));
                    admin.setDescription(rs.getString("description"));
                    admin.setEtat(rs.getString("etat"));
                    admin.setAccount_date(rs.getString("account_date"));

                }
                user = agencier;

            } catch (SQLException ex) {
                Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println(user);
        return user;
    }

    public Boolean FindByNom(String nom) {

        try {
            String req = "SELECT * FROM utilisateur WHERE nom ='" + nom + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            String user_nom = null;

            while (rs.next()) {
                user_nom = rs.getString(4);
                if (nom.equals(user_nom)) {
                    return true;

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public Utilisateur FindByLoginAndPassword(String login, String password) {
        Utilisateur user = new Utilisateur();

        try {
            String req = "SELECT * from utilisateur where login='" + login + "'AND password='" + password + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setCin(rs.getInt("cin"));
                user.setAdresse(rs.getString("adresse"));
                user.setRole(rs.getString("role"));
                user.setImage(rs.getString("image"));
                user.setDescription(rs.getString("description"));
                user.setEtat(rs.getString("etat"));
                user.setAccount_date(rs.getString("account_date"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }

    public Utilisateur Authentification(String login, String password) {
        Utilisateur user = null;
// 

        try {
            String req = "select * from Utilisateur where login = ? and password = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateurIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(user);
        return user;

    }

    public String encrypt(String password) {

        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String decrypt(String password) {

        return new String(Base64.getMimeDecoder().decode(password));
    }

}
