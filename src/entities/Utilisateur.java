/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author chaker
 */
public class Utilisateur {
    
    private  int id ;
    private String login ;
    private String password;
    private String nom ;
    private String prenom ;
    private String email ;
    private int num_tel ;
    private int cin ;
    private String adresse ;
    private String role ;
    private String image ;
    private String description ;
    private String etat ;

    // constructeur par default


    public Utilisateur(){}



    // constructeur avec le champs id

    public Utilisateur(int id, String login, String password, String nom, String prenom, String email, int num_tel, int cin, String adresse, String role, String image, String description , String etat) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.adresse = adresse;
        this.role = role;
        this.image = image;
        this.description = description;
        this.etat= etat;
    }



    // constructeur sans le champs id

    public Utilisateur( String login, String password, String nom, String prenom, String email, int num_tel, int cin, String adresse, String role, String image, String description , String etat) {

        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.adresse = adresse;
        this.role = role;
        this.image = image;
        this.description = description;
        this.etat = etat;
    }



    // getters ans setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    


    // methode to string


    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", num_tel=" + num_tel +
                ", cin=" + cin +
                ", adresse='" + adresse + '\'' +
                ", role='" + role + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                 ", etat='" + etat + '\'' +
                '}';
    }
    
    
    
    
    
    
}
